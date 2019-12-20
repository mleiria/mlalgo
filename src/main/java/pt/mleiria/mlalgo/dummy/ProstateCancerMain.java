/**
 * 
 */
package pt.mleiria.mlalgo.dummy;

import static pt.mleiria.mlalgo.utils.Arrays1D.getColumn;
import static pt.mleiria.mlalgo.utils.Arrays1D.oper;
import static pt.mleiria.mlalgo.utils.Arrays2D.trainTestSplit;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pt.mleiria.mlalgo.conf.DatasetsLocation;
import pt.mleiria.mlalgo.dataset.Dataset;
import pt.mleiria.mlalgo.dataset.DatasetBuilder;
import pt.mleiria.mlalgo.preprocess.StandardScaler;
import pt.mleiria.mlalgo.stats.AttributeStatistics;
import pt.mleiria.mlalgo.utils.Pair;
import pt.mleiria.mlalgo.utils.VUtils;
import pt.mleiria.regressor.linearmodel.Ridge;

/**
 * @author manuel
 *
 */
public class ProstateCancerMain {
    private static final Logger LOG = Logger.getLogger(ProstateCancerMain.class.getName());
    private static final VUtils<Double> vu = new VUtils<>();
    private static final String PROSTATE_CANCER_DIR = DatasetsLocation.DATA_SETS_DIR + "prostate.data";

    /**
     * @param args
     */
    public static void main(String[] args) {

	final List<Pair<Double[][], Double[]>> lst = loadData();
	Double[][] xTrain = lst.get(0).getX();
	Double[] yTrain = lst.get(0).getY();
	Double[][] xTest = lst.get(1).getX();
	Double[] yTest = lst.get(1).getY();
	LOG.log(Level.INFO, "trainX rows size: {0}", xTrain.length);
	
	assert 50 == xTrain.length;

	final StandardScaler scTrain = new StandardScaler();
	final StandardScaler scTest = new StandardScaler();
	xTrain = scTrain.fitTransform(xTrain);
	xTest = scTest.fitTransform(xTest);
	LOG.info("" + scTrain.getParams(0).getMean());
	assert .72360667292 == scTrain.getParams(0).getMean();
	assert 1.0265408398715585 == Math.sqrt(scTrain.getParams(0).getPopulationVariance());
	assert -1.2697255845009394 == xTrain[0][0];

	final Double[] xBar = new Double[xTrain[0].length];
	final Double[] xStd = new Double[xTrain[0].length];
	for (int i = 0; i < xBar.length; i++) {
	    xBar[i] = scTrain.getParams(0).getMean();
	    xStd[i] = Math.sqrt(scTrain.getParams(0).getPopulationVariance());
	}
	assert 8 == xBar.length;

	final Double yBar = new AttributeStatistics(yTrain).getMean();
	final Double yBarTest = new AttributeStatistics(yTest).getMean();
	assert 1.6250303940000002 == yBar;

	yTrain = oper(yTrain, elem -> elem - yBar);
	yTest = oper(yTest, elem -> elem - yBarTest);
	assert -2.055813294 == yTrain[0];
	assert .966486006 == yTrain[yTrain.length - 1];

	final double[] lambdas = new double[] { 0.01, 0.1, 1.0, 10., 100., 1000., 10000. };
	
	Double[][] ridgeThetas = new Double[xTrain[0].length][lambdas.length];
	List<Pair<Double, Double>> trainScores = new ArrayList<>();
	List<Pair<Double, Double>> testScores = new ArrayList<>();
	for (int i = 0; i < lambdas.length; i++) {
	    final Ridge ridge = new Ridge(lambdas[i], false);
	    ridge.fit(xTrain, yTrain);
	    trainScores.add(new Pair<>(lambdas[i], ridge.score(xTrain, yTrain)));
	    testScores.add(new Pair<>(lambdas[i], ridge.score(xTest, yTest)));
	    final double[] theta = ridge.getThetas();
	    for(int j = 0; j < theta.length; j++) {
		ridgeThetas[j][i] = theta[j];
	    }
	}
	LOG.info(vu.showContents(ridgeThetas));
	LOG.info(trainScores.toString());
	LOG.info(testScores.toString());
	/*
	 * For each value of theta, compute the train and test error
	 */
	
    }

    /**
     * 
     * @return
     */
    private static List<Pair<Double[][], Double[]>> loadData() {
	final DatasetBuilder db = new DatasetBuilder(PROSTATE_CANCER_DIR);
	db.setHasRowHeader(true);
	db.setSeparator("\t");
	final Dataset ds = db.createDataSet();
	ds.loadDataset();
	LOG.log(Level.INFO, "Data size (rows, cols): ({0} , {1})",
		new Object[] { ds.featuresX.length, ds.featuresX[0].length });
	return trainTestSplit(ds.featuresX, ds.labelsY, 50, false);
    }

    /**
     * 
     * @param x
     * @return
     */
    private static AttributeStatistics[] getStats(final Double[][] x) {
	final int len = x[0].length;
	final AttributeStatistics[] attrs = new AttributeStatistics[len];
	for (int i = 0; i < len; i++) {
	    final AttributeStatistics attr = new AttributeStatistics(getColumn(x, i));
	    attrs[i] = attr;
	}
	return attrs;
    }

}
