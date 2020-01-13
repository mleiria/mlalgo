/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.utils.VUtils;

import java.util.logging.Logger;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class PolynomialFeaturesTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(PolynomialFeaturesTest.class.getName());

    final Double[][] x = new Double[3][2];

    private final VUtils<Number> vu = new VUtils<>();

    public void setUp() {
        x[0][0] = 0.;
        x[0][1] = 1.;
        x[1][0] = 2.;
        x[1][1] = 3.;
        x[2][0] = 4.;
        x[2][1] = 5.;

    }

    public void testPolynomialFeatures() {

        PolynomialFeatures poly = new PolynomialFeatures(4, true);
        final Double[][] res = poly.fitTransform(x);
        LOG.info(vu.showContents(res));
        assertEquals(15, res[0].length);

        poly = new PolynomialFeatures(2);
        final Double[][] res1 = poly.fitTransform(x);
        LOG.info(vu.showContents(res1));
        assertEquals(3, res1.length);
        assertEquals(6, res1[0].length);
        assertEquals(1., res1[0][0]);
        assertEquals(1., res1[1][0]);
        assertEquals(0., res1[0][1]);
        assertEquals(16., res1[2][3]);
        assertEquals(20., res1[2][4]);
        assertEquals(25., res1[2][5]);

    }

}
