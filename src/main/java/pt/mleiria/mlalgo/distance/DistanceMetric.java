package pt.mleiria.mlalgo.distance;
/**
 *
 * @author manuel
 * @param <X>
 * @param <Y>
 * @param <R>
 */
@FunctionalInterface
public interface DistanceMetric<X, Y, R> {

    /**
     *
     * @param x
     * @param y
     * @return
     */
    R calculate(X x, Y y);
}
