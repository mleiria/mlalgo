/**
 * 
 */
package pt.mleiria.syntheticdata;

import org.apache.commons.math3.distribution.NormalDistribution;

import pt.mleiria.mlalgo.utils.Pair;

/**
 * @author manuel
 *
 */
public final class DataFactory {

    public static Pair<Double[][], Double[]> generateTwoClasses(final NormalDistribution nd1,
	    final NormalDistribution nd2, final int numRows, final int numCols) {

	final Double[][] data = new Double[numRows][numCols];
	final Double[] target = new Double[numRows];

	// data set in class 1
	for (int i = 0; i < (numRows / 2); i++) {
	    data[i][0] = nd1.sample();
	    data[i][1] = nd2.sample();
	    target[i] = 1.;
	}

	// data set in class 2
	for (int i = numRows / 2; i < numRows; i++) {
	    data[i][0] = nd2.sample();
	    data[i][1] = nd1.sample();
	    target[i] = -1.;
	}
	return new Pair<>(data, target);
    }
    
    

}
