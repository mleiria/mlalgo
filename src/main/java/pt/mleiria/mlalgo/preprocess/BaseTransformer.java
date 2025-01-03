/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import pt.mleiria.mlalgo.core.Transformer;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author manuel
 */
public abstract class BaseTransformer implements Transformer {

    protected SummaryStatistics[] sm;

    /**
     * @param colIndex the column index
     */
    @Override
    public SummaryStatistics getParams(int colIndex) {
        return sm[colIndex];
    }

    public SummaryStatistics[] accumulateStatistics(final Double[][] xTrain) {
        final int cols = xTrain[0].length;
        // Initialize summary statistics array
        final SummaryStatistics[] sm = IntStream.range(0, cols)
                .mapToObj(i -> new SummaryStatistics())
                .toArray(SummaryStatistics[]::new);

        // Accumulate statistics for each column
        Stream.of(xTrain)
                .forEach(row ->
                        IntStream.range(0, cols).forEach(j -> sm[j].addValue(row[j])));
        return sm;
    }


}
