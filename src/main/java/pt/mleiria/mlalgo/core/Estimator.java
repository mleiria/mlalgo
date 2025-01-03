/*
Machine Learning tasks like feature extraction , feature selection
or dimensionality reduction are also provided as estimators.

The constructor of an estimator does not see any actual data, nor
does it perform any actual learning. All it does is attach the given parameters
to the object.

    
 */
package pt.mleiria.mlalgo.core;

/**
 * For building and fitting models
 *
 * @author manuel
 */
public interface Estimator<X, Y> {


    /**
     * Learn a model from training data
     *
     * @param xTrain
     * @param yTrain
     * @return
     */
    Estimator<X, Y> fit(final X[][] xTrain, final Y[] yTrain);

    X[] predict(final X[][] xSample);

    Double score(X[][] testX, Y[] trueLabelY);

    X[][] getX();

    Y[] getY();



}
