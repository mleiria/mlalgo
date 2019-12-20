/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.core;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * For converting data
 * @author manuel
 */
public interface Transformer {
    
    Transformer fit(Double[][] xTrain);
    
    Double[][] transform(Double[][] xTrain);
    
    Double[][] fitTransform(Double[][] xTrain);
    /**
     * 
     * @param colIndex
     * @return SummaryStatistics of the selected column
     */
    SummaryStatistics getParams(int colIndex);
    
    
}
