package pt.mleiria.mlalgo.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Paths.get;

public class FileLoader {

    private static final Logger LOG = Logger.getLogger(FileLoader.class.getName());

    /**
     * Loads a file into a List of Strings
     *
     * @param path
     * @return a list of strings
     */
    public static List<String> load(String path) {
        final Path file = get(path);
        final List<String> data = new ArrayList<>();
        try (final InputStream in = newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (final IOException x) {
            LOG.severe(x.getMessage());
        } catch (final Exception e) {
            LOG.severe(e.getMessage());
        }
        return data;
    }

    public static List<String> load(final String path, final boolean skipFirstRow) {
        final Path file = get(path);
        final List<String> data = new ArrayList<>();
        try (final InputStream in = newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            if(skipFirstRow){
                reader.readLine();
            }
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (final IOException x) {
            LOG.severe(x.getMessage());
        } catch (final Exception e) {
            LOG.severe(e.getMessage());
        }
        return data;
    }
}
