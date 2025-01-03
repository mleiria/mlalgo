/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import static java.lang.Math.sqrt;

/**
 * is a transformer and an estimator
 *
 * @author manuel
 */
public class StandardScaler extends BaseTransformer {

    /**
     * Compute the mean and std to be used for later scaling.
     *
     * @param xTrain the data used to compute the mean and standard deviation
     * @return StandardScaler object
     */
    @Override
    public StandardScaler fit(final Double[][] xTrain) {
        sm = accumulateStatistics(xTrain);
        return this;
    }

    /**
     *
     */
    @Override
    public Double[][] transform(Double[][] xTrain) {
        final int rows = xTrain.length;
        final int cols = xTrain[0].length;
        final Double[][] transformed = new Double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transformed[i][j] = (xTrain[i][j] - sm[j].getMean()) / sqrt(sm[j].getPopulationVariance());
            }
        }
        return transformed;
    }

    /**
     *
     */
    @Override
    public Double[][] fitTransform(Double[][] xTrain) {
        return fit(xTrain).transform(xTrain);
    }

}
