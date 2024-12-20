/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.distance;

import java.util.stream.IntStream;

import static java.lang.Math.abs;

/**
 * @author manuel
 */
public class ManhattanDistance implements DistanceMetric<Double[], Double[], Double> {

    @Override
    public Double calculate(Double[] x, Double[] y) {
        return
                IntStream.range(0, x.length)
                        .mapToDouble(i -> abs(x[i] - y[i]))
                        .sum();
    }

}
