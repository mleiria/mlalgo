/**
 *
 */
package pt.mleiria.syntheticdata;

import org.apache.commons.math3.distribution.NormalDistribution;
import pt.mleiria.mlalgo.utils.Tuple2;

/**
 * @author manuel
 *
 */
public final class DataFactory {

    public static Tuple2<Double[][], Double[]> generateTwoClasses(final NormalDistribution nd1,
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


        return new Tuple2<>(data, target);
    }

    public static Tuple2<Double[], Double[]> generateExponentialCurve(final int size){
        final Double[] x = new Double[size];
        final Double[] y = new Double[size];
        for(int i = 0; i < size; i++){
            x[i] = Double.valueOf(i);
            y[i] = Math.exp(x[i]);
        }
        return new Tuple2<>(x,y);
    }


}
