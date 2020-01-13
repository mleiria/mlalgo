package pt.mleiria.mlalgo.stats.words;

import pt.mleiria.mlalgo.tasks.IndexingTask;
import pt.mleiria.mlalgo.tasks.InvertedIndexTask;
import pt.mleiria.mlalgo.tasks.ThreadPoolManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class WordsIndexer extends ThreadPoolManager {

    private static final Logger LOG = Logger.getLogger(WordsIndexer.class.getName());

    private int numTasks;
    private String folderLocation;
    private boolean includeSubFolders;
    private final ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> invertedIndex;
    private File[] files;

    /**
     * numTasks = numCores
     */
    public WordsIndexer() {
        this.numTasks = numCores;
        invertedIndex = new ConcurrentHashMap<>();

    }

    /**
     * @param numTasks
     * @param folderLocation
     * @param includeSubFolders true if is to scan all folders and subfolders
     */
    public WordsIndexer(final int numTasks, final String folderLocation, final boolean includeSubFolders) {
        this.numTasks = numTasks;
        this.folderLocation = folderLocation;
        this.includeSubFolders = includeSubFolders;
        invertedIndex = new ConcurrentHashMap<>();
        getFiles();
    }

    /**
     * numTasks = numCores
     *
     * @param folderLocation
     * @param includeSubFolders true if is to scan all folders and subfolders
     */
    public WordsIndexer(final String folderLocation, final boolean includeSubFolders) {
        this.numTasks = numCores;
        this.folderLocation = folderLocation;
        this.includeSubFolders = includeSubFolders;
        invertedIndex = new ConcurrentHashMap<>();
        getFiles();
    }

    /**
     * @return
     */
    private void getFiles() {
        if (includeSubFolders) {
            try {
                final File[] files = Files.walk(Paths.get(folderLocation))
                        .filter(Files::isRegularFile)
                        .map(elem -> elem.toFile())
                        .toArray(File[]::new);
                this.files = files;
            } catch (IOException e) {
                LOG.severe(e.getMessage());
            }
        } else {
            final File source = new File(folderLocation);
            final File[] files = source.listFiles();
            this.files = files;
        }
    }

    /**
     * @param filesPath
     */
    public void setFiles(final String[] filesPath) {
        files = Arrays.stream(filesPath)
                .map(elem -> new File(elem))
                .toArray(File[]::new);
    }

    /**
     *
     */
    public void indexWords() {
        final ExecutorCompletionService<List<Document>> completionService = new ExecutorCompletionService<>(executor);

        List<File> taskFiles = new ArrayList<>();

        for (final File file : files) {
            taskFiles.add(file);
            if (taskFiles.size() == numTasks) {
                IndexingTask task = new IndexingTask(taskFiles);
                completionService.submit(task);
                taskFiles = new ArrayList<>();
            }
        }
        if (!taskFiles.isEmpty()) {
            IndexingTask task = new IndexingTask(taskFiles);
            completionService.submit(task);
        }

        final InvertedIndexTask invertedIndexTask = new InvertedIndexTask(completionService, invertedIndex);
        final Thread thread1 = new Thread(invertedIndexTask);
        thread1.start();
        final InvertedIndexTask invertedIndexTask2 = new InvertedIndexTask(completionService, invertedIndex);
        final Thread thread2 = new Thread(invertedIndexTask2);
        thread2.start();

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
            thread1.interrupt();
            thread2.interrupt();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        thread2.interrupt();
    }

    /**
     * @return
     */
    public ConcurrentMap<String, ConcurrentLinkedDeque<String>> getInvertedIndex() {
        return invertedIndex;
    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }
}
