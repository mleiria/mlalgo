package pt.mleiria.mlalgo.stats.words;

/**
 * 
 * The Keyword class stores information about a keyword. This information
 * includes the whole word and the number of documents in which this word is a
 * keyword.
 *
 */
public class Keyword implements Comparable<Keyword> {

    private String word;
    private int df;

    @Override
    public int compareTo(Keyword o) {
	return Integer.compare(o.getDf(), this.getDf());
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
     * @return the df
     */
    public int getDf() {
	return df;
    }

    /**
     * @param df the df to set
     */
    public void setDf(int df) {
	this.df = df;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if(this.getClass() != obj.getClass()) {
	    return false;
	}
	final Keyword anotherKeyWord = (Keyword) obj;
	return word.equals(anotherKeyWord.getWord()) && df == anotherKeyWord.getDf();
    }
    
}
