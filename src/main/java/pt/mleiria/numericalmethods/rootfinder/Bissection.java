package pt.mleiria.numericalmethods.rootfinder;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

public class Bissection {

    private static final double epsilon = 1E-10;
    private static final long maxIter = 100;
    /**
     * Step condition
     */
    private static ToDoubleBiFunction<Double, Double> step = (elem1, elem2) -> (elem1 + elem2) / 2.0;


    private static Optional<Double> findRoot(final ToDoubleFunction f, final double am, final double bm, final double xm, final long cnt) {
        System.out.printf("%d    %.12f    %.12f    %n", cnt, xm, f.applyAsDouble(xm));
        if (cnt == maxIter) {
            return Optional.empty();
        }
        final double xm1 = step.applyAsDouble(am, bm);
        final BiPredicate<Double, Double> foundCondition = (elem1, elem2) -> elem1 <= epsilon || elem2 <= epsilon;
        final Predicate<Double> iterateCondition = elem -> elem < 0;

        if (foundCondition.test(Math.abs(xm1 - xm), Math.abs(f.applyAsDouble(xm1)))) {
            return Optional.of(xm1);
        } else {
            if (iterateCondition.test(f.applyAsDouble(xm1) * f.applyAsDouble(am))) {
                return findRoot(f, am, xm1, xm1, cnt + 1);
            } else {
                return findRoot(f, xm1, bm, xm1, cnt + 1);
            }
        }
    }

    /**
     * @param f
     * @param am
     * @param bm
     * @return the root of the function or empty
     */
    public static Optional<Double> findRoot(final ToDoubleFunction f, final double am, final double bm) {
        return findRoot(f, am, bm, am, 0);
    }

    public static void main(String[] args) {
        final ToDoubleFunction<Double> f = x -> Math.pow(x, 3) - x - 2.0;
        final ToDoubleFunction<Double> f1 = x -> 1E6 * Math.exp(x) + (281E3 / x) * (Math.exp(x) - 1) - 1.780E6;

        System.out.println(Bissection.findRoot(f1, 0.1, 1.0));

    }
}
