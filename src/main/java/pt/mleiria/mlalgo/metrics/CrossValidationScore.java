/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.metrics;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.utils.Arrays2D;
import pt.mleiria.mlalgo.utils.Tuple2;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.sqrt;

/**
 * The training set is split into cv smaller sets
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class CrossValidationScore implements Score<Double[][], Double[], Double> {

    private final Estimator estimator;
    private final Integer cv;
    private final SummaryStatistics sm;
    private final boolean isShuffle;

    /**
     * @param estimator
     * @param trainX
     * @param trainY
     * @param cv        defaults to 5
     * @param isShuffle defaults to false
     */
    public CrossValidationScore(final Estimator estimator, final Optional<Integer> cv,
                                final Optional<Boolean> isShuffle) {
        this.estimator = estimator;
        this.cv = cv.orElse(5);
        this.isShuffle = isShuffle.orElse(false);
        sm = new SummaryStatistics();
    }

    /**
     *
     */
    @Override
    public Double score(final Double[][] trainX, final Double[] trainY) {

        final List<Tuple2<Double[][], Double[]>> lst = Arrays2D.kFold(trainX, trainY, cv, isShuffle);
        final int size = lst.size();
        for (int i = 0; i < size; i++) {
            final Tuple2<Double[][], Double[]> trainTuple2 = lst.remove(i);
            final Double[][] trainXFold = trainTuple2.getX();
            final Double[] trainYFold = trainTuple2.getY();

            final int rows = trainX.length - trainXFold.length;
            final int cols = trainX[0].length;

            final Tuple2<Double[][], Double[]> testTuple2 = Arrays2D.merge(lst, rows, cols);
            estimator.fit(trainXFold, trainYFold);
            sm.addValue(estimator.score(testTuple2.getX(), testTuple2.getY()));
            lst.add(i, trainTuple2);
        }
        return sm.getMean();
    }

    /**
     * @return
     */
    public Double mean() {
        return sm.getMean();
    }

    /**
     * @return
     */
    public Double stdv() {
        return sqrt(sm.getPopulationVariance());
    }

}
