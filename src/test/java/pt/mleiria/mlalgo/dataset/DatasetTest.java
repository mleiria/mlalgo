package pt.mleiria.mlalgo.dataset;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.metrics.AccuracyScore;
import pt.mleiria.mlalgo.preprocess.KNeighborsClassifierTest;
import pt.mleiria.mlalgo.utils.VUtils;

import java.util.logging.Logger;

public class DatasetTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(DatasetTest.class.getName());

    private VUtils<Number> v;

    @Override
    protected void setUp() throws Exception {
        v = new VUtils<>();
    }
    public void testDropColumns() {
        Dataset dataset = DatasetBuilder.create("src/test/resources/iris.csv")
                .setHasRowHeader(true)
                .setIsLabelConversion(true)
                .setIsLabelInBeginning(false)
                .createDataSet();
        dataset.loadDataset();
        LOG.info("Dataset before removal: " + v.showContents(dataset.featuresX));
        dataset.dropColumns(0, 2);
        LOG.info("Dataset after removal of col 0 and 2: " + v.showContents(dataset.featuresX));
        assertEquals(2, dataset.featuresX[0].length);
    }
}