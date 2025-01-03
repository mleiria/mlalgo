package pt.mleiria.mlalgo.utils;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.logging.Logger;

public class Arrays1DTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(Arrays1DTest.class.getName());

    public void testConvertToInt() {
        Double[] x = new Double[]{1.0, 2.0, 3.0};
        Integer[] res = Arrays1D.convertToInt(x);
        assertEquals(1, res[0].intValue());
    }

    public void testGetColumn() {
        Double[][] x = new Double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        Double[] res = Arrays1D.getColumn(x, 1);
        assertEquals(2.0, res[0]);
        assertEquals(5.0, res[1]);
        assertEquals(2, res.length);
    }

    public void testRand() {
        final Double[] values = Arrays1D.rand(10);
        assertEquals(10, values.length);
    }

    public void testRandomChoice() {
        final Double[] values = new Double[]{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};
        Integer[] indexes = Arrays1D.randomChoice(values.length, 4);
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

    public void _testRandPerformance() {
        for (int i = 10000; i < 60000000; i += 500000) {
            long start = System.currentTimeMillis();
            Arrays1D.rand(i);
            long end = System.currentTimeMillis();
            if (i > 30000000) System.out.println("Parallel in action");
            System.out.println("Time for " + i + " elements: " + (end - start) + " ms");
        }
    }
}