package pt.mleiria.mlalgo.distance;

/**
 * @param <X>
 * @param <Y>
 * @param <R>
 * @author manuel
 */
@FunctionalInterface
public interface DistanceMetric<X, Y, R> {

    /**
     * @param x
     * @param y
     * @return
     */
    R calculate(X x, Y y);
}
