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
public class TanhFunction implements OneVarFunction<Double[], Double[]> {

    @Override
    public Double[] value(final Double[] x) {

        return Arrays.asList(x)
                .stream()
                .map(elem -> Math.max(0, elem))
                .collect(Collectors.toList())
                .toArray(new Double[x.length]);
    }
}
