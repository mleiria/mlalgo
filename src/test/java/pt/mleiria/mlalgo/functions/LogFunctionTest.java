package pt.mleiria.mlalgo.functions;

import junit.framework.TestCase;

public class LogFunctionTest  extends TestCase{
    
    public void testLogFunction() {
	Double[] z = new Double[] {1., 3.25, 5.5, 7.75, 10.};
	OneVarFunction<Double[], Double[]> log = new LogFunction();
	Double[] res = log.value(z);
	
	assertEquals(0., res[0]);
	assertEquals(1.1786549963416462, res[1]);
	assertEquals(z.length, res.length);
	
    }

}
