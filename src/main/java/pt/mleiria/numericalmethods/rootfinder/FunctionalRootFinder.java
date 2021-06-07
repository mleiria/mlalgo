package pt.mleiria.numericalmethods.rootfinder;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public abstract class FunctionalRootFinder {

    protected static final double epsilon = 1E-10;
    protected static final long maxIter = 100;


    public abstract double step(Double a, Double b, Optional<ToDoubleFunction> f);

    private Optional<Double> findRoot(final ToDoubleFunction f, final double am, final double bm, final double xm, final long cnt) {
        System.out.printf("%d    %.12f    %.12f    %n", cnt, xm, f.applyAsDouble(xm));
        if (cnt == maxIter) {
            return Optional.empty();
        }
        final double xm1 = step(am, bm, Optional.of(f));
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
    protected Optional<Double> findRoot(final ToDoubleFunction f, final double am, final double bm) {
        return findRoot(f, am, bm, am, 0);
    }
}
