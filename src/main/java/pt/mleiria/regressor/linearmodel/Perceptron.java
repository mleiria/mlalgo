/**
 * 
 */
package pt.mleiria.regressor.linearmodel;

import java.util.logging.Logger;

import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.metrics.RegressorMixin;

/**
 * @author manuel
 *
 */
public class Perceptron implements Estimator {
    
    private static final Logger LOG = Logger.getLogger(Perceptron.class.getName());

    private double[] w;
    private Double[][] xData;
    private Double[] yLabel;
    /*
     * Maximum training epochs
     */
    private int epochs = 2000;
    /*
     * Learning rate. Can be 1. in perceptron
     */
    private double learningRate = 1.;
    
       

    @Override
    public Estimator fit(Double[][] xTrain, Double[] yTrain) {
	this.xData = xTrain;
	this.yLabel = yTrain;
	this.w = new double[yTrain.length];
	int epoch = 0;
	while (true) {
	    int classified = 0;
	    for (int i = 0; i < xData.length; i++) {
		classified += train(xData[i], yLabel[i]);
	    }
	    //LOG.info("Epoch: " + epoch + "; Classified: " + classified);
	    if (classified == xData.length) {
		/*
		 * When all data classified correctly
		 */
		break;
	    }
	    epoch++;
	    if (epoch > epochs) {
		LOG.warning("Maximum Epochs Reached: " + epoch);
		break;
	    }
	}
	return this;
    }

    private int train(final Double[] xTrainRow, Double t) {
	int classified = 0;
	double c = 0;
	/*
	 * Check if the data is classified correctly
	 */
	for (int i = 0; i < xTrainRow.length; i++) {
	    /*
	     * Equation of properly classified data: c > 0
	     */
	    c += w[i] * xTrainRow[i] * t;
	}

	if (c > 0) {
	    classified = 1;
	} else {
	    for (int i = 0; i < xTrainRow.length; i++) {
		/*
		 * Gradient Descent
		 */
		w[i] += learningRate * xTrainRow[i] * t;
	    }
	}
	return classified;
    }

    @Override
    public Double[] predict(Double[][] xSample) {
	final Double[] res = new Double[xSample.length];
	for (int i = 0; i < xSample.length; i++) {
	    double preActivation = 0.;
	    for (int j = 0; j < xSample[0].length; j++) {
		preActivation += w[j] * xSample[i][j];
	    }
	    res[i] = preActivation;
	}
	return res;
    }

    @Override
    public Double score(Double[][] testX, Double[] trueLabelY) {
	final Double[] yPred = predict(testX);
        return new RegressorMixin().score(yPred, trueLabelY);
    }

    @Override
    public Double[][] getX() {
	return xData;
    }

    @Override
    public Double[] getY() {
	return yLabel;
    }

}
