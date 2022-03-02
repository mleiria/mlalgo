package pt.mleiria.numericalmethods.rootfinder;

import java.util.Optional;
import java.util.function.*;

/**
 * Condições a verificar:
 * 1. f contínua em [a,b]
 * 2. f(a)f(b) < 0
 * 3. QUalquer que x pertencente a ]a,b[ , f'(x) != 0
 */
public class BissectionImperative {

    private final double a;
    private final double b;
    private final ToDoubleFunction<Double> f;
    private final double epsilon;
    private final long maxIter;

    private double am;
    private double bm;
    private double xm, xm1;

    /**
     * Condição suficiente de convergência do método:
     * - f contínua em [a,b]
     * - f(a)f(b) < 0
     */
    public BissectionImperative(final double a, final double b, ToDoubleFunction<Double> f,
                                final double epsilon, final long maxIter) {
        this.a = a;
        this.b = b;
        this.f = f;
        this.epsilon = epsilon;
        this.maxIter = maxIter;
        init();
    }

    private void init() {
        am = a;
        bm = b;
        xm = a;
    }

    ToDoubleBiFunction<Double, Double> step = (am, bm) -> (am + bm) / 2.0;

    public Optional<Double> findRoot() {
        for (int i = 0; i < maxIter; i++) {
            xm1 = step.applyAsDouble(am, bm);
            double tmp = Math.abs(xm1 - xm);
            double tmp1 = Math.abs(f.applyAsDouble(xm1));

            if (tmp <= epsilon || tmp1 <= epsilon) {
                return Optional.of(xm1);
            } else {
                if ((f.applyAsDouble(xm1) * f.applyAsDouble(am)) < 0) {
                    bm = xm1;
                } else {
                    am = xm1;
                }
                System.out.printf("%d    %.12f    %.12f    %n", i, xm, f.applyAsDouble(xm));
                xm = xm1;
            }
        }
        return Optional.empty();
    }



    public static void main(String[] args) {
        ToDoubleFunction<Double> f = elem -> Math.pow(elem, 3) - elem - 2.0;
        double a = 1.0;
        double b = 2.0;
        double epsilon = 1E-10;
        BissectionImperative bissectionImperative = new BissectionImperative(a, b, f, epsilon, 100);
        System.out.println(bissectionImperative.findRoot());
    }


}
