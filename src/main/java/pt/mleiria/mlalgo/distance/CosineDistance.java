package pt.mleiria.mlalgo.distance;

import java.util.stream.IntStream;

public class CosineDistance implements DistanceMetric<Double[], Double[], Double> {

    @Override
    public Double calculate(Double[] x, Double[] y) {

        final double[] results = IntStream.range(0, x.length)
                .mapToObj(i -> new double[]{x[i] * y[i], x[i] * x[i], y[i] * y[i]})
                .reduce(new double[]{0.0, 0.0, 0.0}, (a, b) ->
                        new double[]{a[0] + b[0], a[1] + b[1], a[2] + b[2]});

        final double dotProduct = results[0];
        final double normX = Math.sqrt(results[1]);
        final double normY = Math.sqrt(results[2]);
        return dotProduct / (normX * normY);
    }
}
