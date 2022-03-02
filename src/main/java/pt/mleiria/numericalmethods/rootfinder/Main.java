package pt.mleiria.numericalmethods.rootfinder;

import java.util.function.ToDoubleFunction;

public class Main {

    public static void main(String[] args) {
        final ToDoubleFunction<Double> f = x -> Math.pow(x, 3) - x - 2.0;
        double a = 1.0;
        double b = 2.0;

        final ToDoubleFunction<Double> f1 = x -> 1E6 * Math.exp(x) + (281E3 / x) * (Math.exp(x) - 1) - 1.780E6;
        double a1 = 0.1;
        double b1 = 1.0;

        Bissection bissection = new Bissection();
        System.out.println(bissection.findRoot(f1, a1, b1));

        final RegulaFalsi regulaFalsi = new RegulaFalsi();
        System.out.println(regulaFalsi.findRoot(f, a, b));


    }
}
