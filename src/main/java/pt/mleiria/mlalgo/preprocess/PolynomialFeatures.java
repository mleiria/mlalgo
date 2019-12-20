/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import static java.lang.Math.pow;
import static java.lang.System.arraycopy;
import static pt.mleiria.mlalgo.utils.Arrays2D.addOnes;
import static pt.mleiria.mlalgo.utils.Arrays2D.copy;
import static pt.mleiria.mlalgo.utils.Arrays2D.mergeHorizontal;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import pt.mleiria.mlalgo.core.Transformer;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class PolynomialFeatures extends BaseTransformer {

    private final int degree;
    private final boolean includeBias;
    private Double[][] dataX;

    /**
     * Default includeBias is true
     * @param degree
     */
    public PolynomialFeatures(int degree) {
        this.degree = degree;
        this.includeBias = true;
    }
    
    /**
    *
    * @param degree
    * @param includeBias
    */
   public PolynomialFeatures(int degree, boolean includeBias) {
       this.degree = degree;
       this.includeBias = includeBias;
   }

    /**
     *
     * @param x
     * @return
     */
    @Override
    public Transformer fit(Double[][] x) {
        final int rows = x.length;
        for (int k = 1; k <= degree; k++) {
            final Double[][] expanded = new Double[rows][k + 1];
            for (int i = 0; i < rows; i++) {
                final Double[] tmp = new Double[k + 1];
                for (int j = 0; j < k + 1; j++) {
                    tmp[j] = pow(x[i][0], k - j) * pow(x[i][1], j);
                }
                arraycopy(tmp, 0, expanded[i], 0, tmp.length);
            }
            if (dataX == null) {
                dataX = copy(expanded, 0);
            } else {
                dataX = mergeHorizontal(dataX, expanded);
            }
        }
        if (includeBias) {
            dataX = addOnes(dataX);
        } else {
            dataX = copy(dataX, 0);
        }
        return this;
    }

    @Override
    public SummaryStatistics getParams(int colIndex) {
        final SummaryStatistics ss = new SummaryStatistics();
        for (final Double[] dataX1 : dataX) {
            ss.addValue(dataX1[colIndex]);
        }
        return ss;
    }

    /**
     *
     * @param x
     * @return
     */
    @Override
    public Double[][] transform(Double[][] x) {
        return dataX;
    }

    @Override
    public Double[][] fitTransform(Double[][] xTrain) {
        return fit(xTrain).transform(xTrain);
    }
}
