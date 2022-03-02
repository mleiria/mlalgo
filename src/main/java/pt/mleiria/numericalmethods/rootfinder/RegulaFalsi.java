package pt.mleiria.numericalmethods.rootfinder;

import java.util.Optional;
import java.util.function.ToDoubleFunction;

public class RegulaFalsi extends FunctionalRootFinder {

    @Override
    public double step(Double am, Double bm, Optional<ToDoubleFunction> optF) {
        final ToDoubleFunction f = optF.get();
        return bm - f.applyAsDouble(bm) * (bm - am) / (f.applyAsDouble(bm) - f.applyAsDouble(am));

    }

}
