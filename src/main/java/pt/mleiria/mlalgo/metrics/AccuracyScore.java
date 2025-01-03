/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.metrics;

import pt.mleiria.mlalgo.utils.Validator;

import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class AccuracyScore implements Score<Double[], Double[], Double> {

    private static final Logger LOG = Logger.getLogger(AccuracyScore.class.getName());

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
        return normalized
                ? score(yPred, yTrue)
                : calculate(yPred, yTrue);
    }

    /**
     * @param yPred
     * @param yTrue
     * @return
     */
    private double calculate(Double[] yPred, Double[] yTrue) {
        Validator.validateThrowIfMatch(yPred.length, yTrue.length, (a,b) -> !Objects.equals(a, b),
                () -> "Sizes dont match yPred: " + yPred.length + " yTrue: " + yTrue.length);
        return
                IntStream.range(0, yPred.length)
                        .filter(i -> yTrue[i].doubleValue() == yPred[i].doubleValue())
                        .count();
    }
}
