/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.dataset.Dataset;
import pt.mleiria.mlalgo.dataset.DatasetBuilder;
import pt.mleiria.mlalgo.utils.Arrays1D;
import pt.mleiria.mlalgo.utils.Arrays2D;
import pt.mleiria.mlalgo.utils.Tuple2;
import pt.mleiria.mlalgo.utils.VUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class Arrays2DTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(Arrays2DTest.class.getName());

    public void testTrainSplit() {
        final String dataFileIris = "/home/manuel/tools/adalineProcesses/mlearning/knn/iris.data";
        final DatasetBuilder dsb = new DatasetBuilder(dataFileIris);
        dsb.setIsLabelConversion(true);
        dsb.setSeparator(",");
        final Dataset ds = dsb.createDataSet();

        ds.loadDataset();
        final Double[][] x = ds.featuresX;
        final Double[] y = ds.labelsY;
        final VUtils<Number> v = new VUtils<>();
        assertEquals(150, x.length);
        final List<Tuple2<Double[][], Double[]>> splitter = Arrays2D.trainTestSplit(x, y, 0.5, true);
        final Double[][] trainX = splitter.get(0).getX();
        final Double[][] testX = splitter.get(1).getX();
        final Double[] trainY = splitter.get(0).getY();
        final Double[] testY = splitter.get(1).getY();

        //LOG.info(v.showContents(testX));
        //LOG.info(v.showContents(testY));
        assertEquals(75, trainX.length);
        assertEquals(75, trainY.length);
        assertEquals(75, testX.length);
        assertEquals(75, testY.length);
    }

    public void testKfold() {
        final String dataFileIris = "/home/manuel/tools/adalineProcesses/mlearning/knn/iris.data";
        final DatasetBuilder dsb = new DatasetBuilder(dataFileIris);
        dsb.setIsLabelConversion(true);
        dsb.setSeparator(",");
        final Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        final Double[][] x = ds.featuresX;
        final Double[] y = ds.labelsY;
        final VUtils<Number> v = new VUtils<>();
        assertEquals(150, x.length);
        final int cv = 7;
        final List<Tuple2<Double[][], Double[]>> lst = Arrays2D.kFold(x, y, cv, true);
        /*
        lst.stream()
           .forEach(elem -> LOG.info(v.showContents(elem.getX())));
        lst.stream()
           .forEach(elem -> LOG.info(v.showContents(elem.getY())));
           */
        assertEquals(cv, lst.size());
    }

    public void testGenMatrix() {
        final Double[][] matrix = Arrays2D.genMatrix(3, 2);
        assertEquals(0., matrix[0][0]);
        assertEquals(1., matrix[0][1]);
        assertEquals(2., matrix[1][0]);
        assertEquals(3., matrix[1][1]);
        assertEquals(4., matrix[2][0]);
        assertEquals(5., matrix[2][1]);
    }

    public static void testMerge() {

        final Double[][] x1 = Arrays2D.genMatrix(4, 5);
        final Double[] y1 = Arrays1D.genVector(0, 4);

        final Double[][] x2 = Arrays2D.genMatrix(16, 5);
        final Double[] y2 = Arrays1D.genVector(0, 16);

        final List<Tuple2<Double[][], Double[]>> lst = new ArrayList<>();
        lst.add(new Tuple2<>(x1, y1));
        lst.add(new Tuple2<>(x2, y2));
        final Tuple2<Double[][], Double[]> res = Arrays2D.merge(lst, 20, 5);
        LOG.info(new VUtils<Double>().showContents(res.getX()));
        LOG.info(new VUtils<Double>().showContents(res.getY()));
        assertEquals(res.getX().length, 20);
        assertEquals(res.getY().length, 20);
        assertEquals(res.getX()[0].length, 5);
    }

    public void testGenMatrixFromIndexes() {
        final Double[][] matrix = Arrays2D.genMatrix(3, 2);
        final Double[][] m1 = Arrays2D.genMatrixFromIndexes(matrix, new int[]{0, 2});
        LOG.info(new VUtils<Double>().showContents(m1));
        assertEquals(2, m1.length);
        assertEquals(0., m1[0][0]);
        assertEquals(4., m1[1][0]);
    }

    public void testHadamardProduct() {
        final double[] x = new double[]{1, 2, 3};
        final double[] y = new double[]{2, 3, 4};
        final double[] res = Arrays2D.hadamardProduct(x, y);
        assertEquals(2., res[0]);
        assertEquals(6., res[1]);
        assertEquals(12., res[2]);
    }

    public void testOper() {
        final Double[] x = new Double[]{1., 2., 3.};
        final Double[] y = new Double[]{2., 3., 4.};
        Double[] res = Arrays1D.oper(x, y, (a, b) -> a * b);
        assertEquals(2., res[0]);
        assertEquals(6., res[1]);
        assertEquals(12., res[2]);
        res = Arrays1D.oper(x, y, (a, b) -> a - b);
        assertEquals(-1., res[0]);
        assertEquals(-1., res[1]);
        assertEquals(-1., res[2]);
        res = Arrays1D.oper(x, y, (a, b) -> a + b);
        assertEquals(3., res[0]);
        assertEquals(5., res[1]);
        assertEquals(7., res[2]);
        final Double[] xx = new Double[]{4., 2., 6.};
        res = Arrays1D.oper(xx, elem -> elem / 2.);
        assertEquals(2., res[0]);
        assertEquals(1., res[1]);
        assertEquals(3., res[2]);
    }

    public void testDot() {
        final Double[][] y = Arrays2D.genMatrix(3, 2, 1);
        LOG.info(new VUtils<Double>().showContents(y));
        final double[] x = new double[]{7., 8., 9.};
        final double[] res = Arrays2D.dot(x, Arrays2D.copyToPrimitive(y));
        assertEquals(76., res[0]);
        assertEquals(3, res.length);
    }
}
