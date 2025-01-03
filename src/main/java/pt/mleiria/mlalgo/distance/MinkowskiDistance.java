package pt.mleiria.mlalgo.distance;

import java.util.stream.IntStream;

import static java.lang.Math.abs;

public interface MinkowskiDistance {


    default Double calculate(Double[] x, Double[] y, final int p) {
        return
                Math.pow(
                        IntStream.range(0, x.length)
                                .mapToDouble(i -> Math.pow(abs(x[i] - y[i]), p))
                                .sum(), 1.0 / p);
    }
}
