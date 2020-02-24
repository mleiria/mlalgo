package pt.mleiria.mlalgo.dataset.vo.imdb;

import java.util.logging.Logger;

public interface Imdb {

    Logger LOG = Logger.getLogger(Imdb.class.getName());

    void setData(final String[] data);

    String getPrincipalActor();

    String getMovieTitle();

    String[] getMovieGenres();

    double getMovieRating();

    int getNumberOfVotes();

    int getNumColumns();


    /**
     * @param str
     * @return
     */
    static int parseInt(final String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LOG.warning("Parse Int Ex: " + e.getMessage());
            return -1;
        }
    }

    /**
     * @param str
     * @return
     */
    static double parseDouble(final String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            LOG.warning("Parse Double Ex: " + e.getMessage());
            return -1.0;
        }
    }

}
