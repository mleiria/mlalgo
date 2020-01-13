/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.metrics;

import junit.framework.TestCase;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class AccuracyScoreTest extends TestCase {

    public void testAccuracyScore() {
        final Score<Double[], Double[], Double> s = new AccuracyScore();
        final Double[] yPred = new Double[]{0., 2., 1., 3.};
        final Double[] yTrue = new Double[]{0., 1., 2., 3.};
        assertEquals(0.5, s.score(yPred, yTrue));

        assertEquals(2., ((AccuracyScore) s).score(yPred, yTrue, false));
    }

}
