/**
 *
 */
package pt.mleiria.regressor.linearmodel;

import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.metrics.CrossValidationScore;

/**
 * Implements ridge regression with built-in cross-validation of the alpha
 * parameter
 *
 * @author manuel
 *
 */
public class RidgeCV implements Estimator<Double, Double> {


    private final double[] lambdas;
    private final boolean fitIntercept;
    private final int cv;
    private final boolean isShuffle;
    private Estimator<Double, Double> bestRidge;

    /**
     *
     * @param lambda
     * @param fitIntercept if true => addOnes to X[][]
     * @param cv           number of folds
     * @param isSHuffle
     */
    public RidgeCV(final double[] lambdas, final boolean fitIntercept, int cv, boolean isShuffle) {
        this.lambdas = lambdas;
        this.fitIntercept = fitIntercept;
        this.cv = cv;
        this.isShuffle = isShuffle;
    }

    /**
     * Defaults fitIntercept to true
     * Defaults isShuffle to false
     * @param lambda
     * @param cv     number of folds
     */
    public RidgeCV(final double[] lambdas, int cv) {
        this(lambdas, true, cv, false);
    }


    @Override
    public Estimator<Double, Double> fit(Double[][] xTrain, Double[] yTrain) {
        double bestScore = Double.NEGATIVE_INFINITY;
        for (final double lambda : lambdas) {
            final Ridge ridge = new Ridge(lambda, fitIntercept);

            final Double score = CrossValidationScore.create(ridge)
                    .setCv(cv)
                    .setShuffle(isShuffle)
                    .score(xTrain, yTrain);


            if (score > bestScore) {
                bestScore = score;
                bestRidge = new Ridge(lambda, fitIntercept);
            }
        }
        bestRidge.fit(xTrain, yTrain);
        return this;
    }

    @Override
    public Double[] predict(Double[][] xSample) {
        return bestRidge.predict(xSample);
    }

    @Override
    public Double score(Double[][] testX, Double[] yTrue) {
        return bestRidge.score(testX, yTrue);
    }

    @Override
    public Double[][] getX() {
        return bestRidge.getX();
    }

    @Override
    public Double[] getY() {
        return bestRidge.getY();
    }

    /**
     *
     * @return
     */
    public double[] getThetas() {
        return ((Ridge) bestRidge).getThetas();
    }

    /**
     *
     * @return
     */
    public double getBestLambda() {
        return ((Ridge) bestRidge).getLambda();
    }


}
