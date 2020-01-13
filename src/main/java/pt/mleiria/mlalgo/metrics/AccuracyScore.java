/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.metrics;

import java.util.Objects;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class AccuracyScore implements Score<Double[], Double[], Double> {

    /**
     * @param yPred
     * @param yTrue
     * @return
     */
    @Override
    public Double score(Double[] yPred, Double[] yTrue) {
        return calculate(yPred, yTrue) / yPred.length;
    }

    /**
     * @param yPred
     * @param yTrue
     * @param normalized
     * @return
     */
    public double score(Double[] yPred, Double[] yTrue, boolean normalized) {
        if (normalized) {
            return score(yPred, yTrue);
        }
        return calculate(yPred, yTrue);
    }

    /**
     * @param yPred
     * @param yTrue
     * @return
     */
    private double calculate(Double[] yPred, Double[] yTrue) {
        final int size = yPred.length;
        if (size != yTrue.length) {
            throw new IllegalArgumentException("Sizes dont match.");
        }
        double counter = 0.0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(yTrue[i], yPred[i])) {
                counter++;
            }
        }
        return counter;
    }


}
