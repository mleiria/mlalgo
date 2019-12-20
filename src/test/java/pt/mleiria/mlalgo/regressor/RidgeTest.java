/**
 * 
 */
package pt.mleiria.mlalgo.regressor;

import java.util.logging.Logger;
import static pt.mleiria.mlalgo.utils.MathematicaUtils.round;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.utils.Arrays2D;
import pt.mleiria.mlalgo.utils.VUtils;
import pt.mleiria.regressor.linearmodel.Ridge;

/**
 * @author manuel
 *
 */
public class RidgeTest extends TestCase{
    
    private static final Logger LOG = Logger.getLogger(RidgeTest.class.getName());
    private VUtils<Number> vu;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        vu = new VUtils<>();
    }
    
    public void testRidge() {
	Double[][] x = new Double[3][2];
	x[0][0] = 0.;
	x[0][1] = 0.;
	x[1][0] = 0.;
	x[1][1] = 0.;
	x[2][0] = 1.;
	x[2][1] = 1.;
	Double[] y = new Double[] {0., 0.1, 1.};
	
	Ridge ridge = new Ridge(0.5, false);
	ridge.fit(x, y);
	double[] thetas = ridge.getThetas();
	assertEquals(0.4, thetas[0]);
	assertEquals(0.4, thetas[1]);
	assertEquals(.9214659685863875, ridge.score(x, y));

	ridge = new Ridge(.5, true);
	ridge.fit(x, y);
	LOG.info(VUtils.showArrayContents(ridge.getThetas()));
	assertEquals(0.1, round(ridge.getThetas()[0], 1));
	double score = ridge.score(x, y); 
	LOG.info(""+score);
	assertEquals(.9274734204645493, score);
    }

    

}
