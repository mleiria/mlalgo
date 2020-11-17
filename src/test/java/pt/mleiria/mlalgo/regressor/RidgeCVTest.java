/**
 *
 */
package pt.mleiria.mlalgo.regressor;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.conf.DatasetsLocation;
import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.dataset.Dataset;
import pt.mleiria.mlalgo.dataset.DatasetBuilder;
import pt.mleiria.mlalgo.utils.MathematicalUtils;
import pt.mleiria.regressor.linearmodel.RidgeCV;

import java.util.logging.Logger;

/**
 * @author manuel
 *
 */
public class RidgeCVTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(RidgeCVTest.class.getName());
    private static final String dataSetDiabetes = DatasetsLocation.DATA_SETS_DIR + "/diabetes.csv";

    public void testRidgeCVDiabetes() {
        final DatasetBuilder dsb = new DatasetBuilder(dataSetDiabetes);
        Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        Double[][] X = ds.featuresX;
        Double[] y = ds.labelsY;
        double[] lambdas = new double[]{0.001, 0.01, 0.1, 1.};
        Estimator ridgeCV = new RidgeCV(lambdas, 3);
        ridgeCV.fit(X, y);
        double bestLambda = ((RidgeCV) ridgeCV).getBestLambda();
        LOG.info("Best lambda:" + bestLambda);
        double score = ridgeCV.score(X, y);
        LOG.info("Score:" + score);
        assertEquals(0.01, bestLambda);
        assertEquals(0.5166, MathematicalUtils.round(score, 4));
    }

    public void testRidgeCV() {
        Double[][] X = new Double[3][2];
        X[0][0] = 0.;
        X[0][1] = 0.;
        X[1][0] = 0.;
        X[1][1] = 0.;
        X[2][0] = 1.;
        X[2][1] = 1.;
        Double[] y = new Double[]{0., .1, 1.};
        double[] lambdas = new double[]{0.1, 1., 10.0};
        Estimator ridgeCV = new RidgeCV(lambdas, 3);
        ridgeCV.fit(X, y);
        double bestLambda = ((RidgeCV) ridgeCV).getBestLambda();
        LOG.info("Best lambda:" + bestLambda);
        double score = ridgeCV.score(X, y);
        LOG.info("Score:" + score);
        assertEquals(0.1, bestLambda);
        assertEquals(0.9874, MathematicalUtils.round(score, 4));
    }

}
