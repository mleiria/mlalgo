package pt.mleiria.mlalgo.cluster;


import pt.mleiria.mlalgo.distance.EuclideanDistance;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double distance(Point p1, Point p2) {
        final Double[] x = {p1.getX(), p1.getY()};
        final Double[] y = {p2.getX(), p2.getY()};
        return new EuclideanDistance().calculate(x, y);
        //return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
