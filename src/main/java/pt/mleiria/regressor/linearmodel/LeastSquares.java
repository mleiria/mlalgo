/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.regressor.linearmodel;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.metrics.RegressorMixin;
import pt.mleiria.mlalgo.utils.Arrays1D;
import pt.mleiria.mlalgo.utils.Arrays2D;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class LeastSquares implements Estimator {

    private double[] w;
    private Double[][] xData;
    private Double[] yLabel;


    @Override
    public Estimator fit(Double[][] xTrain, Double[] yTrain) {
        this.xData = xTrain;
        this.yLabel = yTrain;
        final RealMatrix x = new Array2DRowRealMatrix(Arrays2D.copyToPrimitive(xData));
        final RealMatrix xt = x.transpose();
        final RealMatrix xtx = xt.multiply(x);

        final RealMatrix xtxi = new LUDecomposition(xtx).getSolver().getInverse();
        final RealMatrix xtxixt = xtxi.multiply(xt);
        w = xtxixt.operate(Arrays1D.unBox(yLabel));
        return this;
    }

    @Override
    public Double[] predict(Double[][] xSample) {
        final Double[] res = new Double[xSample.length];
        for (int i = 0; i < xSample.length; i++) {
            double x = 0;
            for (int j = 0; j < xSample[0].length; j++) {
                x += w[j] * xSample[i][j];
            }
            res[i] = x;
        }
        return res;
    }

    @Override
    public Double score(Double[][] testX, Double[] yTrue) {
        final Double[] yPred = predict(testX);
        return new RegressorMixin().score(yPred, yTrue);
    }

    @Override
    public Double[][] getX() {
        return xData;
    }

    @Override
    public Double[] getY() {
        return yLabel;
    }

    public double getIntercept() {
        return w[0];
    }

    public double getSlope() {
        return w[1];
    }

    public double[] getCoefs() {
        return w;
    }
}
