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
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class Ridge implements Estimator {

    private double[] thetas;
    private Double[][] xData;
    private Double[] yLabel;
    private final double lambda;
    private final boolean fitIntercept;

    /**
     * 
     * @param lambda
     * @param fitIntercept if true => addOnes to X[][]
     */
    public Ridge(final double lambda, final boolean fitIntercept) {
	this.lambda = lambda;
	this.fitIntercept = fitIntercept;
    }
    /**
     * Defaults fitIntercept to true
     * @param lambda
     */
    public Ridge(final double lambda) {
	this.lambda = lambda;
	this.fitIntercept = true;
    }

    @Override
    public Estimator fit(Double[][] xTrain, Double[] yTrain) {
	
	this.xData = adjustForFitIntercept(xTrain);
	this.yLabel = yTrain;

	final RealMatrix x = new Array2DRowRealMatrix(Arrays2D.copyToPrimitive(xData));
	final RealMatrix xt = x.transpose();
	final RealMatrix xtx = xt.multiply(x);
	final RealMatrix i = new Array2DRowRealMatrix(
		Arrays2D.identity(xtx.getRowDimension(), xtx.getColumnDimension()));

	final RealMatrix rm = i.scalarMultiply(lambda).add(xtx);

	final RealMatrix xtxi = new LUDecomposition(rm).getSolver().getInverse();
	final RealMatrix xtxixt = xtxi.multiply(xt);
	thetas = xtxixt.operate(Arrays1D.unBox(yLabel));
	return this;
    }

    @Override
    public Double[] predict(Double[][] xSample) {
	xSample = adjustForFitIntercept(xSample);
	final Double[] res = new Double[xSample.length];
	for (int i = 0; i < xSample.length; i++) {
	    double x = 0;
	    for (int j = 0; j < xSample[0].length; j++) {
		x += thetas[j] * xSample[i][j];
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
    /**
     * 
     * @return
     */
    public double[] getThetas() {
	return thetas;
    }
    
    private Double[][] adjustForFitIntercept(final Double[][] x){
	if(fitIntercept) {
	    return Arrays2D.addOnes(x);
	}
	return x;
    }
    /**
     * 
     * @return
     */
    public double getLambda() {
	return lambda;
    }
}
