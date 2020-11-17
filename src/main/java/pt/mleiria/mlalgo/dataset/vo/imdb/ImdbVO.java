/**
 *
 */
package pt.mleiria.mlalgo.dataset.vo.imdb;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Rank,Title,Genre,Description,Director,Actors,Year,Runtime
 * (Minutes),Rating,Votes,Revenue (Millions),Metascore
 *
 * @author manuel
 *
 */
public class ImdbVO implements Imdb {
    private static final Logger LOG = Logger.getLogger(ImdbVO.class.getName());

    private final String[] headers = new String[]{"Rank", "Title", "Genre", "Description", "Director", "Actors",
            "Year", "Runtime (Minutes)", "Rating", "Votes", "Revenue (Millions)", "Metascore"};

    private int rank;
    private String title;
    private String genre;
    private String description;
    private String director;
    private String actors;
    private int year;
    private int runtimeMinutes;
    private double rating;
    private int votes;
    private double revenueMillions;
    private int metascore;

    private int numColumns;


    /**
     *
     * @param data
     */
    @Override
    public void setData(final String[] data) {
        numColumns = data.length;
        if (data.length != headers.length) {
            LOG.warning("Wrong data length: " + data.length);
            LOG.warning(Arrays.toString(data));
        }
        try {
            rank = Imdb.parseInt(data[0]);
            title = data[1];
            genre = data[2];
            description = data[3];
            director = data[4];
            actors = data[5];
            year = Imdb.parseInt(data[6]);
            runtimeMinutes = Imdb.parseInt(data[7]);
            rating = Imdb.parseDouble(data[8]);
            votes = Imdb.parseInt(data[9]);
            revenueMillions = data[10].isEmpty() ? -1.0d : Imdb.parseDouble(data[10]);
            metascore = Imdb.parseInt(data[11]);
        } catch (IndexOutOfBoundsException iob) {
            LOG.warning("Missing elements: " + iob.getMessage());
        }

    }


    @Override
    public String getPrincipalActor() {
        return getActors();
    }

    @Override
    public int getNumColumns() {
        return numColumns;
    }

    @Override
    public String getMovieTitle() {
        return getTitle();
    }

    @Override
    public String[] getMovieGenres() {
        return genre.split(",");
    }

    @Override
    public double getMovieRating() {
        return getRating();
    }

    @Override
    public int getNumberOfVotes() {
        return getVotes();
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getRevenueMillions() {
        return revenueMillions;
    }

    public void setRevenueMillions(double revenueMillions) {
        this.revenueMillions = revenueMillions;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String[] getHeaders() {
        return headers;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ImdbVO [rank=" + rank + ", title=" + title + ", genre=" + genre + ", description=" + description
                + ", director=" + director + ", actors=" + actors + ", year=" + year + ", runtimeMinutes="
                + runtimeMinutes + ", rating=" + rating + ", votes=" + votes + ", revenueMillions=" + revenueMillions
                + ", metascore=" + metascore + "]";
    }


}
