package pt.mleiria.mlalgo.distance;

import junit.framework.TestCase;

import java.util.logging.Logger;

public class MinkowskiTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(MinkowskiTest.class.getName());

    public void testCalculate() {
        MinkowskiDistance minkowskiDistance = new MinkowskiDistance() {
            @Override
            public Double calculate(Double[] x, Double[] y, int p) {
                    return MinkowskiDistance.super.calculate(x, y, p);
            }
        };
        Double[] x = {3.0, 6.0, 9.0};
        Double[] y = {1.0, 0.0, 1.0};
        final double result = minkowskiDistance.calculate(x, y, 3);
        LOG.info("Minkowski Distance: " + result);
        assertEquals(9.0287, result, 0.005);
    }
}