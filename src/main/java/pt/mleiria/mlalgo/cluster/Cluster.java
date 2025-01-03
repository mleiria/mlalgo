package pt.mleiria.mlalgo.cluster;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private int id;
    private Point centroid;
    private List<Point> points;

    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void clearPoints() {
        points.clear();
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    @Override
    public String toString() {
        return "Cluster " + id + " centroid: " + centroid + " points: " + points;
    }
}
