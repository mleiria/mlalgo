/**
 *
 */
package pt.mleiria.mlalgo.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.random;

/**
 * @author manuel
 */
public class Arrays1D {

    private static final Random RND = new Random();

    private Arrays1D() {
    }

    private static int parallelThreshold = 30000000;

    /**
     * @param value the value to set
     */
    public static void setParallelThreshold(final int value) {
        parallelThreshold = value;
    }

    /**
     * @param unBoxed the array to box
     * @return a boxed array
     */
    public static Double[] box(final double[] unBoxed) {
        return unBoxed.length > parallelThreshold
                ? DoubleStream.of(unBoxed).parallel().boxed().toArray(Double[]::new)
                : DoubleStream.of(unBoxed).boxed().toArray(Double[]::new);

    }

    /**
     * @param vector the vector to convert
     * @return an array of integers
     */
    public static Integer[] convertToInt(final Double[] vector) {
        return
                Arrays.stream(vector).map(Double::intValue)
                        .toArray(Integer[]::new);
    }

    /**
     * flats a matrix into a row vector
     *
     * @param data the matrix to convert
     * @return a row vector
     */
    public static Double[] convertToVector(final Double[][] data) {
        return
                Arrays.stream(data)
                        .flatMap(Arrays::stream)
                        .toArray(Double[]::new);
    }

    /**
     *
     *
     * @param boxed the array to unbox
     * @return an unboxed array
     */
    public static double[] unBox(final Double[] boxed) {
        return boxed.length > parallelThreshold
                ? Stream.of(boxed).parallel().mapToDouble(Double::doubleValue).toArray()
                : Stream.of(boxed).mapToDouble(Double::doubleValue).toArray();

    }


    /**
     * @param x
     * @param y
     * @return
     */
    public static Double[] dot(final Double[] x, final Double[][] y) {
        Validator.validateThrowIfMatch(x.length, y.length, (a, b) -> !Objects.equals(a, b),
                () -> "Num cols of x must have the same size of Num rows of y");

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
     * @param x
     * @param p
     * @return a Double[] with the values x[i] = 0 where the predicate is false
     */
    public static Double[] filter(final Double[] x, final Predicate<Double> p) {
        return
                Arrays.stream(x)
                        .map(elem -> p.test(elem) ? elem : 0.)
                        .toArray(Double[]::new);

    }

    /**
     * @param matrix
     * @param colIndex starts at zero
     * @return
     */
    public static Double[] getColumn(final Double[][] matrix, final int colIndex) {
        return
                Arrays.stream(matrix)
                        .map(row -> row[colIndex])
                        .toArray(Double[]::new);
    }

    /**
     * @param x
     * @param y
     */
    public static Double[] dblOperator(final Double[] x, final Double[] y, final BiFunction<Double, Double, Double> func) {
        Validator.validateThrowIfMatch(x.length, y.length, (a, b) -> !Objects.equals(a, b), () -> "Vectors must be the same size");
        return
                IntStream.range(0, x.length)
                        .mapToObj(i -> func.apply(x[i], y[i]))
                        .toArray(Double[]::new);
    }

    /**
     * Creates a vector with random elements uniformly distributed on the interval
     * (0, 1).
     *
     * @param size
     * @return
     */
    public static Double[] rand(final int size) {
        return dblOperator(new Double[size], x -> random());


    }

    /**
     * @param x    the vector
     * @param func the function to apply
     * @return a new vector with the function applied to each element
     */
    public static Double[] dblOperator(final Double[] x, final Function<Double, Double> func) {
        return x.length > parallelThreshold
                ? Arrays.stream(x).parallel().map(func).toArray(Double[]::new)
                : Arrays.stream(x).map(func).toArray(Double[]::new);
    }

    /**
     * @param x    the vector
     * @param func the function to apply
     * @return a new vector with the function applied to each element
     */
    public static Integer[] intOperator(final Integer[] x, final Function<Integer, Integer> func) {
        return Arrays.stream(x).map(func).toArray(Integer[]::new);

    }

    /**
     * @param start
     * @param size
     * @return
     */
    public static Double[] genVector(final int start, final int size) {
        return
                IntStream.range(0, size)
                        .mapToObj(i -> (double) (i + start))
                        .toArray(Double[]::new);

    }

    /**
     * @param range
     * @param size
     * @return
     */
    public static Integer[] randomChoice(final int range, final int size) {
        return intOperator(new Integer[size], x -> RND.nextInt(range));
    }

    /**
     * @param values
     * @param indexes
     * @return
     */
    public static Double[] genVectorFromIndexes(final Double[] values, final Integer[] indexes) {
        return
                Arrays.stream(indexes).mapToDouble(index -> values[index])
                        .boxed()
                        .toArray(Double[]::new);
    }

    public static Double[] round(final Double[] x) {
        return dblOperator(x, elem -> (double) Math.round(elem));
    }

    public static Double[] cloneVector(final Double[] x) {
        return x.length > parallelThreshold
                ? Stream.of(x).parallel().toArray(Double[]::new)
                : Stream.of(x).toArray(Double[]::new);

    }
}
