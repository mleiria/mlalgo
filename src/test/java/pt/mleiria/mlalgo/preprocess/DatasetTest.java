/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.dataset.Dataset;
import pt.mleiria.mlalgo.dataset.DatasetBuilder;
import pt.mleiria.mlalgo.utils.ResourceFileLoader;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class DatasetTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(DatasetTest.class.getName());


    public void testDatasetIrisLabelHolder() {
        final String dataFileIris = ResourceFileLoader.getFilePath("iris.csv");
        final Dataset ds = DatasetBuilder.create(dataFileIris)
                .setHasRowHeader(true)
                .setIsLabelConversion(true)
                .setSeparator(",")
                .createDataSet();
        ds.loadDataset();
        LOG.info(ds.getLabelHolder().toString());
        LOG.info(Arrays.toString(ds.getHeader()));
        assertEquals(3, ds.getLabelHolder().size());
        assertEquals(150, ds.labelsY.length);
        assertEquals(150, ds.featuresX.length);
    }

    public void testDatasetProstate() {
        final String dataFileProstate = ResourceFileLoader.getFilePath("prostate.csv");

        final Dataset ds = DatasetBuilder.create(dataFileProstate)
                .setHasRowHeader(true)
                .setIsLabelConversion(false)
                .setSeparator(",")
                .createDataSet();
        ds.loadDataset();
        LOG.info(Arrays.toString(ds.getHeader()));
        assertEquals("lcavol", ds.getHeader()[0]);
        assertEquals(97, ds.labelsY.length);
        assertEquals(97, ds.featuresX.length);
        LOG.info("Prostate y data: " + Arrays.toString(ds.labelsY));
    }

    public void testDatasetMninst() {
        final String dataFileMnistTrain = ResourceFileLoader.getFilePath("mnist_test.csv");
        final Dataset ds = DatasetBuilder.create(dataFileMnistTrain)
                .setHasRowHeader(true)
                .setIsLabelConversion(false)
                .setIsLabelInBeginning(true)
                .createDataSet();
        ds.loadDataset();
        //LOG.info(Arrays.toString(ds.getHeader()));
        assertEquals("1x1", ds.getHeader()[0]);
        assertEquals("label", ds.getHeader()[ds.getHeader().length - 1]);
        //LOG.info("MNIST y data: " + Arrays.toString(ds.labelsY));
    }


}
