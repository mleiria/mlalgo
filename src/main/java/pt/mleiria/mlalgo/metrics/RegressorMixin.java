/**
 *
 */
package pt.mleiria.mlalgo.metrics;

/**
 * Mixin class for all regression estimators
 *
 * @author manuel
 *
 */
public class RegressorMixin implements Score<Double[], Double[], Double> {

    /**
     * The best possible score is 1.0 and it can be negative (because the model can
     * be arbitrarily worse). A constant model that always predicts the expected
     * value of y, disregarding the input features, would get a R^2 score of 0.0.
     *
     * @param yPred
     * @param yTrue
     * @return
     */
    @Override
    public Double score(Double[] yPred, Double[] yTrue) {
        final int size = yPred.length;
        if (size != yTrue.length) {
            throw new IllegalArgumentException("Sizes dont match.");
        }
        double u = 0.0;
        double sumYTrue = 0.;
        for (int i = 0; i < size; i++) {
            u += Math.pow(yTrue[i] - yPred[i], 2);
            sumYTrue += yPred[i];
        }
        final double mu = sumYTrue / (double) yTrue.length;
        double v = 0.;
        for (int i = 0; i < size; i++) {
            v += Math.pow(yTrue[i] - mu, 2);
        }
        if (v == 0.) {
            return Double.NaN;
        }
        return (1 - u / v);
    }

}
