/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.regressor;


import junit.framework.TestCase;
import pt.mleiria.mlalgo.utils.VUtils;
import pt.mleiria.regressor.linearmodel.LeastSquares;

import java.util.logging.Logger;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class LinearRegressionTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(LinearRegressionTest.class.getName());

    private VUtils<Number> vu;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        vu = new VUtils<>();
    }


    /**
     *
     */
    public void testLinearRegression() {
        final LeastSquares lr = new LeastSquares();
        final Double[][] x = new Double[4][2];
        x[0][0] = 1.;
        x[0][1] = 0.;
        x[1][0] = 1.;
        x[1][1] = 1.;
        x[2][0] = 1.;
        x[2][1] = 2.;
        x[3][0] = 1.;
        x[3][1] = 3.;

        LOG.info(vu.showContents(x));

        final Double[] y = new Double[]{-1., 0.2, 0.9, 2.1};
        lr.fit(x, y);
        assertEquals(-0.9500000000000005, lr.getIntercept());
        assertEquals(1.0000000000000004, lr.getSlope());
    }

}
