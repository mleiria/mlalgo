package pt.mleiria.mlalgo.cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeansDemo {
    private final int k;
    private final int maxIterations;
    private List<Point> points;
    private List<Cluster> clusters;

    public KMeansDemo(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.points = new ArrayList<>();
        this.clusters = new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1.0, 2.0));
        points.add(new Point(2.0, 3.0));
        points.add(new Point(3.0, 4.0));
        points.add(new Point(8.0, 9.0));
        points.add(new Point(9.0, 10.0));
        points.add(new Point(10.0, 11.0));

        KMeansDemo kMeans = new KMeansDemo(2, 100);
        kMeans.init(points);
        kMeans.calculate();

        for (int i = 0; i < kMeans.getClusters().size(); i++) {
            Cluster cluster = kMeans.getClusters().get(i);
            System.out.println("Cluster " + i + " centroid: " + cluster.getCentroid());
            System.out.println("Cluster " + i + " points: " + cluster.getPoints());
        }
    }

    public void init(List<Point> points) {
        this.points = points;

        // Initialize clusters
        for (int i = 0; i < k; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = points.get(new Random().nextInt(points.size()));
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        // Assign initial points
        assignClusters();
    }

    public void calculate() {
        boolean finish = false;
        int iteration = 0;

        while (!finish && iteration < maxIterations) {
            assignClusters();

            List<Point> lastCentroids = getCentroids();
            updateCentroids();

            iteration++;

            List<Point> currentCentroids = getCentroids();

            // Check convergence
            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.distance(lastCentroids.get(i), currentCentroids.get(i));
            }
            if (distance == 0) {
                finish = true;
            }
        }
    }

    private void assignClusters() {
        clusters.forEach(Cluster::clearPoints);

        for (Point point : points) {
            double minDistance = Double.MAX_VALUE;
            int clusterIndex = 0;

            for (int i = 0; i < clusters.size(); i++) {
                Cluster cluster = clusters.get(i);
                double distance = Point.distance(point, cluster.getCentroid());
                if (distance < minDistance) {
                    minDistance = distance;
                    clusterIndex = i;
                }
            }

            clusters.get(clusterIndex).addPoint(point);
        }
    }

    private void updateCentroids() {
        for (Cluster cluster : clusters) {
            List<Point> points = cluster.getPoints();
            double sumX = 0;
            double sumY = 0;
            for (Point point : points) {
                sumX += point.getX();
                sumY += point.getY();
            }
            Point centroid = new Point(sumX / points.size(), sumY / points.size());
            cluster.setCentroid(centroid);
        }
    }

    private List<Point> getCentroids() {
        List<Point> centroids = new ArrayList<>(k);
        for (Cluster cluster : clusters) {
            centroids.add(cluster.getCentroid());
        }
        return centroids;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }
}
