/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.tasks;

import java.util.concurrent.CountDownLatch;

import pt.mleiria.mlalgo.distance.Distance;
import pt.mleiria.mlalgo.distance.DistanceMetric;

/**
 *
 * @author manuel
 */
public class DistanceTask implements Runnable {

    /**
     * Array of distances
     */
    private final Distance[] distances;
    /**
     * Indexes that determines the examples of the train data this task will
     * process
     */
    private final int startIndex;
    
    private final int endIndex;
    /**
     * Example of the test data we want to classify
     */
    private final Double[] example;
    /**
     * Data set with the train data examples
     */
    private final Double[][] featuresX;
    /**
     * Synchronization mechanism to control the end of the task
     */
    private final CountDownLatch endControler;
    
    private final DistanceMetric<Double[], Double[], Double> distanceMetric;

    /**
     * Constructor of the class. Initializes all the internal data
     *
     * @param distances Array of distances
     * @param startIndex Start index that determines the examples of the train
     * data this task will process
     * @param endIndex End index that determines the examples of the train data
     * this task will process
     * @param featuresX
     * @param example Example of the test data we want to classify
     * @param endControler Synchronization mechanism to control the end of the
     * task
     * @param distanceMetric
     */
    public DistanceTask(final Distance[] distances, final int startIndex, final int endIndex,
            final Double[][] featuresX, final Double[] example, final CountDownLatch endControler, 
            final DistanceMetric<Double[], Double[], Double> distanceMetric) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.featuresX = featuresX;
        this.example = example;
        this.endControler = endControler;
        this.distanceMetric = distanceMetric;
    }

    @Override
    public void run() {
        for (int index = startIndex; index < endIndex; index++) {
            final Double[] localExample = featuresX[index];
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(
                    distanceMetric.calculate(localExample, example));
        }
        endControler.countDown();
    }

}
