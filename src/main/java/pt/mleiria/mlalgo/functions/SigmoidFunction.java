/**
 *
 */
package pt.mleiria.mlalgo.functions;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author manuel
 *
 */
public class SigmoidFunction implements OneVarFunction<Double[], Double[]> {

    @Override
    public Double[] value(final Double[] x) {

        return Arrays.stream(x)
                .map(elem -> 1. / (1. + Math.exp(-elem)))
                .toList()
                .toArray(new Double[x.length]);
    }
}
