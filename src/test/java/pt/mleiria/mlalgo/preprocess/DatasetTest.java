/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import static pt.mleiria.mlalgo.conf.DatasetsLocation.DATA_SETS_DIR;

import java.util.Arrays;
import java.util.logging.Logger;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.dataset.Dataset;
import pt.mleiria.mlalgo.dataset.DatasetBuilder;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class DatasetTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(DatasetTest.class.getName());

    private final String dataFileIris = DATA_SETS_DIR + "/iris.data";
    private final String dataFileProstate = DATA_SETS_DIR + "/prostate.data";
    private final String dataFile100MetrosOlymp = DATA_SETS_DIR + "/100MetrosOlymp.csv";
    private final String dataFileMnistTrain = DATA_SETS_DIR + "/mnist/mnist_test.csv";
    

    public void testDatasetOlympics() {
        final DatasetBuilder dsb = new DatasetBuilder(dataFile100MetrosOlymp);
        dsb.setHasRowHeader(false);
        dsb.setIsLabelConversion(false);
        dsb.setSeparator(",");
        final Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        assertEquals(27, ds.labelsY.length);
        assertEquals(27, ds.featuresX.length);
        assertEquals(1900.0, ds.featuresX[1][0]);
    }
    
    public void testDatasetIrisLabelHolder() {
        final DatasetBuilder dsb = new DatasetBuilder(dataFileIris);
        dsb.setHasRowHeader(false);
        dsb.setIsLabelConversion(true);
        dsb.setSeparator(",");
        final Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        LOG.info(ds.getLabelHolder().toString());
        LOG.info(Arrays.toString(ds.getHeader()));
        assertEquals(3, ds.getLabelHolder().size());
        assertEquals(150, ds.labelsY.length);
        assertEquals(150, ds.featuresX.length);
    }
    
    public void testDatasetProstate() {
        final DatasetBuilder dsb = new DatasetBuilder(dataFileProstate);
        dsb.setHasRowHeader(true);
        dsb.setIsLabelConversion(false);
        dsb.setSeparator("\t");
        final Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        LOG.info(Arrays.toString(ds.getHeader()));
        assertEquals("lcavol", ds.getHeader()[0]);
        assertEquals(97, ds.labelsY.length);
        assertEquals(97, ds.featuresX.length);
        LOG.info("Prostate y data: " + Arrays.toString(ds.labelsY));
    }
    
    public void testDatasetMninst() {
	final DatasetBuilder dsb = new DatasetBuilder(dataFileMnistTrain);
        dsb.setHasRowHeader(true);
        dsb.setIsLabelConversion(false);
        dsb.setIsLabelInBeginning(true);
        final Dataset ds = dsb.createDataSet();
        ds.loadDataset();
        //LOG.info(Arrays.toString(ds.getHeader()));
        assertEquals("1x1", ds.getHeader()[0]);
        assertEquals("label", ds.getHeader()[ds.getHeader().length - 1]);
        //LOG.info("MNIST y data: " + Arrays.toString(ds.labelsY));
    }
    
    
    

}
