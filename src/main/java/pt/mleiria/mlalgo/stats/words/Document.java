/**
 *
 */
package pt.mleiria.mlalgo.stats.words;

import java.util.Map;
import java.util.Set;

import pt.mleiria.mlalgo.words.preprocess.Dictionary;

/**
 * The Document class that stores the name of the file that contains the
 * document and the words that form it
 *
 */
public class Document implements Dictionary {

    private final String fileName;
    private final Map<String, Word> voc;

    /**
     *
     * @param fileName
     * @param voc
     */
    public Document(final String fileName, final Map<String, Word> voc) {
        this.fileName = fileName;
        this.voc = voc;
    }

    /**
     * the number of times a word appear in the document
     *
     * @return the vocWord
     */
    public Map<String, Word> getVoc() {
        return voc;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     */
    @Override
    public Integer getWordCount(String term) {
        return getVoc().getOrDefault(term, new Word()).getTf();
    }

    /**
     *
     */
    @Override
    public int getNumWords() {
        return getVoc().size();
    }

    /**
     *
     */
    @Override
    public Set<String> getBagOfWords() {
        return getVoc().keySet();
    }

}
