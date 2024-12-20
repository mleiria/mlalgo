/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.distance;

import java.util.stream.IntStream;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class EuclideanDistance implements DistanceMetric<Double[], Double[], Double> {

    //private static final Logger LOG = Logger.getLogger(EuclideanDistance.class.getName());

    @Override
    public Double calculate(Double[] x, Double[] y) {
        return Math.sqrt(
                IntStream.range(0, x.length)
                        .mapToDouble(i -> Math.pow(x[i] - y[i], 2))
                        .sum()
        );
    }


}
