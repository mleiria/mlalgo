package pt.mleiria.mlalgo.functions;

/**
 * @param <T>
 * @author manuel
 */
@FunctionalInterface
public interface OneVarFunction<T, R> {

    /**
     * Returns the value of the function for the specified variable value.
     *
     * @param x
     * @return a function of the form f(x)
     */
    R value(T x);

}
