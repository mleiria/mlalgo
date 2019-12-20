/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;


import java.util.logging.Logger;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.core.Transformer;
import pt.mleiria.mlalgo.utils.VUtils;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class StandardScalerTest extends TestCase{
    
    private static final Logger LOG = Logger.getLogger(StandardScalerTest.class.getName());
    
    private final VUtils<Number> vu = new VUtils<>();

    Double[][] data = new Double[4][2];
    Transformer ss;
    
    @Override
    protected void setUp() throws Exception {
        data[0][0] = 0.;
        data[1][0] = 0.;
        data[2][0] = 1.;
        data[3][0] = 1.;
        data[0][1] = 0.;
        data[1][1] = 0.;
        data[2][1] = 1.;
        data[3][1] = 1.;
        ss = new StandardScaler();
    }
    
    public void testFit(){
        ss.fit(data);
        
        assertEquals(0.5, ss.getParams(0).getMean());
        
    }
    
    public void testTransform(){
        
        final Double[][] transformed = ss.fitTransform(data);
        
        LOG.info(vu.showContents(transformed));
        
        final Double[][] data1 = new Double[1][2];
        data1[0][0] = 2.;
        data1[0][1] = 2.;
        
        
        assertEquals(3.0, ss.transform(data1)[0][0]);
    }
}
