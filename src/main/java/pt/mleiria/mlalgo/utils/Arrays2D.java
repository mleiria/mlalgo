/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.utils;

import static java.lang.Math.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 *
 * @author manuel
 */
public class Arrays2D {

    //private static final Logger LOG = Logger.getLogger(Arrays2D.class.getName());

    private Arrays2D() {
    }

    /**
     * 
     * @param numRows
     * @param numCols
     * @return a double[numRows][numCols] with elements from 0 to numRows * numCols
     */
    public static Double[][] genMatrix(final int numRows, final int numCols) {
	return genMatrix(numRows, numCols, 0);
    }

    /**
     * 
     * @param numRows
     * @param numCols
     * @return a double[numRows][numCols] with elements from startValue to numRows *
     *         numCols
     */
    public static Double[][] genMatrix(final int numRows, final int numCols, final int startValue) {
	final Double[][] matrix = new Double[numRows][numCols];
	int cnt = startValue;
	for (int i = 0; i < numRows; i++) {
	    for (int j = 0; j < numCols; j++) {
		matrix[i][j] = (double) cnt++;
	    }
	}
	return matrix;
    }

    /**
     * Element wise multiplication
     * 
     * @param x
     * @param y
     * @return
     */
    public static double[] hadamardProduct(final double[] x, final double[] y) {
	if (x.length != y.length) {
	    throw new IllegalArgumentException("Vectors must be the same size");
	}
	final double[] res = new double[x.length];
	for (int i = 0; i < x.length; i++) {
	    res[i] = x[i] * y[i];
	}
	return res;
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public static double[] dot(final double[] x, final double[][] y) {
	if (x.length != y.length) {
	    throw new IllegalArgumentException("Num cols of x must have the same size of Num rows of y");
	}
	final double[] res = new double[x.length];
	for (int j = 0; j < y[0].length; j++) {
	    for (int i = 0; i < x.length; i++) {
		res[j] += x[i] * y[i][j];
	    }
	}
	return res;
    }

    /**
     * 
     * @param x
     * @return
     */
    public static Double sum(final Double[] x) {
	double res = 0;
	for (int i = 0; i < x.length; i++) {
	    res += x[i];
	}
	return res;
    }

    /**
     *
     * @param rows
     * @param cols
     * @return a Matrix of 1.0
     */
    public static double[][] ones(final int rows, final int cols) {
	final double[][] components = new double[rows][cols];
	// for each row
	for (int i = 0; i < rows; i++) {
	    // for each column
	    for (int j = 0; j < cols; j++) {
		components[i][j] = 1.0;
	    }
	}
	return components;
    }
   
    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double[][] mergeHorizontal(double[][] a, double[][] b) {
	final int totalCols = a[0].length + b[0].length;
	final double[][] res = new double[a.length][totalCols];
	for (int i = 0; i < a.length; i++) {
	    for (int j = 0; j < totalCols; j++) {
		if (j < a[0].length) {
		    res[i][j] = a[i][j];
		} else {
		    res[i][j] = b[i][j - a[0].length];
		}
	    }
	}
	return res;
    }

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static Double[][] mergeHorizontal(Double[][] a, Double[][] b) {
	final int totalCols = a[0].length + b[0].length;
	final Double[][] res = new Double[a.length][totalCols];
	for (int i = 0; i < a.length; i++) {
	    for (int j = 0; j < totalCols; j++) {
		if (j < a[0].length) {
		    res[i][j] = a[i][j];
		} else {
		    res[i][j] = b[i][j - a[0].length];
		}
	    }
	}
	return res;
    }
    /**
     * Merges the Pairs e one single Pair 
     * @param lst
     * @param rows
     * @param cols
     * @return
     */
    public static Pair<Double[][], Double[]> merge(final List<Pair<Double[][], Double[]>> lst, int rows, int cols) {
	Double[][] x = new Double[rows][cols];
	Double[] y = new Double[rows];
	int rowCount = 0;
	for (final Pair<Double[][], Double[]> pair : lst) {
	    for (int i = 0; i < pair.getX().length; i++) {
		for (int j = 0; j < pair.getX()[0].length; j++) {
		    x[rowCount][j] = pair.getX()[i][j];
		}
		y[rowCount] = pair.getY()[i];
		rowCount++;
	    }
	}
	//LOG.info("x"+ new VUtils<>().showContents(x));
	//LOG.info("y"+ new VUtils<>().showContents(y));
	return new Pair<>(x, y);
    }

    /**
     * 
     * @param x
     * @return
     */
    public static double[][] addOnes(double[][] x) {
	final double[][] res = copy(x, 1);
	for (int i = 0; i < res.length; i++) {
	    res[i][0] = 1.0;
	}
	return res;
    }

    /**
     * 
     * @param x
     * @return
     */
    public static Double[][] addOnes(Double[][] x) {
	final Double[][] res = copy(x, 1);
	for (int i = 0; i < res.length; i++) {
	    res[i][0] = 1.0;
	}
	return res;
    }

    /**
     * 
     * @param x
     * @param offSet
     * @return
     */
    public static double[][] copy(double[][] x, int offSet) {
	final int rows = x.length;
	final int cols = x[0].length;
	final double[][] res = new double[rows][cols + offSet];
	for (int i = 0; i < rows; i++) {
	    System.arraycopy(x[i], 0, res[i], offSet, cols);
	}
	return res;
    }

    /**
     * 
     * @param x
     * @param offSet
     * @return
     */
    public static Double[][] copy(Double[][] x, int offSet) {
	final int rows = x.length;
	final int cols = x[0].length;
	final Double[][] res = new Double[rows][cols + offSet];
	for (int i = 0; i < rows; i++) {
	    res[i] = new Double[cols + offSet];
	    System.arraycopy(x[i], 0, res[i], offSet, cols);
	}
	return res;
    }

    /**
     * Creates a matrix with random elements uniformly distributed on the interval
     * (0, 1).
     *
     * @param rows
     * @param cols
     * @return Matrix
     */
    public static double[][] rand(final int rows, final int cols) {
	final double[][] components = new double[rows][cols];
	// for each row
	for (int i = 0; i < rows; i++) {
	    // for each column
	    for (int j = 0; j < cols; j++) {
		components[i][j] = random();
	    }
	}
	return components;
    }

    /**
     *
     * @param rows
     * @param cols
     * @return an Identity Matrix
     */
    public static double[][] identity(final int rows, final int cols) {
	final double[][] components = new double[rows][cols];
	if (rows != cols) {
	    throw new IllegalArgumentException("Only Square matrix supported.");
	}
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		if (i == j) {
		    components[i][j] = 1;
		} else {
		    components[i][j] = 0;
		}
	    }
	}
	return components;
    }

    /**
     * Trace Operator
     *
     * @param matrix
     * @return
     */
    public static double trace(final double[][] matrix) {
	final int rows = matrix.length;
	final int cols = matrix[0].length;
	double sum = 0;
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		if (i == j) {

		    sum += matrix[j][j];
		}
	    }
	}
	return sum;
    }

    /**
     *
     * @param x
     * @param y
     * @param trainSize
     *                      interval [0,1]
     * @param isShuffle
     * @return List<Pair<Double[][], Double[]> { Pair(trainX, trainY), Pair(testX,
     *         testY)}
     * 
     */
    public static List<Pair<Double[][], Double[]>> trainTestSplit(Double[][] x, Double[] y, double trainSize,
	    boolean isShuffle) {
	if (trainSize > 1. || trainSize < 0.) {
	    throw new IllegalArgumentException("trainSize must be in interval [0, 1]");
	}
	final int rows = y.length;
	final int cols = x[0].length;
	final int dim = (int) (rows * trainSize);

	final Double[][] trainX = new Double[dim][cols];
	final Double[][] testX = new Double[rows - dim][cols];
	final Double[] trainY = new Double[dim];
	final Double[] testY = new Double[rows - dim];

	if (isShuffle) {
	    final List<Integer> lst = new ArrayList<>(rows);
	    IntStream.range(0, rows).forEach(i -> lst.add(i));
	    Collections.shuffle(lst);
	    for (int i = 0; i < rows; i++) {
		final int j = lst.get(i);
		if (i < dim) {
		    System.arraycopy(x[j], 0, trainX[i], 0, cols);
		    trainY[i] = y[j];
		} else {
		    System.arraycopy(x[j], 0, testX[i - dim], 0, cols);
		    testY[i - dim] = y[j];
		}
	    }
	} else {
	    for (int i = 0; i < rows; i++) {
		if (i < dim) {
		    System.arraycopy(x[i], 0, trainX[i], 0, cols);
		    trainY[i] = y[i];
		} else {
		    System.arraycopy(x[i], 0, testX[i - dim], 0, cols);
		    testY[i - dim] = y[i];
		}
	    }
	}

	final Pair<Double[][], Double[]> trainDs = new Pair<>(trainX, trainY);
	final Pair<Double[][], Double[]> testDs = new Pair<>(testX, testY);
	final List<Pair<Double[][], Double[]>> res = new ArrayList<>();
	res.add(trainDs);
	res.add(testDs);
	return res;
    }

    /**
     *
     * @param x
     * @param y
     * @param trainSize
     *                      Number of rows to train data
     * @param isShuffle
     * @return List<Pair<Double[][], Double[]> { Pair(trainX, trainY), Pair(testX,
     *         testY)}
     * 
     */
    public static List<Pair<Double[][], Double[]>> trainTestSplit(Double[][] x, Double[] y, int trainSize,
	    boolean isShuffle) {
	if (trainSize > x.length) {
	    throw new IllegalArgumentException("trainSize must be < " + x.length);
	}
	final int rows = y.length;
	final int cols = x[0].length;

	final Double[][] trainX = new Double[trainSize][cols];
	final Double[][] testX = new Double[rows - trainSize][cols];
	final Double[] trainY = new Double[trainSize];
	final Double[] testY = new Double[rows - trainSize];

	if (isShuffle) {
	    final List<Integer> lst = new ArrayList<>(rows);
	    IntStream.range(0, rows).forEach(i -> lst.add(i));
	    Collections.shuffle(lst);
	    for (int i = 0; i < rows; i++) {
		final int j = lst.get(i);
		if (i < trainSize) {
		    System.arraycopy(x[j], 0, trainX[i], 0, cols);
		    trainY[i] = y[j];
		} else {
		    System.arraycopy(x[j], 0, testX[i - trainSize], 0, cols);
		    testY[i - trainSize] = y[j];
		}
	    }
	} else {
	    for (int i = 0; i < rows; i++) {
		if (i < trainSize) {
		    System.arraycopy(x[i], 0, trainX[i], 0, cols);
		    trainY[i] = y[i];
		} else {
		    System.arraycopy(x[i], 0, testX[i - trainSize], 0, cols);
		    testY[i - trainSize] = y[i];
		}
	    }
	}

	final Pair<Double[][], Double[]> trainDs = new Pair<>(trainX, trainY);
	final Pair<Double[][], Double[]> testDs = new Pair<>(testX, testY);
	final List<Pair<Double[][], Double[]>> res = new ArrayList<>();
	res.add(trainDs);
	res.add(testDs);
	return res;
    }

    /**
     * Implementing Fisher–Yates shuffle
     *
     * @param ar
     */
    public static void shuffleArray(Double[] ar) {
	final Random rnd = ThreadLocalRandom.current();
	for (int i = ar.length - 1; i > 0; i--) {
	    final int index = rnd.nextInt(i + 1);
	    // Simple swap
	    final Double a = ar[index];
	    ar[index] = ar[i];
	    ar[i] = a;
	}
    }

    /**
     *
     * @param trainX
     * @param trainY
     * @param rows
     * @param cv
     * @param isShuffle
     * @return
     */
    public static List<Pair<Double[][], Double[]>> kFold(final Double[][] trainX, final Double[] trainY, final int cv,
	    final boolean isShuffle) {
	final List<Pair<Double[][], Double[]>> res = new ArrayList<>();
	final int rows = trainY.length;
	final int cols = trainX[0].length;
	final int length = rows / cv;
	final List<Integer> lst = new ArrayList<>(rows);
	IntStream.range(0, rows).forEach(i -> lst.add(i));
	if (isShuffle) {
	    Collections.shuffle(lst);
	}
	int startIndex = 0;
	int endIndex = length;

	for (int i = 0; i < cv; i++) {
	    int k = 0;
	    final Double[][] x = new Double[endIndex - startIndex][cols];
	    final Double[] y = new Double[endIndex - startIndex];
	    for (int index = startIndex; index < endIndex; index++) {
		System.arraycopy(trainX[lst.get(index)], 0, x[k], 0, cols);
		y[k] = trainY[lst.get(index)];
		k++;
	    }
	    startIndex = endIndex;
	    if (i < cv - 2) {
		endIndex = endIndex + length;
	    } else {
		endIndex = rows;
	    }
	    res.add(new Pair<>(x, y));
	}
	return res;
    }

    /**
     * 
     * @param values
     * @param indexes
     * @return
     */
    public static Double[][] genMatrixFromIndexes(final Double[][] values, final int[] indexes) {
	final Double[][] res = new Double[indexes.length][];
	for (int i = 0; i < res.length; i++) {
	    res[i] = new Double[values[0].length];
	    System.arraycopy(values[indexes[i]], 0, res[i], 0, values[0].length);
	}
	return res;
    }

    /**
     *
     * @param x
     * @return
     */
    public static double[][] copyToPrimitive(final Double[][] x) {
	final int rows = x.length;
	final int cols = x[0].length;
	final double[][] res = new double[rows][cols];
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		res[i][j] = x[i][j];
	    }
	}
	return res;
    }

}
