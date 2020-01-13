/**
 *
 */
package pt.mleiria.mlalgo.classifier;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.conf.DatasetsLocation;
import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.dataset.Dataset;
import pt.mleiria.mlalgo.dataset.DatasetBuilder;
import pt.mleiria.mlalgo.utils.Arrays1D;
import pt.mleiria.mlalgo.utils.Arrays2D;
import pt.mleiria.mlalgo.utils.Tuple2;
import pt.mleiria.mlalgo.utils.VUtils;
import pt.mleiria.neighbors.classifier.SGDClassifier;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author manuel
 *
 */
public class SGDClassifierTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(SGDClassifierTest.class.getName());
    private Double[][] xTrain;
    private Double[] yTrain;
    private Double[] w;

    public void setUp() {
        xTrain = new Double[4][4];
        xTrain[0][0] = 1.;
        xTrain[0][1] = 2.;
        xTrain[0][2] = 3.;
        xTrain[0][3] = 4.;
        xTrain[1][0] = 5.;
        xTrain[1][1] = 6.;
        xTrain[1][2] = 7.;
        xTrain[1][3] = 8.;
        xTrain[2][0] = 9.;
        xTrain[2][1] = 10.;
        xTrain[2][2] = 11.;
        xTrain[2][3] = 12.;
        xTrain[3][0] = 13.;
        xTrain[3][1] = 14.;
        xTrain[3][2] = 15.;
        xTrain[3][3] = 16.;

        yTrain = new Double[]{1., 2., 3., 4.};
        w = new Double[]{-1., -0.33333333, 0.33333333, 1.};
    }

    public void testComputeLoss() {
        final VUtils<Double> vu = new VUtils<>();
        LOG.info(vu.showContents(xTrain));
        LOG.info(vu.showContents(yTrain));
        LOG.info(vu.showContents(w));
        assertEquals(xTrain.length, w.length);

        SGDClassifier e = new SGDClassifier();
        Double expected = -4.9649475788057185;
        assertEquals(expected, e.computeLoss(xTrain, yTrain, w));
    }

    public void testComputeGrad() {
        SGDClassifier e = new SGDClassifier();
        Double[] res = e.computeGrad(xTrain, yTrain, w);
        LOG.info(new VUtils<>().showContents(res));
        assertEquals(-15.741116370439514, res[0]);
        //-15.74111637, -17.27556157, -18.81000676, -20.34445196
    }


    public void testPredictIris() {
        final String dataFileIris = DatasetsLocation.DATA_SETS_DIR + "iris.data";
        final DatasetBuilder dsb = new DatasetBuilder(dataFileIris);
        dsb.setIsLabelConversion(true);
        dsb.setSeparator(",");
        final Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        final List<Tuple2<Double[][], Double[]>> splitter = Arrays2D.trainTestSplit(ds.featuresX, ds.labelsY, 0.66, true);
        final Double[][] trainX = splitter.get(0).getX();
        final Double[][] testX = splitter.get(1).getX();
        final Double[] trainY = splitter.get(0).getY();
        final Double[] testY = splitter.get(1).getY();

        final Double[] trainYFiltered = Arrays1D.filter(trainY, elem -> elem == 1.);
        final Double[] testYFiltered = Arrays1D.filter(testY, elem -> elem == 1.);


        final Estimator estimator = new SGDClassifier(0.001, 2000, 8);
        estimator.fit(trainX, trainYFiltered);

        final Double[] predict = estimator.predict(testX);
        LOG.info(Arrays.toString(Arrays1D.round(predict)));
        LOG.info(Arrays.toString(testYFiltered));
        assertEquals(testY.length, predict.length);
        LOG.info("SGD Score:" + estimator.score(testX, testYFiltered));

    }

    public void testBanknoteAuthentication() {
        final String dataFileBankNoteAuth = DatasetsLocation.DATA_SETS_DIR + "data_banknote_authentication.csv";
        final DatasetBuilder dsb = new DatasetBuilder(dataFileBankNoteAuth);
        final Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        final List<Tuple2<Double[][], Double[]>> splitter = Arrays2D.trainTestSplit(ds.featuresX, ds.labelsY, 0.66, true);
        final Double[][] trainX = splitter.get(0).getX();
        final Double[][] testX = splitter.get(1).getX();
        final Double[] trainY = splitter.get(0).getY();
        final Double[] testY = splitter.get(1).getY();


        final Estimator estimator = new SGDClassifier(0.001, 2000, 4);
        estimator.fit(trainX, trainY);

        final Double[] predict = estimator.predict(testX);
        LOG.info(Arrays.toString(Arrays1D.round(predict)));
        LOG.info(Arrays.toString(testY));
        assertEquals(testY.length, predict.length);
        LOG.info("SGD Score:" + estimator.score(testX, testY));
    }
}
