/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.distance;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class EuclideanDistance implements DistanceMetric<Double[], Double[], Double>, MinkowskiDistance {

    @Override
    public Double calculate(Double[] x, Double[] y) {
        return calculate(x, y, 2);
    }


}
