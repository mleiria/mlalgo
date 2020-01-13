/**
 *
 */
package pt.mleiria.mlalgo.stats.words;

/**
 *
 * The Word class that stores the string with the word and the measures of that
 * word (TF, DF, and TF-IDF). <p> TF (short for term frequency) is the number of
 * times that a word appears in a document. <p> DF (short for document frequency)
 * is the number of documents that contains a word. <p>The IDF (short for inverse
 * document frequency) measures the information that word provides to
 * distinguish a document from others. If a word is very common, it's IDF will
 * be low, but if the word appears in only a few documents, it's IDF will be
 * high.
 *
 *
 */
public class Word implements Comparable<Word> {

    private String word;
    private int tf;
    private int df;
    private double tfidf;

    /*
     * We want the words ordered from a higher to lower tfIdf attribute:
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Word o) {
        return Double.compare(o.getTfidf(), this.getTfidf());
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * The number of times the word appears in a document
     * @return the tf
     */
    public int getTf() {
        return tf;
    }

    /**
     * The number of times the word appears in a document
     * @param tf the tf to set
     */
    public void setTf(int tf) {
        this.tf = tf;
    }

    /**
     * The number of documents that contains the word
     * @return the df
     */
    public int getDf() {
        return df;
    }

    /**
     * The number of documents that contains the word
     * @param df the df to set
     */
    public void setDf(int df) {
        this.df = df;
    }

    /**
     *
     * @param df
     * @param N  the total number of documents in the collection
     */
    public void setDf(int df, int N) {
        this.df = df;
        tfidf = tf * Math.log(Double.valueOf(N) / df);
    }

    /**
     * @return the tfidf
     */
    public double getTfidf() {
        return tfidf;
    }

    /**
     * @param tfidf the tfidf to set
     */
    public void setTfidf(double tfidf) {
        this.tfidf = tfidf;
    }

}
