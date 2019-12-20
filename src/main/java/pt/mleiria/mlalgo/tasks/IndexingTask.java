package pt.mleiria.mlalgo.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import pt.mleiria.mlalgo.loader.DocumentLoader;
import pt.mleiria.mlalgo.stats.words.Document;
import pt.mleiria.mlalgo.stats.words.Word;

public class IndexingTask implements Callable<List<Document>> {

    private final List<File> files;

    public IndexingTask(final List<File> files) {
        this.files = files;
    }

    @Override
    public List<Document> call() throws Exception {
        List<Document> documents = new ArrayList<>();
        for (final File file : files) {
            final DocumentLoader documentLoader = new DocumentLoader();
            final Map<String, Word> voc = documentLoader.parse(file.getAbsolutePath());

            final Document document = new Document(file.getName(), voc);
            documents.add(document);
        }
        return documents;
    }

}
