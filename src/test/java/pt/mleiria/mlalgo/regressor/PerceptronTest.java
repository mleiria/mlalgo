/**
 *
 */
package pt.mleiria.mlalgo.regressor;

import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.math3.distribution.NormalDistribution;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.utils.Pair;
import pt.mleiria.mlalgo.utils.VUtils;
import pt.mleiria.regressor.linearmodel.Perceptron;
import pt.mleiria.syntheticdata.DataFactory;

/**
 * @author manuel
 *
 */
public class PerceptronTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(PerceptronTest.class.getName());

    private Double[][] xTrain;
    private Double[] yLabelTrain;

    private Double[][] xTest;
    private Double[] yLabelTest;

    @Override
    protected void setUp() throws Exception {
        int numRows = 1000;
        int numCols = 2;
        NormalDistribution nd1 = new NormalDistribution(-2.0, 1.0);
        NormalDistribution nd2 = new NormalDistribution(2.0, 1.0);
        Pair<Double[][], Double[]> dataGen = DataFactory.generateTwoClasses(nd1, nd2, numRows, numCols);

        xTrain = dataGen.getX();
        yLabelTrain = dataGen.getY();

        dataGen = DataFactory.generateTwoClasses(nd1, nd2, 200, 2);

        xTest = dataGen.getX();
        yLabelTest = dataGen.getY();
	/*
	VUtils<Double> vu = new VUtils<>();
	System.out.println(vu.showContents(xTest));
	System.out.println(Arrays.toString(yLabelTest));
	*/
    }

    /**
     *
     */
    public void testGenerateTwoClasses() {
        assertEquals(1000, xTrain.length);
        assertEquals(1000, yLabelTrain.length);
    }
	/*
    public void testPerceptron() {
        Perceptron perceptron = new Perceptron();
        Estimator estimator = perceptron.fit(xTrain, yLabelTrain);
        Double score = estimator.score(xTest, yLabelTest);
        LOG.info("Perceptron Score:" + score);
        assertEquals(true, score > 0.5);
    }
*/
}
