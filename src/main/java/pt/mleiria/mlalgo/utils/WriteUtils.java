package pt.mleiria.mlalgo.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteUtils {


    public static void writeToFile(final String fileName, final List<String> contents) {
        try (final FileWriter myWriter = new FileWriter(fileName)) {
            contents.stream().forEach(elem -> {
                try {
                    myWriter.write(elem + "\n");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            });
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(final String fileName, final String contents) {
        try (final FileWriter myWriter = new FileWriter(fileName)) {
            myWriter.write(contents);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
