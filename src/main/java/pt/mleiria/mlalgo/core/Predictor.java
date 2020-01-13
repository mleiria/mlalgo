/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.core;

/**
 * For making predictions
 *
 * @author manuel
 */
public interface Predictor extends Estimator {

    Predictor fit(double[][] xTrain, double[] yTrain);

    double[] predict(double[][] xTest);

    double score(double[][] xTest, double[] yTest);
}
