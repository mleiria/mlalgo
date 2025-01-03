/**
 *
 */
package pt.mleiria.mlalgo.functions;

import java.util.Arrays;

/**
 * @author manuel
 *
 */
public class ReluFunction implements OneVarFunction<Double[], Double[]> {

    @Override
    public Double[] value(final Double[] x) {
        return Arrays.stream(x)
                .map(Math::tanh)
                .toList()
                .toArray(new Double[x.length]);
    }
}
