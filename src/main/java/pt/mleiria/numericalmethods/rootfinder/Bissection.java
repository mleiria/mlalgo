package pt.mleiria.numericalmethods.rootfinder;

import java.util.Optional;
import java.util.function.ToDoubleFunction;

public class Bissection extends FunctionalRootFinder {

    @Override
    public double step(Double a, Double b, Optional<ToDoubleFunction> f) {
        return (a + b) / 2.0;
    }

}
