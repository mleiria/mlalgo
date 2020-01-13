/**
 *
 */
package pt.mleiria.mlalgo.words.preprocess;

import java.util.Set;

/**
 * @author manuel
 *
 */
public interface Dictionary {

    Integer getWordCount(String word);

    int getNumWords();

    Set<String> getBagOfWords();

}
