package pt.mleiria.mlalgo.loader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import pt.mleiria.mlalgo.stats.words.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class DocumentLoader {

    private static final Logger LOG = Logger.getLogger(DocumentLoader.class.getName());

    private static final Pattern PATTERN = Pattern.compile("\\P{IsAlphabetic}+");
    private static final String PDF_EXTENSION = "pdf";
    private static final String TXT_EXTENSION = "txt";


    /**
     * @param route
     * @return a map where the key is the word and the value is a Word object which
     * includes num of times the word appear in the document (TF)
     */
    public Map<String, Word> parse(final String route) {
        if (getFileExtension(route).equals(PDF_EXTENSION)) {
            try {
                final Map<String, Word> ret = new HashMap<>();
                final PDDocument document = PDDocument.load(new File(route));
                final PDFTextStripper tstripper = new PDFTextStripper();
                final String documentText = tstripper.getText(document);
                document.close();
                parseLine(documentText, ret);
                return ret;
            } catch (IOException e) {
                LOG.severe(e.getMessage() + " in file: " + route);
                return new HashMap<>();
            }
        }
        if (getFileExtension(route).equals(TXT_EXTENSION)) {
            return processTextFile(route);
        }
        return new HashMap<>();
    }

    /**
     * @param route
     * @return
     */
    private Map<String, Word> processTextFile(final String route) {
        final Map<String, Word> ret = new HashMap<>();
        final Path file = Paths.get(route);
        try (final BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                parseLine(line, ret);
            }
        } catch (IOException e) {
            LOG.severe(e.getMessage() + " in file: " + route);
        }
        return ret;
    }

    /**
     * @param route a String representing the complete path to a file
     * @return the file extension associated with the route
     */
    private String getFileExtension(final String route) {
        return route.substring(route.lastIndexOf('.') + 1);
    }

    /**
     * @param line
     * @param ret
     */
    private void parseLine(final String line, final Map<String, Word> ret) {
        for (String word : PATTERN.split(line)) {
            if (!word.isEmpty()) {
                Word w = ret.get(word);
                if (w == null) {
                    w = new Word();
                    w.setTf(1);
                    w.setWord(Normalizer.normalize(word, Normalizer.Form.NFKD).toLowerCase());
                } else {
                    w.setTf(w.getTf() + 1);
                }
                ret.put(word, w);
            }
        }
    }
}
