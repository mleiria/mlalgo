package pt.mleiria.mlalgo.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

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
                if (!line.trim().isEmpty()) {
                    data.add(line);
                }
            }
        } catch (final IOException x) {
            LOG.severe(x.getMessage());
        } catch (final Exception e) {
            LOG.severe(e.getMessage());
        }
        return data;
    }

    /**
     * @param path
     * @param skipFirstRow
     * @return
     */
    public static List<String> load(final String path, final boolean skipFirstRow) {
        final Path file = get(path);
        final List<String> data = new ArrayList<>();
        try (final InputStream in = newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            if (skipFirstRow) {
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

    /**
     * @param dir
     * @return
     */
    public static File[] loadAllFilesInDir(final String dir) {
        final File source = new File(dir);
        return source.listFiles();
    }

    /**
     * @param dir
     * @return a Set with all file names of the dir (no recursion)
     */
    public static Set<String> loadAllFileNamesInDir(final String dir) {
        final File source = new File(dir);
        final Set<String> res = new HashSet<>();
        Arrays.stream(source.listFiles()).sequential().forEach(elem -> res.add(elem.getName()));
        return res;
    }

    /**
     * @param sDir
     * @return a Set of all jpg files in dir and sub dirs
     */
    public static File[] loadJPG(String sDir) {
        final StopWatch sw = new StopWatch();
        final Set<File> fileSet = new HashSet<>();
        try (final Stream<Path> stream = Files.find(Paths.get(sDir), 999,
                (p, bfa) -> bfa.isRegularFile() && p.getFileName().toString().matches(".*\\.(?i)jpg"))) {
            stream.parallel().forEach(elem -> fileSet.add(elem.toFile()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("Loaded " + fileSet.size() + " in " + sw.elapsedTime());
        return fileSet.toArray(new File[0]);
    }

    public static void main(String[] args) {
        final String dirToProcess = "/home/manuel/Pictures";
        File[] f = loadJPG(dirToProcess);
        Arrays.stream(f).sequential().forEach(elem -> System.out.println(elem.getName()));
    }
}
