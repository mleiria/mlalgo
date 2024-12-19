package pt.mleiria.mlalgo.cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    public static void main(String[] args) {
        List<Point> data = generateRandomData(100);

        int k = 3; // Number of clusters
        List<Cluster> clusters = initializeClusters(data, k);

        for (int i = 0; i < 100; i++) {
            assignPointsToClusters(data, clusters);
            updateCentroids(clusters);
        }

        // Print the final clusters
        for (int i = 0; i < k; i++) {
            System.out.println("Cluster " + (i + 1) + ": " + clusters.get(i).points);
        }
    }

    private static List<Point> generateRandomData(int n) {
        List<Point> data = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            double x = rand.nextDouble() * 100;
            double y = rand.nextDouble() * 100;
            data.add(new Point(x, y));
        }

        return data;
    }

    public static List<Cluster> initializeClusters(List<Point> data, int k) {
        List<Cluster> clusters = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < k; i++) {
            Point centroid = data.get(rand.nextInt(data.size()));
            clusters.add(new Cluster(centroid));
        }

        return clusters;
    }

    public static void assignPointsToClusters(List<Point> data, List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            cluster.clearPoints();
        }

        for (Point point : data) {
            Cluster closestCluster = findClosestCluster(point, clusters);
            closestCluster.addPoint(point);
        }
    }

    private static Cluster findClosestCluster(Point point, List<Cluster> clusters) {
        Cluster closestCluster = null;
        double minDistance = Double.MAX_VALUE;

        for (Cluster cluster : clusters) {
            double distance = calculateDistance(point, cluster.getCentroid());
            if (distance < minDistance) {
                minDistance = distance;
                closestCluster = cluster;
            }
        }
        System.out.println(point.toString() + " belongs to cluster " + closestCluster);
        return closestCluster;
    }

    public static void updateCentroids(List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            double sumX = 0, sumY = 0;

            for (Point point : cluster.points) {
                sumX += point.x;
                sumY += point.y;
            }

            double centroidX = sumX / cluster.points.size();
            double centroidY = sumY / cluster.points.size();

            cluster.centroid.x = centroidX;
            cluster.centroid.y = centroidY;
        }
    }

    public static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
