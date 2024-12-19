package pt.mleiria.mlalgo.cluster;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    Point centroid;
    List<Point> points;

    Cluster(Point centroid) {
        this.centroid = centroid;
        this.points = new ArrayList<>();
    }

    void addPoint(Point point) {
        points.add(point);
    }

    void clearPoints() {
        points.clear();
    }

    Point getCentroid() {
        return centroid;
    }
}
