package pt.mleiria.mlalgo.functions;

/**
 *
 * @author manuel
 * @param <T>
 */
@FunctionalInterface
public interface OneVarFunction<T, R> {

    /**
     * Returns the value of the function for the specified variable value.
     *
     * @param x
     * @return a function of the form f(x)
     */
    public R value(T x);

}
