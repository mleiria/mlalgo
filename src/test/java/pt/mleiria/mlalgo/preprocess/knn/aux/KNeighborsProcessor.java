package pt.mleiria.mlalgo.preprocess.knn.aux;

import org.apache.commons.csv.CSVRecord;
import pt.mleiria.mlalgo.dataset.CsvReader;
import pt.mleiria.mlalgo.preprocess.KNeighborsClassifierTest;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class KNeighborsProcessor {

    private static final Logger LOG = Logger.getLogger(KNeighborsProcessor.class.getName());

    private final List<Integer> uniqueUsers;
    private final List<Integer> uniqueMovies;
    private final List<Movie> moviesList;
    // Map<MovieId, Map<UserId, Rating>>
    private final Map<Integer, Map<Integer, Double>> movieUserRatings;
    private final Double[][] movieUserMatrix;


    public KNeighborsProcessor(final List<Integer> uniqueUsers, final List<Integer> uniqueMovies,
                               List<Movie> moviesList) {
        this.uniqueUsers = uniqueUsers;
        this.uniqueMovies = uniqueMovies;
        this.moviesList = moviesList;
        this.movieUserRatings = moviesList.stream()
                .collect(
                        Collectors.groupingBy(Movie::movieId, Collectors.toMap(Movie::userId, Movie::rating,
                                (a, b) -> b)));
        this.movieUserMatrix = generateMatrix();
    }


    private Double[][] generateMatrix() {
        return uniqueMovies.stream()
                .map(movieId ->
                        uniqueUsers.stream()
                                .map(userId -> movieUserRatings.getOrDefault(movieId, Map.of())
                                        .getOrDefault(userId, 0.0))
                                .toArray(Double[]::new))
                .toArray(Double[][]::new);
    }

    public double extractRating(int userId, int movieId) {
        final Map<String, Integer> matrixCoords = getMatrixCoords(userId, movieId);
        final int userIndex = matrixCoords.get("userIndex");
        final int movieIndex = matrixCoords.get("movieIndex");
        final double rating = movieUserMatrix[movieIndex][userIndex];
        LOG.info("UserIndex: " + userIndex + " MovieIndex: " + movieIndex + " => " + rating);
        return rating;
    }

    private Map<String, Integer> getMatrixCoords(int userId, int movieId) {
        LOG.info("UserId: " + userId + " MovieId: " + movieId);
        int userIndex = getUserIdIndex(userId);
        int movieIndex = getMovieIdIndex(movieId);
        return Map.of("userIndex", userIndex, "movieIndex", movieIndex);
    }

    private int getMovieIdIndex(final int movieId) {
        return uniqueMovies.stream()
                .filter(m -> m == movieId)
                .findFirst()
                .map(uniqueMovies::indexOf)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    private int getUserIdIndex(final int userId) {
        return uniqueUsers.stream()
                .filter(u -> u == userId)
                .findFirst()
                .map(uniqueUsers::indexOf)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Double[] getDataFromMovieId(final int movieId) {

        // movieId,title,genres
        final String moviesFilePath = "src/test/resources/movies/movies.csv";
        final List<CSVRecord> movies = CsvReader.getCsvRecords(moviesFilePath);
        final Movie movie =
                movies.stream().filter(record -> record.get("movieId").equals(String.valueOf(movieId)))
                        .findFirst()
                        .map(record -> new Movie(0, movieId, 0.0, record.get("title")))
                        .orElseThrow(() -> new RuntimeException("Movie not found"));
        LOG.info("Found movie " + movie + " with id " + movieId);
        // Get row from matrix
        final int movieIndex = getMovieIdIndex(movieId);
        return movieUserMatrix[movieIndex];
    }

    public Double[][] getMovieUserMatrix() {
        return movieUserMatrix;
    }

    public Double[] getMoviesList() {
        return uniqueMovies.stream()
                .mapToDouble(Integer::doubleValue)
                .boxed()
                .toArray(Double[]::new);
    }

    public Map<Integer, Double> getMovieUserRatings(final int movieId) {
        return movieUserRatings.get(movieId);
    }
}
