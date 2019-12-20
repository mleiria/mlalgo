/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.neighbors.classifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.distance.Distance;
import pt.mleiria.mlalgo.distance.DistanceMetric;
import pt.mleiria.mlalgo.distance.EuclideanDistance;
import pt.mleiria.mlalgo.metrics.RegressorMixin;
import pt.mleiria.mlalgo.tasks.DistanceTask;
import pt.mleiria.mlalgo.tasks.ThreadPoolManager;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class KNeighborsClassifier extends ThreadPoolManager implements Estimator {

    private static final Logger LOG = Logger.getLogger(KNeighborsClassifier.class.getName());

    private final int k;
    private DistanceMetric<Double[], Double[], Double> dm;
    private Double[][] xData;
    private Double[] yLabel;

    /**
     * 
     * @param k
     */
    public KNeighborsClassifier(final int k) {
	this.k = k;
	dm = new EuclideanDistance();
    }

    /**
     * 
     * @param k
     * @param dm
     */
    public KNeighborsClassifier(final int k, final DistanceMetric<Double[], Double[], Double> dm) {
	this.k = k;
	this.dm = dm;
    }

    /**
     * 
     * @param dm
     */
    public void setDm(final DistanceMetric<Double[], Double[], Double> dm) {
	this.dm = dm;
    }

    /**
     * 
     * @param xData
     * @param yLabel
     * @return
     */
    @Override
    public Estimator fit(Double[][] xData, Double[] yLabel) {
	this.xData = xData;
	this.yLabel = yLabel;
	return this;
    }

    /**
     * 
     * @param xSample
     * @return
     */
    @Override
    public Double[] predict(Double[][] xSample) {
	final Double[] res = new Double[xSample.length];
	try {
	    for (int j = 0; j < xSample.length; j++) {
		res[j] = predict(xSample[j]);
	    }
	} catch (final InterruptedException ex) {
	    LOG.severe(ex.getMessage());
	    Thread.currentThread().interrupt();
	}
	return res;
    }

    /**
     * 
     * @param xSample
     * @return
     * @throws InterruptedException
     */
    private Double predict(Double[] xSample) throws InterruptedException {
	final Distance[] distances = new Distance[yLabel.length];
	final CountDownLatch endControler = new CountDownLatch(numThreads);
	final int length = yLabel.length / numThreads;
	int startIndex = 0;
	int endIndex = length;

	for (int i = 0; i < numThreads; i++) {
	    final DistanceTask task = new DistanceTask(distances, startIndex, endIndex, xData, xSample, endControler,
		    dm);
	    startIndex = endIndex;
	    if (i < numThreads - 2) {
		endIndex = endIndex + length;
	    } else {
		endIndex = yLabel.length;
	    }
	    executor.execute(task);
	}
	endControler.await();
	Arrays.parallelSort(distances);

	return computeFinal(distances);
    }

    /**
     *
     * @param distances
     * @return
     */
    private double computeFinal(final Distance[] distances) {
	final Map<Double, Integer> results = new HashMap<>();
	for (int i = 0; i < k; i++) {
	    final Double label = yLabel[distances[i].getIndex()];
	    results.merge(label, 1, (a, b) -> a + b);
	}
	return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Override
    public Double score(final Double[][] testX, final Double[] yTrue) {
	final Double[] yPred = predict(testX);
	return new RegressorMixin().score(yPred, yTrue);
    }

    /**
     * 
     * @return
     */
    @Override
    public Double[][] getX() {
	return xData;
    }

    /**
     * 
     * @return
     */
    @Override
    public Double[] getY() {
	return yLabel;
    }

}
