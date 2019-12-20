/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.distance;

import static java.lang.Math.abs;

/**
 *
 * @author manuel
 */
public class ManhattanDistance implements DistanceMetric<Double[], Double[], Double> {

    @Override
    public Double calculate(Double[] x, Double[] y) {
        double res = 0.0;
        for (int i = 0; i < x.length; i++) {
            res += abs(x[i] - y[i]);
        }
        return res;
    }

}
