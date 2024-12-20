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
public class LogFunction implements OneVarFunction<Double[], Double[]> {

    @Override
    public Double[] value(Double[] x) {
        return Arrays.stream(x)
                .map(Math::log)
                .toList()
                .toArray(new Double[x.length]);
    }

}
