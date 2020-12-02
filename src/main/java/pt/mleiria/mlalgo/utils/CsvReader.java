package pt.mleiria.mlalgo.utils;

import pt.mleiria.graph.undirected.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Paths.get;

public class CsvReader<Number> {

    private static final Logger LOG = Logger.getLogger(CsvReader.class.getName());

    /**
     * Loads a file into a List of Strings
     *
     * @param path
     * @return a list of strings
     */
    public List<Number[]> load(String path) {
        final Path file = get(path);
        final List<Number[]> data = new ArrayList<>();
        try (final InputStream in = newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] arr = line.split(";");
                final Integer[] lineArr = new Integer[arr.length];
                for(int i = 0; i < arr.length; i++){
                    lineArr[i] = Integer.valueOf(arr[i]);
                }
                data.add((Number[]) lineArr);
            }
        } catch (final IOException x) {
            LOG.severe(x.getMessage());
        } catch (final Exception e) {
            LOG.severe(e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {
        //String file = "/home/manuel/Elements/Datasets/graph/graph.txt";
        String file = "/home/manuel/Elements/Datasets/graph/cluedo_graph.txt";

        CsvReader<Integer> csvReader = new CsvReader<>();
        List<Integer[]> res = csvReader.load(file);
        Graph graph = new Graph(res);
        System.out.println(graph.toString());

    }
}
