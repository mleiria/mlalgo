/**
 *
 */
package pt.mleiria.mlalgo.functions;

import java.util.Arrays;

/**
 * @author manuel
 *
 */
public class TanhFunction implements OneVarFunction<Double[], Double[]> {

    @Override
    public Double[] value(final Double[] x) {

        return Arrays.stream(x)
                .map(elem -> Math.max(0, elem))
                .toList()
                .toArray(new Double[x.length]);
    }
}
