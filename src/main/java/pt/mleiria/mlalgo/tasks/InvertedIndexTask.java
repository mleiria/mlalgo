/**
 *
 */
package pt.mleiria.mlalgo.tasks;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import pt.mleiria.mlalgo.stats.words.Document;
import pt.mleiria.mlalgo.stats.words.Word;

/**
 * @author manuel
 * Stores all the
 * words of the document collection and a list of the documents that contains
 * that word.
 */
public class InvertedIndexTask implements Runnable {

    private final CompletionService<List<Document>> completionService;
    private final ConcurrentMap<String, ConcurrentLinkedDeque<String>> invertedIndex;

    /**
     *
     * @param completionService
     * @param invertedIndex
     */
    public InvertedIndexTask(final CompletionService<List<Document>> completionService,
                             final ConcurrentMap<String, ConcurrentLinkedDeque<String>> invertedIndex) {

        this.completionService = completionService;
        this.invertedIndex = invertedIndex;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                try {
                    final List<Document> documents = completionService.take().get();
                    for (final Document document : documents) {
                        updateInvertedIndex(document.getVoc(), invertedIndex, document.getFileName());
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
            while (true) {
                final Future<List<Document>> future = completionService.poll();
                if (future == null)
                    break;
                final List<Document> documents = future.get();
                for (final Document document : documents) {
                    updateInvertedIndex(document.getVoc(), invertedIndex, document.getFileName());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param voc
     * @param invertedIndex
     * @param fileName
     */
    private void updateInvertedIndex(Map<String, Word> voc,
                                     ConcurrentMap<String, ConcurrentLinkedDeque<String>> invertedIndex, String fileName) {

        for (final String word : voc.keySet()) {
            if (word.length() >= 3) {
                invertedIndex.computeIfAbsent(word, k -> new ConcurrentLinkedDeque<>()).add(fileName);
            }
        }
    }
}
