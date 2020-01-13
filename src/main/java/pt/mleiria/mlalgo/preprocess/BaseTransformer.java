/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import pt.mleiria.mlalgo.core.Transformer;

/**
 * @author manuel
 */
public abstract class BaseTransformer implements Transformer {

    protected SummaryStatistics[] sm;

    /**
     * @param colIndex
     */
    @Override
    public SummaryStatistics getParams(int colIndex) {
        return sm[colIndex];
    }


}
