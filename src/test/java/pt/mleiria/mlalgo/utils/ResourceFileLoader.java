package pt.mleiria.mlalgo.utils;

import java.net.URL;

public class ResourceFileLoader {

    public static String getFilePath(final String fileName) {

        // Load the file from the resources folder
        URL resource = ResourceFileLoader.class.getClassLoader().getResource(fileName);
        if (resource != null) {
            return resource.getFile();
        } else {
            return null;
        }
    }


}
