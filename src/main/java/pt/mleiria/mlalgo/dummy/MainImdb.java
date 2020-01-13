package pt.mleiria.mlalgo.dummy;

import pt.mleiria.mlalgo.conf.DatasetsLocation;
import pt.mleiria.mlalgo.loader.ImdbLoader;

public class MainImdb {

    public static void main(String[] args) {
        ImdbLoader.load(DatasetsLocation.IMDB_DATA);
    }

}
