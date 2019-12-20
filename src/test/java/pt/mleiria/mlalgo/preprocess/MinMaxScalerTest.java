/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.core.Transformer;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class MinMaxScalerTest extends TestCase{

    Double[][] data = new Double[4][2];
    Transformer t;
    
    @Override
    protected void setUp() throws Exception {
        data[0][0] = -1.;
        data[1][0] = -0.5;
        data[2][0] = 0.;
        data[3][0] = 1.;
        data[0][1] = 2.;
        data[1][1] = 6.;
        data[2][1] = 10.;
        data[3][1] = 18.;
        t = new MinMaxScaler();
    }
    
    
    
    public void testFit(){
        t.fit(data);
        assertEquals(1.,t.getParams(0).getMax());
        assertEquals(18.,t.getParams(1).getMax());
    }
    
    public void testTransform(){
        final Double[][] transformed = t.fitTransform(data);
        
        assertEquals(0., transformed[0][0]);
        assertEquals(.25, transformed[1][0]);
        assertEquals(.5, transformed[2][1]);
        assertEquals(1., transformed[3][1]);
    }
    
    public void testTransformCustomMinMax() {
	((MinMaxScaler)t).setFeaturesRange(10, 20);
	final Double[][] transformed = t.fitTransform(data);
	
	assertEquals(10., transformed[0][0]);
        assertEquals(12.5, transformed[1][0]);
        assertEquals(15., transformed[2][1]);
        assertEquals(20., transformed[3][1]);
	
    }
}
