package pt.mleiria.mlalgo.cluster;

import junit.framework.TestCase;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class KMeansTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(KMeansTest.class.getName());

    public void testKMeansClustering() {
        Double[][] xTrain = new Double[6][2];
        xTrain[0][0] = 1.;
        xTrain[0][1] = 2.;
        xTrain[1][0] = 2.;
        xTrain[1][1] = 3.;
        xTrain[2][0] = 3.;
        xTrain[2][1] = 4.;
        xTrain[3][0] = 8.;
        xTrain[3][1] = 9.;
        xTrain[4][0] = 9.;
        xTrain[4][1] = 10.;
        xTrain[5][0] = 10.;
        xTrain[5][1] = 11.;
        /*
        List<Point> points = new ArrayList<>();
        points.add(new Point(1.0, 2.0));
        points.add(new Point(2.0, 3.0));
        points.add(new Point(3.0, 4.0));
        points.add(new Point(8.0, 9.0));
        points.add(new Point(9.0, 10.0));
        points.add(new Point(10.0, 11.0));
        */
        KMeans kMeans = new KMeans();
        kMeans.setClusterSize(2)
                .setMaxIterations(300)
                .fit(xTrain, null);


        Double[] prediction = kMeans.predict(new Double[][]{{2.5, 1.0}});
        LOG.info("Centroid: " + prediction[0] + " " + prediction[1]);
        assertEquals(2.0, prediction[0], 0.1);
        assertEquals(3.0, prediction[1], 0.1);
        Double[] prediction1 = kMeans.predict(new Double[][]{{25.0, 11.0}});
        LOG.info("Centroid: " + prediction1[0] + " " + prediction1[1]);
        assertEquals(9.0, prediction1[0], 0.1);
        assertEquals(10.0, prediction1[1], 0.1);
    }


}