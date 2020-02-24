package pt.mleiria.mlalgo.dataset.vo.imdb;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * ["color", "director_name", "num_critic_for_reviews", "duration",
 * "director_facebook_likes", "actor_3_facebook_likes", "actor_2_name",
 * "actor_1_facebook_likes", "gross", "genres", "actor_1_name",
 * "movie_title", "num_voted_users", "cast_total_facebook_likes",
 * "actor_3_name", "facenumber_in_poster", "plot_keywords",
 * "movie_imdb_link", "num_user_for_reviews", "language", "country",
 * "content_rating", "budget", "title_year", "actor_2_facebook_likes",
 * "imdb_score", "aspect_ratio", "movie_facebook_likes"]
 */
public class ImdbVOBig implements Imdb {

    private static final Logger LOG = Logger.getLogger(ImdbVO.class.getName());

    private final String[] headers = new String[]{"color", "director_name", "num_critic_for_reviews", "duration",
            "director_facebook_likes", "actor_3_facebook_likes", "actor_2_name",
            "actor_1_facebook_likes", "gross", "genres", "actor_1_name",
            "movie_title", "num_voted_users", "cast_total_facebook_likes",
            "actor_3_name", "facenumber_in_poster", "plot_keywords",
            "movie_imdb_link", "num_user_for_reviews", "language", "country",
            "content_rating", "budget", "title_year", "actor_2_facebook_likes",
            "imdb_score", "aspect_ratio", "movie_facebook_likes"};

    private String color;
    private String directorName;
    private int numCriticsForReview;
    private double duration;
    private int directorFacebookLikes;
    private int actor3FacebookLikes;
    private String actor2Name;
    private int actor1FacebookLikes;
    private double gross;
    private String genres;
    private String actor1Name;
    private String movieTitle;
    private int numVotedUsers;
    private int castTotalFacebookLikes;
    private String actor3Name;
    private double faceNumberInPoster;
    private String plotKeywords;
    private String movieImdbLink;
    private int numUserForReviews;
    private String language;
    private String country;
    private String contentRating;
    private double budget;
    private int titleYear;
    private int actor2FacebookLikes;
    private double imdbScore;
    private double aspectRatio;
    private int movieFacebookLikes;

    private int numColumns;

    @Override
    public void setData(final String[] data) {
        numColumns = data.length;
        if (data.length != headers.length) {
            LOG.warning("Wrong data length: " + data.length);
            LOG.warning(Arrays.toString(data));
        }
        try {
            color = data[0];
            directorName = data[1];
            numCriticsForReview = Imdb.parseInt(data[2]);
            duration = Imdb.parseDouble(data[3]);
            directorFacebookLikes = Imdb.parseInt(data[4]);
            actor3FacebookLikes = Imdb.parseInt(data[5]);
            actor2Name = data[6];
            actor1FacebookLikes = Imdb.parseInt(data[7]);
            gross = Imdb.parseDouble(data[8]);
            genres = data[9];
            actor1Name = data[10];
            movieTitle = data[11];
            numVotedUsers = Imdb.parseInt(data[12]);
            castTotalFacebookLikes = Imdb.parseInt(data[13]);
            actor3Name = data[14];
            faceNumberInPoster = Imdb.parseDouble(data[15]);
            plotKeywords = data[16];
            movieImdbLink = data[17];
            numUserForReviews = Imdb.parseInt(data[18]);
            language = data[19];
            country = data[20];
            contentRating = data[21];
            budget = Imdb.parseDouble(data[22]);
            titleYear = Imdb.parseInt(data[23]);
            actor2FacebookLikes = Imdb.parseInt(data[24]);
            imdbScore = Imdb.parseDouble(data[25]);
            aspectRatio = Imdb.parseDouble(data[26]);
            movieFacebookLikes = Imdb.parseInt(data[27]);

        } catch (IndexOutOfBoundsException iob) {
            LOG.warning("Missing elements: " + iob.getMessage());
        }
    }

