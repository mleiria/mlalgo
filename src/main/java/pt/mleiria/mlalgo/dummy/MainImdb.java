package pt.mleiria.mlalgo.dummy;

import pt.mleiria.mlalgo.conf.DatasetsLocation;
import pt.mleiria.mlalgo.dataset.vo.imdb.Imdb;
import pt.mleiria.mlalgo.dataset.vo.imdb.ImdbVO;
import pt.mleiria.mlalgo.loader.imdb.ImdbLoader;

import java.util.List;

public class MainImdb {

    public static void main(String[] args) {
        ImdbLoader imdb = new ImdbLoader(DatasetsLocation.IMDB_DATA, ImdbVO.class);
        List<Imdb> res = imdb.filterByActor("Christian");
        System.out.println(res);
        System.out.println(res.size());

        //ImdbLoader.load(DatasetsLocation.IMDB_DATA, ImdbVO.class);
    }

}
