/**
 * 
 */
package pt.mleiria.mlalgo.functions;

import junit.framework.TestCase;

/**
 * @author manuel
 *
 */
public class ExpFunctionTest extends TestCase{
    

    public void testExpFunction() {
	final Double[] z = new Double[] {1., 2.,3.,4.,5.,6.,7.,8.,9.};
	final OneVarFunction<Double[], Double[]> exp = new SigmoidFunction();
	final Double[] res = exp.value(z);
	
	assertEquals(0.7310585786300049, res[0]);
	assertEquals(0.9998766054240137, res[8]);
	assertEquals(z.length, res.length);
	
    }
}