    @Override
    public String getPrincipalActor() {
        return getActor1Name();
    }
    @Override
    public int getNumColumns(){
        return numColumns;
    }
    @Override
    public String[] getMovieGenres(){
        return genres.split(",");
    }
    @Override
    public double getMovieRating(){
        return getImdbScore();
    }
    @Override
    public int getNumberOfVotes() {
        return getNumVotedUsers();
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getNumCriticsForReview() {
        return numCriticsForReview;
    }

    public void setNumCriticsForReview(int numCriticsForReview) {
        this.numCriticsForReview = numCriticsForReview;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getDirectorFacebookLikes() {
        return directorFacebookLikes;
    }

    public void setDirectorFacebookLikes(int directorFacebookLikes) {
        this.directorFacebookLikes = directorFacebookLikes;
    }

    public int getActor3FacebookLikes() {
        return actor3FacebookLikes;
    }

    public void setActor3FacebookLikes(int actor3FacebookLikes) {
        this.actor3FacebookLikes = actor3FacebookLikes;
    }

    public String getActor2Name() {
        return actor2Name;
    }

    public void setActor2Name(String actor2Name) {
        this.actor2Name = actor2Name;
    }

    public int getActor1FacebookLikes() {
        return actor1FacebookLikes;
    }

    public void setActor1FacebookLikes(int actor1FacebookLikes) {
        this.actor1FacebookLikes = actor1FacebookLikes;
    }

    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getActor1Name() {
        return actor1Name;
    }

    public void setActor1Name(String actor1Name) {
        this.actor1Name = actor1Name;
    }

    @Override
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getNumVotedUsers() {
        return numVotedUsers;
    }

    public void setNumVotedUsers(int numVotedUsers) {
        this.numVotedUsers = numVotedUsers;
    }

    public int getCastTotalFacebookLikes() {
        return castTotalFacebookLikes;
    }

    public void setCastTotalFacebookLikes(int castTotalFacebookLikes) {
        this.castTotalFacebookLikes = castTotalFacebookLikes;
    }

    public String getActor3Name() {
        return actor3Name;
    }

    public void setActor3Name(String actor3Name) {
        this.actor3Name = actor3Name;
    }

    public double getFaceNumberInPoster() {
        return faceNumberInPoster;
    }

    public void setFaceNumberInPoster(double faceNumberInPoster) {
        this.faceNumberInPoster = faceNumberInPoster;
    }

    public String getPlotKeywords() {
        return plotKeywords;
    }

    public void setPlotKeywords(String plotKeywords) {
        this.plotKeywords = plotKeywords;
    }

    public String getMovieImdbLink() {
        return movieImdbLink;
    }

    public void setMovieImdbLink(String movieImdbLink) {
        this.movieImdbLink = movieImdbLink;
    }

    public int getNumUserForReviews() {
        return numUserForReviews;
    }

    public void setNumUserForReviews(int numUserForReviews) {
        this.numUserForReviews = numUserForReviews;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getTitleYear() {
        return titleYear;
    }

    public void setTitleYear(int titleYear) {
        this.titleYear = titleYear;
    }

    public int getActor2FacebookLikes() {
        return actor2FacebookLikes;
    }

    public void setActor2FacebookLikes(int actor2FacebookLikes) {
        this.actor2FacebookLikes = actor2FacebookLikes;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public int getMovieFacebookLikes() {
        return movieFacebookLikes;
    }

    public void setMovieFacebookLikes(int movieFacebookLikes) {
        this.movieFacebookLikes = movieFacebookLikes;
    }

    @Override
    public String toString() {
        return "ImdbVOBig{" +
                "color='" + color + '\'' +
                ", directorName='" + directorName + '\'' +
                ", numCriticsForReview=" + numCriticsForReview +
                ", duration=" + duration +
                ", directorFacebookLikes=" + directorFacebookLikes +
                ", actor3FacebookLikes=" + actor3FacebookLikes +
                ", actor2Name='" + actor2Name + '\'' +
                ", actor1FacebookLikes=" + actor1FacebookLikes +
                ", gross=" + gross +
                ", genres='" + genres + '\'' +
                ", actor1Name='" + actor1Name + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", numVotedUsers=" + numVotedUsers +
                ", castTotalFacebookLikes=" + castTotalFacebookLikes +
                ", actor3Name='" + actor3Name + '\'' +
                ", faceNumberInPoster=" + faceNumberInPoster +
                ", plotKeywords='" + plotKeywords + '\'' +
                ", movieImdbLink='" + movieImdbLink + '\'' +
                ", numUserForReviews=" + numUserForReviews +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", contentRating='" + contentRating + '\'' +
                ", budget=" + budget +
                ", titleYear=" + titleYear +
                ", actor2FacebookLikes=" + actor2FacebookLikes +
                ", imdbScore=" + imdbScore +
                ", aspectRatio=" + aspectRatio +
                ", movieFacebookLikes=" + movieFacebookLikes +
                '}';
    }
}
