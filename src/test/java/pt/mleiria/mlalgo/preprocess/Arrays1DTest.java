package pt.mleiria.mlalgo.preprocess;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.utils.Arrays1D;

import java.util.Arrays;
import java.util.logging.Logger;

public class Arrays1DTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(Arrays1DTest.class.getName());

    public void testRandomChoice() {
        final Double[] values = new Double[]{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};
        int[] indexes = Arrays1D.randomChoice(values.length, 4);
        LOG.info(Arrays.toString(indexes));
        indexes = Arrays1D.randomChoice(values.length, 5);
        LOG.info(Arrays.toString(indexes));
        indexes = Arrays1D.randomChoice(values.length, 6);
        LOG.info(Arrays.toString(indexes));
        indexes = Arrays1D.randomChoice(values.length, 3);
        LOG.info(Arrays.toString(indexes));
        assertEquals(3, indexes.length);
    }

    public void testFilter() {
        final Double[] values = new Double[]{1., 2., 3., 4., 1., 6., 1., 8., 9., 10.};

        final Double[] res = Arrays1D.filter(values, elem -> elem == 1.);
        assertEquals(values.length, res.length);
        assertEquals(1., res[0]);
        assertEquals(0., res[1]);
        assertEquals(1., res[4]);
        assertEquals(0., res[5]);
        assertEquals(1., res[6]);
    }

}
