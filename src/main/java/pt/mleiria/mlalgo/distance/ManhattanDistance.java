/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.distance;

/**
 * @author manuel
 */
public class ManhattanDistance implements DistanceMetric<Double[], Double[], Double>, MinkowskiDistance {

    @Override
    public Double calculate(Double[] x, Double[] y) {
        return calculate(x, y, 1);

    }

}
