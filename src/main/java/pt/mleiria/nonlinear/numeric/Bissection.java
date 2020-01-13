/**
 *
 */
package pt.mleiria.nonlinear.numeric;

import pt.mleiria.mlalgo.functions.OneVarFunction;

/**
 * @author manuel
 *
 *         Condição suficiente de convergência do método:<br>
 *         - f continua em [a, b] <br>
 *         - f(a)f(b) < 0
 *
 */
public class Bissection {

    private double x;
    private double x0;
    private double a0;
    private double a;
    private double b0;
    private double b;
    private double epsilon = 0.0001;
    private int maxIter = 100;
    private OneVarFunction<Double, Double> f;

    /**
     *
     * @param a
     * @param b
     */
    public Bissection(final double a, final double b, OneVarFunction<Double, Double> f) {
        this.a0 = a;
        this.b0 = b;
        this.x0 = a;
        this.f = f;
    }

    public void iterate() {
        int currentCount = 0;
        while (true) {
            x = (a0 + b0) / 2;
            if (hasconverged() || currentCount == maxIter) {
                break;
            }
            currentCount++;
            System.out.println(currentCount + " : " + x + " : " + f.value(x));
        }
        System.out.println(currentCount + " : " + x + " : " + f.value(x));
    }

    public boolean hasconverged() {
        if (Math.abs(x - x0) <= epsilon || Math.abs(f.value(x)) <= epsilon) {
            return true;
        } else {
            if (x * f.value(a0) < 0) {
                a = a0;
                b = x;
            } else {
                a = x;
                b = b0;
            }
            return false;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        OneVarFunction<Double, Double> func = x -> Math.pow(10, 6) * Math.exp(x) + (281000 / x) * (Math.exp(x) - 1) - 178000000;
        System.out.println(func.value(0.36));
        Bissection b = new Bissection(0.1, 1., func);

//		OneVarFunction<Double, Double> func = x -> Math.pow(x, 2) - 4;
//		Bissection b = new Bissection(1., 3., func);

        b.iterate();

    }

}
