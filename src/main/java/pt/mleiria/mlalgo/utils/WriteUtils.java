package pt.mleiria.mlalgo.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    /**
     * Append to file
     * @param fileName
     * @param contents
     */
    public static void appendToFile(final String fileName, final String contents){
        try {
            Files.write(Paths.get(fileName), contents.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
