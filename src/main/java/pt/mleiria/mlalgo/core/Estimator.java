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
public interface Estimator {

      
    /**
     * Learn a model from training data
     * @param xTrain
     * @param yTrain 
     * @return  
     */
    Estimator fit(final Double[][] xTrain, final Double[] yTrain);
    
    Double[] predict(final Double[][] xSample);
    
    Double score(Double[][] testX, Double[] trueLabelY);
    
    Double[][] getX();
    
    Double[] getY();
    
}
