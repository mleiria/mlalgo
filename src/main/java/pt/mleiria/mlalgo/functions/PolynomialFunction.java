package pt.mleiria.mlalgo.functions;

import static java.lang.Math.max;


/**
 * Mathematical polynomial: c[0] + c[1] * x + c[2] * x^2 + ....
 */
public class PolynomialFunction implements OneVarFunction<Double, Double> {

    /**
     * Polynomial coefficients.
     */
    private final double[] coefficients;

    /**
     * Constructor method.
     *
     * @param coeffs polynomial coefficients.
     */
    public PolynomialFunction(double[] coeffs) {
        coefficients = coeffs;
    }

    /**
     * @param r double number added to the polynomial.
     * @return PolynomialFunction
     */
    public PolynomialFunction add(double r) {
        final int n = coefficients.length;
        final double[] coef = new double[n];
        coef[0] = coefficients[0] + r;
        for (int i = 1; i < n; i++) {
            coef[i] = coefficients[i];
        }
        return new PolynomialFunction(coef);
    }

    /**
     * @param p PolynomialFunction
     * @return PolynomialFunction
     */
    public PolynomialFunction add(PolynomialFunction p) {
        final int n = max(p.degree(), degree()) + 1;
        final double[] coef = new double[n];
        for (int i = 0; i < n; i++) {
            coef[i] = coefficient(i) + p.coefficient(i);
        }
        return new PolynomialFunction(coef);
    }

    /**
     * Returns the coefficient value at the desired position
     *
     * @param n int the position of the coefficient to be returned
     * @return double the coefficient value
     * @version 1.2
     */
    public double coefficient(int n) {
        return n < coefficients.length ? coefficients[n] : 0;
    }

    /**
     * Returns degree of this polynomial function
     *
     * @return int degree of this polynomial function
     */
    public int degree() {
        return coefficients.length - 1;
    }

    /**
     * Returns the derivative of the receiver.
     *
     * @return PolynomialFunction derivative of the receiver.
     */
    public PolynomialFunction derivative() {
        final int n = degree();
        if (n == 0) {
            final double[] coef = {0};
            return new PolynomialFunction(coef);
        }
        final double[] coef = new double[n];
        for (int i = 1; i <= n; i++) {
            coef[i - 1] = coefficients[i] * i;
        }
        return new PolynomialFunction(coef);
    }

    /**
     * @param r double
     * @return PolynomialFunction
     */
    public PolynomialFunction divide(double r) {
        return multiply(1 / r);
    }

    /**
     * Returns the integral of the receiver having the value 0 for X = 0.
     *
     * @return PolynomialFunction integral of the receiver.
     */
    public PolynomialFunction integral() {
        return integral(0);
    }

    /**
     * Returns the integral of the receiver having the specified value for X =
     * 0.
     *
     * @param value double value of the integral at x=0
     * @return PolynomialFunction integral of the receiver.
     */
    public PolynomialFunction integral(double value) {
        final int n = coefficients.length + 1;
        final double[] coef = new double[n];
        coef[0] = value;
        for (int i = 1; i < n; i++) {
            coef[i] = coefficients[i - 1] / i;
        }
        return new PolynomialFunction(coef);
    }

    /**
     * @param r double
     * @return DhbFunctionEvaluation.PolynomialFunction
     */
    public PolynomialFunction multiply(double r) {
        final int n = coefficients.length;
        final double[] coef = new double[n];
        for (int i = 0; i < n; i++) {
            coef[i] = coefficients[i] * r;
        }
        return new PolynomialFunction(coef);
    }

    /**
     * @param p DhbFunctionEvaluation.PolynomialFunction
     * @return DhbFunctionEvaluation.PolynomialFunction
     */
    public PolynomialFunction multiply(PolynomialFunction p) {
        final int n = p.degree() + degree();
        final double[] coef = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            coef[i] = 0;
            for (int k = 0; k <= i; k++) {
                coef[i] += p.coefficient(k) * coefficient(i - k);
            }
        }
        return new PolynomialFunction(coef);
    }

    /**
     * @param r
     * @return DhbFunctionEvaluation.PolynomialFunction
     */
    public PolynomialFunction subtract(double r) {
        return add(-r);
    }

    /**
     * @param p DhbFunctionEvaluation.PolynomialFunction
     * @return DhbFunctionEvaluation.PolynomialFunction
     */
    public PolynomialFunction subtract(PolynomialFunction p) {
        final int n = max(p.degree(), degree()) + 1;
        final double[] coef = new double[n];
        for (int i = 0; i < n; i++) {
            coef[i] = coefficient(i) - p.coefficient(i);
        }
        return new PolynomialFunction(coef);
    }

    /**
     * Returns a string representing the receiver
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        boolean firstNonZeroCoefficientPrinted = false;
        for (int n = 0; n < coefficients.length; n++) {
            if (coefficients[n] != 0) {
                if (firstNonZeroCoefficientPrinted) {
                    sb.append(coefficients[n] > 0 ? " + " : " ");
                } else {
                    firstNonZeroCoefficientPrinted = true;
                }
                if (n == 0 || coefficients[n] != 1) {
                    sb.append(coefficients[n]);
                }
                if (n > 0) {
                    sb.append(" X^").append(n);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns the value of the polynomial for the specified variable value.
     *
     * @param x double value at which the polynomial is evaluated
     * @return double polynomial value.
     */
    @Override
    public Double value(Double x) {
        int n = coefficients.length;
        double answer = coefficients[--n];
        while (n > 0) {
            answer = answer * x + coefficients[--n];
        }
        return answer;
    }

    /**
     * Returns the value and the derivative of the polynomial for the specified
     * variable value in an array of two elements
     *
     * @param x double value at which the polynomial is evaluated
     * @return double[0] the value of the polynomial double[1] the derivative of
     * the polynomial
     * @version 1.2
     */
    public double[] valueAndDerivative(double x) {
        int n = coefficients.length;
        final double[] answer = new double[2];
        answer[0] = coefficients[--n];
        answer[1] = 0;
        while (n > 0) {
            answer[1] = answer[1] * x + answer[0];
            answer[0] = answer[0] * x + coefficients[--n];
        }
        return answer;
    }
}
