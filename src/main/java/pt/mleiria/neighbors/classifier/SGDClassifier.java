/**
 * 
 */
package pt.mleiria.neighbors.classifier;

import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.functions.LogFunction;
import pt.mleiria.mlalgo.functions.SigmoidFunction;
import pt.mleiria.mlalgo.metrics.RegressorMixin;
import pt.mleiria.mlalgo.utils.Arrays1D;
import pt.mleiria.mlalgo.utils.Arrays2D;

/**
 * @author manuel
 *
 */
public class SGDClassifier implements Estimator {
    
    private static final Logger LOG = Logger.getLogger(SGDClassifier.class.getName());

    private Double[][] xData;
    private Double[] yLabel;
    private Double[] w;
    
    private Double[] lossSGD;
    
    private final double learningRate;
    private final int numIter;
    private final int batchSize;

    /**
     * Default Values
     * 
     * learningRate = 0.1;
     * numIter = 2000;
     * batchSize = 4;
     */
    public SGDClassifier() {
	this(0.1, 2000, 4);
    }
    /**
     * 
     * @param learningRate
     * @param numIter
     * @param batchSize
     */
    public SGDClassifier(double learningRate, int numIter, int batchSize) {
	this.learningRate = learningRate;
	this.numIter = numIter;
	this.batchSize = batchSize;
	lossSGD = new Double[numIter];
	Arrays.fill(lossSGD, 0.);
    }
    

    /* (non-Javadoc)
     * @see pt.mleiria.mlalgo.core.Estimator#fit(java.lang.Double[][], java.lang.Double[])
     */
    @Override
    public Estimator fit(final Double[][] xTrain, final Double[] yTrain) {
	this.xData = xTrain;
	this.yLabel = yTrain;
	Double[] weights = Arrays1D.rand(xTrain[0].length);
		
	for(int i = 0; i < numIter; i++) {
	    final int[] ind = Arrays1D.randomChoice(xTrain.length, batchSize);
	    lossSGD[i] = computeLoss(xTrain, yTrain, weights);
	    Double[] dW = computeGrad(
		    Arrays2D.genMatrixFromIndexes(xTrain, ind), 
		    Arrays1D.genVectorFromIndexes(yTrain, ind),  
		    weights);
	    //w = w - learningRate * dW
	    dW = Arrays1D.oper(dW, elem -> elem * learningRate);
	    weights = Arrays1D.oper(weights, dW, (x, y) -> x - y);
	}
	w = new Double[weights.length];
	System.arraycopy(weights, 0, w, 0, w.length);
	return this;
    }
    
    public Double[] computeGrad(final Double[][] xTrain, final Double[] yTrain, final Double[] w) {
	final int l = xTrain.length;
	final Double[] prob = probability(xTrain, w);
	final Double[] dZ = Arrays1D.oper(prob, yTrain, (a, b) -> a - b);
	final Double[] res = Arrays1D.dot(dZ, xTrain);
	return Arrays1D.oper(res, elem -> elem / (double)l);
    }
    /**
     * 
     * @param xTrain
     * @param yTrain
     * @param w
     * @return
     */
    public Double computeLoss(Double[][] xTrain, Double[] yTrain, Double[] w) {
	
	final int l = xTrain.length;
	final Double[] prob = probability(xTrain, w);
	final Double[] firstTerm = Arrays1D.oper(yTrain, new LogFunction().value(prob), (a, b) -> a * b);
	final Double[] yy = new Double[yTrain.length];
	System.arraycopy(yTrain, 0, yy, 0, yy.length);
	Arrays.setAll(yy, i -> 1. - yy[i]);
	Arrays.setAll(prob, i -> 1. - prob[i]);
	final Double[] secondTerm = Arrays1D.oper(yy, new LogFunction().value(prob), (a, b) -> a * b);
	final Double[] res = Arrays1D.oper(firstTerm, secondTerm, (a, b) -> a + b);
	return - Arrays2D.sum(res) / l;
    }
    /**
     * 
     * @param x
     * @param w
     * @return
     */
    private Double[] probability(Double[][] x, Double[] w) {
	final RealMatrix xrm = new Array2DRowRealMatrix(Arrays2D.copyToPrimitive(x));
	Double[] dot =  Arrays1D.box(xrm.operate(Arrays1D.unBox(w)));
	return new SigmoidFunction().value(dot);
	
    }

    /* (non-Javadoc)
     * @see pt.mleiria.mlalgo.core.Estimator#predict(java.lang.Double[][])
     */
    @Override
    public Double[] predict(Double[][] xSample) {
	return probability(xSample, w);
    }

    /* (non-Javadoc)
     * @see pt.mleiria.mlalgo.core.Estimator#score(java.lang.Double[][], java.lang.Double[])
     */
    @Override
    public Double score(Double[][] testX, Double[] trueLabelY) {
	final Double[] yPred = Arrays1D.round(predict(testX));
        return new RegressorMixin().score(yPred, trueLabelY);
    }

    /* (non-Javadoc)
     * @see pt.mleiria.mlalgo.core.Estimator#getX()
     */
    @Override
    public Double[][] getX() {
	return xData;
    }

    /* (non-Javadoc)
     * @see pt.mleiria.mlalgo.core.Estimator#getY()
     */
    @Override
    public Double[] getY() {
	return yLabel;
    }
    /**
     * 
     * @return
     */
    public Double[] getLossSGD() {
	return lossSGD;
    }
}
