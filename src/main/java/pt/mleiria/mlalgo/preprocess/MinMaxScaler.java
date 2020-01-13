/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * @author manuel
 */
public class MinMaxScaler extends BaseTransformer {

    private int min = 0;
    private int max = 1;

    /**
     * @param min
     * @param max
     */
    public void setFeaturesRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Compute the minimum and maximum to be used for later scaling.
     */
    @Override
    public MinMaxScaler fit(Double[][] xTrain) {
        final int rows = xTrain.length;
        final int cols = xTrain[0].length;
        sm = new SummaryStatistics[cols];
        for (int j = 0; j < cols; j++) {
            sm[j] = new SummaryStatistics();
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sm[j].addValue(xTrain[i][j]);
            }
        }
        return this;
    }

    /**
     * Scaling features of X according to feature_range.
     */
    @Override
    public Double[][] transform(Double[][] xTrain) {
        final int rows = xTrain.length;
        final int cols = xTrain[0].length;
        final Double[][] transformed = new Double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transformed[i][j] = (xTrain[i][j] - sm[j].getMin()) / (sm[j].getMax() - sm[j].getMin());
                transformed[i][j] = transformed[i][j] * (max - min) + min;
            }
        }
        return transformed;
    }

    /**
     * Fit to data, then transform it.
     */
    @Override
    public Double[][] fitTransform(Double[][] xTrain) {
        return fit(xTrain).transform(xTrain);
    }

}
