package pt.mleiria.mlalgo.distance;

import junit.framework.TestCase;

import java.util.logging.Logger;

public class EuclideanDistanceTest extends TestCase {
    private static final Logger LOG = Logger.getLogger(EuclideanDistanceTest.class.getName());

    public void testCalculate() {
        EuclideanDistance euclideanDistance = new EuclideanDistance();
        Double[] x = {3.0, 6.0, 9.0};
        Double[] y = {1.0, 0.0, 1.0};
        final double result = euclideanDistance.calculate(x, y);
        LOG.info("Euclidean Distance: " + result);
        assertEquals(10.198, result, 0.005);
    }
}