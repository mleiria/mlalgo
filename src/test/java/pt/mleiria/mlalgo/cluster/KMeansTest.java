package pt.mleiria.mlalgo.cluster;

import org.junit.Ignore;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KMeansTest {
    @Ignore
    public void testKMeansClustering() {
        // Generate test data with known clusters
        Point[] testData = {
                new Point(1, 1),
                new Point(1, 2),
                new Point(2, 2),
                new Point(5, 5),
                new Point(5, 6),
                new Point(6, 6),
                new Point(10, 10),
                new Point(11, 11),
                new Point(12, 12)
        };

        int k = 3; // Number of clusters

        List<Point> data = Arrays.asList(testData);
        List<Cluster> clusters = KMeans.initializeClusters(data, k);

        // Run K-means clustering algorithm
        for (int i = 0; i < 100; i++) {
            KMeans.assignPointsToClusters(data, clusters);
            KMeans.updateCentroids(clusters);
        }

        // Test the number of clusters
        System.out.println( "Number of clusters should be " + k);
        System.out.println( "Actual clusters should be " + clusters.size());
        assertEquals(k, clusters.size());

        // Test each cluster
        for (int i = 0; i < k; i++) {
            Cluster cluster = clusters.get(i);

            // Test that each point in the cluster is close to the centroid
            for (Point point : cluster.points) {
                double distance = KMeans.calculateDistance(point, cluster.getCentroid());
                System.out.println("Point should be close to the centroid");
                assertEquals(0, distance, 0.1);
            }
        }
    }
}