package pt.mleiria.mlalgo.distance;

import junit.framework.TestCase;

import java.util.logging.Logger;

public class ManhattanDistanceTest extends TestCase {
    private static final Logger LOG = Logger.getLogger(ManhattanDistanceTest.class.getName());

    public void testCalculate() {
        ManhattanDistance manhattanDistance = new ManhattanDistance();
        Double[] x = {3.0, 6.0, 9.0};
        Double[] y = {1.0, 0.0, 1.0};
        final double result = manhattanDistance.calculate(x, y);
        LOG.info("Manhattan Distance: " + result);
        assertEquals(16.0, result, 0.005);
    }
}