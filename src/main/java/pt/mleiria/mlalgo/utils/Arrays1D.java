/**
 *
 */
package pt.mleiria.mlalgo.utils;

import static java.lang.Math.random;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @author manuel
 *
 */
public class Arrays1D {

    private static final Random RND = new Random();

    private Arrays1D() {
    }

    private static int parallelThreshold = 30000000;

    /**
     *
     * @param value
     */
    public static void setParallelThreshold(final int value) {
        parallelThreshold = value;
    }

    /**
     *
     * @param unBoxed
     * @return
     */
    public static Double[] box(final double[] unBoxed) {
        if (unBoxed.length > parallelThreshold) {
            return DoubleStream.of(unBoxed).parallel().boxed().toArray(Double[]::new);
        } else {
            return DoubleStream.of(unBoxed).boxed().toArray(Double[]::new);
        }
    }

    /**
     *
     * @param vector
     * @return
     */
    public static Integer[] convertToInt(final Double[] vector) {
        final Integer[] res = new Integer[vector.length];
        System.arraycopy(vector, 0, res, 0, vector.length);
        return res;
    }

    /**
     * flats a matrix into a row vector
     * @param data
     * @return
     */
    public static Double[] convertToVector(final int[][] data) {
        final int numRows = data.length;
        final int numCols = data[0].length;
        final Double[] res = new Double[numRows * numCols];
        int cnt = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                res[cnt] = Double.valueOf(data[i][j]);
                cnt++;
            }
        }
        return res;
    }

	/**c
	 * 
	 * @param boxed
	 * @return
	 */
    public static double[] unBox(final Double[] boxed) {
        if (boxed.length > parallelThreshold) {
            return Stream.of(boxed).parallel().mapToDouble(Double::doubleValue).toArray();
        } else {
            return Stream.of(boxed).mapToDouble(Double::doubleValue).toArray();
        }
    }


    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static Double[] dot(final Double[] x, final Double[][] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Num cols of x must have the same size of Num rows of y");
        }

        final Double[] res = new Double[y[0].length];
        for (int j = 0; j < y[0].length; j++) {
            res[j] = 0.;
            for (int i = 0; i < x.length; i++) {
                res[j] += x[i] * y[i][j];
            }
        }
        return res;
    }

    /**
     *
     * @param x
     * @param p
     * @return a Double[] with the values x[i] = 0 where the predicate is false
     */
    public static Double[] filter(final Double[] x, final Predicate<Double> p) {

        final Double[] res = new Double[x.length];
        for (int i = 0; i < x.length; i++) {
            if (p.test(x[i])) {
                res[i] = x[i];
            } else {
                res[i] = 0.;
            }
        }
        return res;
    }

    /**
     *
     * @param matrix
     * @param colIndex
     *                     starts at zero
     * @return
     */
    public static Double[] getColumn(final Double[][] matrix, final int colIndex) {
        final Double[] col = new Double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            col[i] = matrix[i][colIndex];
        }
        return col;
    }

    /**
     *
     * @param x
     * @param y
     */
    public static Double[] oper(final Double[] x, final Double[] y, final BiFunction<Double, Double, Double> func) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Vectors must be the same size");
        }
        final Double[] res = new Double[x.length];
        for (int i = 0; i < x.length; i++) {
            res[i] = func.apply(x[i], y[i]);
        }
        return res;
    }

    /**
     * Creates a vector with random elements uniformly distributed on the interval
     * (0, 1).
     *
     * @param size
     * @return
     */
    public static Double[] rand(final int size) {
        final Double[] components = new Double[size];
        for (int i = 0; i < size; i++) {
            components[i] = random();
        }
        return components;
    }

    /**
     *
     * @param x
     * @param func
     * @return
     */
    public static Double[] oper(final Double[] x, final Function<Double, Double> func) {
        final Double[] res = new Double[x.length];
        for (int i = 0; i < x.length; i++) {
            res[i] = func.apply(x[i]);
        }
        return res;
    }

    /**
     *
     * @param start
     * @param size
     * @return
     */
    public static Double[] genVector(final int start, final int size) {
        final Double[] res = new Double[size];
        int cnt = start;
        for (int i = 0; i < size; i++) {
            res[i] = (double) cnt;
            cnt++;
        }
        return res;
    }

    /**
     *
     * @param range
     * @param size
     * @return
     */
    public static int[] randomChoice(final int range, final int size) {
        final int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = RND.nextInt(range);
        }
        return res;
    }

    /**
     *
     * @param values
     * @param indexes
     * @return
     */
    public static Double[] genVectorFromIndexes(final Double[] values, final int[] indexes) {
        final Double[] res = new Double[indexes.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = values[indexes[i]];
        }
        return res;
    }

    public static Double[] round(final Double[] x) {
        final Double[] res = new Double[x.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = (double) Math.round(x[i]);
        }
        return res;
    }

    public static Double[] cloneVector(final Double[] x) {
        if (x.length > parallelThreshold) {
            return Stream.of(x).parallel().toArray(Double[]::new);
        } else {
            return Stream.of(x).toArray(Double[]::new);
        }
    }
}
