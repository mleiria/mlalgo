/**
 *
 */
package pt.mleiria.mlalgo.words;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Logger;

import junit.framework.TestCase;
import pt.mleiria.mlalgo.conf.DatasetsLocation;
import pt.mleiria.mlalgo.stats.words.WordsIndexer;

/**
 * @author manuel
 *
 */
public class TestWords extends TestCase {

    private static final Logger LOG = Logger.getLogger(TestWords.class.getName());

    public void test_correct_size() {
        final WordsIndexer wi = new WordsIndexer(8, DatasetsLocation.WIKI_MOVIE_DATA, false);
        assertNotNull(wi);
/*
        wi.indexWords();

        final int size = wi.getInvertedIndex().size();
        LOG.info("Inverted Index size: " + size);
        Set<Map.Entry<String, ConcurrentLinkedDeque<String>>> set = wi.getInvertedIndex().entrySet();


        for (Map.Entry<String, ConcurrentLinkedDeque<String>> elem : set) {

            LOG.info(elem.getKey() + " : " + elem.getValue());
        }
        assertEquals(657378, size);
*/
    }

}
