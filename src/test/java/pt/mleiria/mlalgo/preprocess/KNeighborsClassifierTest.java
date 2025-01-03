/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.preprocess;

import junit.framework.TestCase;
import org.apache.commons.csv.CSVRecord;
import pt.mleiria.mlalgo.core.Estimator;
import pt.mleiria.mlalgo.dataset.CsvReader;
import pt.mleiria.mlalgo.dataset.Dataset;
import pt.mleiria.mlalgo.dataset.DatasetBuilder;
import pt.mleiria.mlalgo.distance.CosineDistance;
import pt.mleiria.mlalgo.distance.EuclideanDistance;
import pt.mleiria.mlalgo.metrics.AccuracyScore;
import pt.mleiria.mlalgo.metrics.CrossValidationScore;
import pt.mleiria.mlalgo.metrics.Score;
import pt.mleiria.mlalgo.preprocess.knn.aux.KNeighborsProcessor;
import pt.mleiria.mlalgo.preprocess.knn.aux.Movie;
import pt.mleiria.mlalgo.utils.*;
import pt.mleiria.neighbors.classifier.KNeighborsClassifier;
import pt.mleiria.syntheticdata.DataFactory;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class KNeighborsClassifierTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(KNeighborsClassifierTest.class.getName());

    private VUtils<Number> v;
    private Score<Double[], Double[], Double> score;

    @Override
    protected void setUp() throws Exception {
        v = new VUtils<>();
        score = new AccuracyScore();
    }

    public void testPredictBankData() {
        final String dataFile = ResourceFileLoader.getFilePath("bank.data");
        final Dataset ds = DatasetBuilder.create(dataFile)
                .setHasRowHeader(false)
                .setIsLabelConversion(true)
                .setSeparator(";")
                .createDataSet();
        ds.loadDataset();
        final Double[][] x = ds.featuresX;
        final Double[] y = ds.labelsY;
        final Estimator<Double, Double> estimator = KNeighborsClassifier.create(3);
        estimator.fit(x, y);
        //Test set
        final String testFile = ResourceFileLoader.getFilePath("bank.test");
        final Dataset dsTest = DatasetBuilder.create(testFile)
                .setHasRowHeader(false)
                .setIsLabelConversion(true)
                .setSeparator(";")
                .createDataSet();
        dsTest.loadDataset();
        final Double[][] xTest = dsTest.featuresX;
        final Double[] yTest = dsTest.labelsY;

        final Double[] predY = estimator.predict(xTest);
        LOG.log(Level.INFO, "predY Bank Data:\n{0}", v.showContents(predY));
        LOG.log(Level.INFO, "Ground data Bank Data:\n{0}", v.showContents(yTest));
        LOG.log(Level.INFO, "Labels:{0}", ds.getLabelHolder().toString());
        final double accuracyScore = score.score(predY, yTest);
        LOG.log(Level.INFO, "Score: {0}", accuracyScore);
        assertEquals(0.904, accuracyScore, 0.01);
    }

    public void testPredict() {
        final Double[][] x = new Double[4][1];
        x[0][0] = 0.;
        x[1][0] = 1.;
        x[2][0] = 2.;
        x[3][0] = 3.;
        final Double[] y = new Double[]{0., 0., 1., 1.};
        final Estimator<Double, Double> knn = KNeighborsClassifier.create(3);
        knn.fit(x, y);
        final Double[][] value = new Double[1][1];
        value[0][0] = 1.1;
        LOG.log(Level.INFO, "Accuracy Score:{0}", score.score(knn.predict(value), new Double[]{0.}));
        assertEquals(0., knn.predict(value)[0]);
    }

    public void testPredictIris() {
        final Tuple2<Double[][], Double[]> ds = DataFactory.loadIrisDataset();
        final List<Tuple2<Double[][], Double[]>> splitter = Arrays2D.trainTestSplit(ds.getX(), ds.getY(), 0.66, true);
        final Double[][] trainX = splitter.get(0).getX();
        final Double[][] testX = splitter.get(1).getX();
        final Double[] trainY = splitter.get(0).getY();
        final Double[] testY = splitter.get(1).getY();
        final Estimator<Double, Double> estimator = KNeighborsClassifier.create(3).setDm(new EuclideanDistance());
        estimator.fit(trainX, trainY);
        final Double[] predY = estimator.predict(testX);
        LOG.log(Level.INFO, "predY:\n{0}", v.showContents(predY));
        LOG.log(Level.INFO, "testY:\n{0}", v.showContents(testY));
        LOG.log(Level.INFO, "Accuracy Score:{0}", score.score(predY, testY));
        LOG.log(Level.INFO, "Accuracy Score estimator:{0}", estimator.score(testX, testY));

        assertEquals(testY.length, predY.length);
    }

    public void testCrossValidation() {
        final Tuple2<Double[][], Double[]> ds = DataFactory.loadIrisDataset();
        final Double[][] trainX = ds.getX();
        final Double[] trainY = ds.getY();
        final Estimator<Double, Double> estimator = KNeighborsClassifier.create(3);
        estimator.fit(trainX, trainY);
        final Double res = CrossValidationScore.create(estimator)
                .setCv(5)
                .setShuffle(true)
                .score(trainX, trainY);
        LOG.info("Cross Validation:\n" + res);
        bestK(trainX, trainY);
    }

    public void bestK(Double[][] trainX, Double[] trainY) {
        int bestK = 0;
        double bestKScore = 0.;
        for (int k = 3; k < 10; k++) {
            final Estimator<Double, Double> estimator = KNeighborsClassifier.create(k);
            estimator.fit(trainX, trainY);
            final CrossValidationScore cv = CrossValidationScore.create(estimator)
                    .setCv(5)
                    .setShuffle(true);
            final Double res = cv.score(trainX, trainY);
            LOG.info("Cross Validation K:" + k);
            LOG.info("Cross Validation Values:\n" + res);
            LOG.info("Cross Validation Mean and Stdv:" + cv.mean() + " (+ -) " + cv.stdv());
            if (res > bestKScore) {
                bestKScore = res;
                bestK = k;
            }
        }
        LOG.info("Best k is: " + bestK + " with score: " + bestKScore);
        assertTrue(bestK < 10);
    }

    public void testPredictIrisThreeSample() {
        final String dataFileIris = ResourceFileLoader.getFilePath("iris.csv");
        final Dataset ds = DatasetBuilder.create(dataFileIris)
                .setHasRowHeader(true)
                .setIsLabelConversion(true)
                .setSeparator(",")
                .createDataSet();
        ds.loadDataset();
        final Double[][] x = ds.featuresX;
        final Double[] y = ds.labelsY;

        final Estimator<Double, Double> estimator = KNeighborsClassifier.create(3);
        estimator.fit(x, y);

        final Double[] predY = estimator.predict(getThreeSamples());
        LOG.log(Level.INFO, "3 Samples Pred:{0}", v.showContents(predY));
        LOG.info("Verginica-Versicolor-Setosa");
        LOG.info(ds.getLabelHolder().toString());

        assertEquals(3, predY.length);
    }

    public void testPredictIrisOneSampleVirginica() {
        final String dataFileIris = ResourceFileLoader.getFilePath("iris.csv");
        final Dataset ds = DatasetBuilder.create(dataFileIris)
                .setHasRowHeader(true)
                .setIsLabelConversion(true)
                .setSeparator(",")
                .createDataSet();
        ds.loadDataset();
        final Double[][] x = ds.featuresX;
        final Double[] y = ds.labelsY;

        final Estimator<Double, Double> estimator = KNeighborsClassifier.create(3);
        estimator.fit(x, y);

        final Double[] predY = estimator.predict(getOneSampleVirginica());
        LOG.log(Level.INFO, "predY One Sample verginica:\n{0}", v.showContents(predY));
        LOG.log(Level.INFO, "Labels:{0}", ds.getLabelHolder().toString());
        assertEquals(1, predY.length);
    }

    public void testPredictIrisOneSampleSetosa() {
        final String dataFileIris = ResourceFileLoader.getFilePath("iris.csv");
        final Dataset ds = DatasetBuilder.create(dataFileIris)
                .setHasRowHeader(true)
                .setIsLabelConversion(true)
                .setSeparator(",")
                .createDataSet();
        ds.loadDataset();
        final Double[][] x = ds.featuresX;
        final Double[] y = ds.labelsY;

        final Estimator<Double, Double> estimator = KNeighborsClassifier.create(3);
        estimator.fit(x, y);

        final Double[] predY = estimator.predict(getOneSampleSetosa());
        LOG.log(Level.INFO, "predY One Sample Setosa:\n{0}", v.showContents(predY));
        LOG.log(Level.INFO, "Labels:{0}", ds.getLabelHolder().toString());
        assertEquals(1, predY.length);
    }

    public void testPredictIrisOneSampleVersicolor() {
        final String dataFileIris = ResourceFileLoader.getFilePath("iris.csv");
        final Dataset ds = DatasetBuilder.create(dataFileIris)
                .setHasRowHeader(true)
                .setIsLabelConversion(true)
                .setSeparator(",")
                .createDataSet();
        ds.loadDataset();
        final Double[][] x = ds.featuresX;
        final Double[] y = ds.labelsY;

        final Estimator<Double, Double> estimator = KNeighborsClassifier.create(3);
        estimator.fit(x, y);

        final Double[] predY = estimator.predict(getOneSampleVersicolor());
        LOG.info("predY One Sample Versicolor:\n" + v.showContents(predY));
        LOG.info("Labels:" + ds.getLabelHolder().toString());
        assertEquals(1, predY.length);
    }

    private Double[][] getOneSampleVirginica() {
        //5.9, 3.0, 5.1, 1.8, Iris-virginica
        //5.5,2.3,4.0,1.3,Iris-versicolor
        //4.4,3.2,1.3,0.2,Iris-setosa
        final Double[][] oneSample = new Double[1][4];
        oneSample[0][0] = 5.9;
        oneSample[0][1] = 3.0;
        oneSample[0][2] = 5.1;
        oneSample[0][3] = 1.8;
        return oneSample;

    }

    private Double[][] getOneSampleVersicolor() {
        //5.9, 3.0, 5.1, 1.8, Iris-virginica
        //5.5, 2.3, 4.0, 1.3, Iris-versicolor
        //4.4,3.2,1.3,0.2,Iris-setosa
        final Double[][] oneSample = new Double[1][4];
        oneSample[0][0] = 5.5;
        oneSample[0][1] = 2.3;
        oneSample[0][2] = 4.0;
        oneSample[0][3] = 1.3;
        return oneSample;

    }

    private Double[][] getOneSampleSetosa() {
        //5.9, 3.0, 5.1, 1.8, Iris-virginica
        //5.5,2.3,4.0,1.3,Iris-versicolor
        //4.4, 3.2, 1.3, 0.2, Iris-setosa
        final Double[][] oneSample = new Double[1][4];
        oneSample[0][0] = 4.4;
        oneSample[0][1] = 3.2;
        oneSample[0][2] = 1.3;
        oneSample[0][3] = 0.2;
        return oneSample;

    }

    private Double[][] getThreeSamples() {
        final Double[][] res = new Double[3][4];
        res[0][0] = 5.9;
        res[0][1] = 3.0;
        res[0][2] = 5.1;
        res[0][3] = 1.8;

        res[1][0] = 5.5;
        res[1][1] = 2.3;
        res[1][2] = 4.0;
        res[1][3] = 1.3;

        res[2][0] = 4.4;
        res[2][1] = 3.2;
        res[2][2] = 1.3;
        res[2][3] = 0.2;
        return res;
    }

    public void testMoviesDataset() {
        StopWatch stopWatch = new StopWatch();
        final KNeighborsProcessor knnProcessor = getKNeighborsProcessor();
        final Double[][] x = knnProcessor.getMovieUserMatrix();
        LOG.info(v.showContents(x, 10));
        LOG.info(stopWatch.elapsedTimeToString());

        double r1 = knnProcessor.extractRating(7, 1); //4.5
        double r2 = knnProcessor.extractRating(6, 4); //3.0
        double r3 = knnProcessor.extractRating(608, 3); //2.0
        double r4 = knnProcessor.extractRating(604, 5); //3.0
        assertEquals(4.5, r1);
        assertEquals(3.0, r2);
        assertEquals(2.0, r3);
        assertEquals(3.0, r4);
        // Some queries to see the data
        final int matrixMovieId = 2571; // Movie Matrix
        knnProcessor.getDataFromMovieId(matrixMovieId);

        KNeighborsClassifier knn = KNeighborsClassifier.create(30);
        knn.setDm(new EuclideanDistance());
        knn.fit(x, knnProcessor.getMoviesList());
        // Prediction for matrix movie
        LOG.info("Matrix ratings:" +knnProcessor.getMovieUserRatings(matrixMovieId));
        final Double[] testX = knnProcessor.getDataFromMovieId(matrixMovieId);
        final Double[][] test = new Double[1][];
        test[0] = testX;
        final Double[] predY = knn.predict(test);
        LOG.info("Prediction for movie 2571: " + predY[0]);
        knnProcessor.getDataFromMovieId(predY[0].intValue());



    }

    private static KNeighborsProcessor getKNeighborsProcessor() {
        final String ratingsFilePath = "src/test/resources/movies/ratings.csv";
        // userId,movieId,rating,timestamp
        final List<CSVRecord> ratings = CsvReader.getCsvRecords(ratingsFilePath);
        // Create an Double[movieId][userId]
        final List<Movie> moviesList =
                ratings.stream()
                        .map(rating -> {
                            final int userId = Integer.parseInt(rating.get("userId"));
                            final int movieId = Integer.parseInt(rating.get("movieId"));
                            final double ratingValue = Double.parseDouble(rating.get("rating"));
                            return new Movie(userId, movieId, ratingValue, "");
                        })
                        .toList();
        final List<Integer> uniqueUsers = moviesList.stream()
                .map(Movie::userId)
                .distinct()
                .toList();
        final List<Integer> uniqueMovies = moviesList.stream()
                .map(Movie::movieId)
                .distinct()
                .toList();
        final KNeighborsProcessor knnProcessor = new KNeighborsProcessor(uniqueUsers, uniqueMovies, moviesList);

        LOG.info("Creating the ratings matrix...");
        return knnProcessor;
    }


}
