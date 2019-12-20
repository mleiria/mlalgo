/**
 * 
 */
package pt.mleiria.mlalgo.words.preprocess;

import java.util.HashSet;
import java.util.Set;

/**
 * @author manuel
 *
 */
public class SimpleTokenizer implements Tokenizer {

    private final int minTokenSize;
    private Set<String> excludedTokens;

    /**
     * 
     * @param minTokenSize
     */
    public SimpleTokenizer(final int minTokenSize, final Set<String> excludedTokens) {
	this.minTokenSize = minTokenSize;
	this.excludedTokens = excludedTokens;
    }

    /**
     * 
     */
    public SimpleTokenizer(final int minTokenSize) {
	this(minTokenSize, new HashSet<>());
    }
    
    /**
     * 
     */
    public SimpleTokenizer() {
	this(0, new HashSet<>());
    }

    /*
     * (non-Javadoc)
     * 
     * @see pt.mleiria.mlalgo.words.preprocess.Tokenizer#getTokens(java.lang.String)
     */
    @Override
    public String[] getTokens(String document) {
	final String[] tokens = document.trim().replaceAll("_", " ").split("\\s+");
	final Set<String> cleanTokens = new HashSet<>();
	for (final String token : tokens) {
	    final String cleanToken = token.trim().toLowerCase().replaceAll("[^A-Za-z\']+", "");
	    if (cleanToken.length() > minTokenSize) {
		if (excludedTokens.isEmpty()) {
		    cleanTokens.add(cleanToken);
		} else {
		    if (!excludedTokens.contains(cleanToken)) {
			cleanTokens.add(cleanToken);
		    }
		}
	    }
	}
	return cleanTokens.toArray(new String[0]);
    }

}
