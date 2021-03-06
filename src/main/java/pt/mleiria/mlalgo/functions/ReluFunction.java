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
public class ReluFunction implements OneVarFunction<Double[], Double[]> {

    @Override
    public Double[] value(final Double[] x) {

        return Arrays.asList(x)
                .stream()
                .map(elem -> Math.tanh(elem))
                .collect(Collectors.toList())
                .toArray(new Double[x.length]);
    }
}
