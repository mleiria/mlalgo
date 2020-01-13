/**
 *
 */
package pt.mleiria.mlalgo.functions;

/**
 * @author manuel
 *
 */
public class ActivationFunction implements OneVarFunction<Double, Integer> {

    @Override
    public Integer value(Double x) {
        return x > 0 ? 1 : -1;
    }

}
