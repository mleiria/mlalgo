/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.conf;

/**
 *
 * @author manuel
 */
public final class DatasetsLocation {
    
    private DatasetsLocation() {
	
    }
    
    public static final String DATA_SETS_DIR = "/media/manuel/Elements/Datasets/";
    public static final String DATA_SETS_TMP_DIR = DATA_SETS_DIR + "tmp/";
    
    public static final String IMDB_DATA = DATA_SETS_DIR + "IMDB-Movie-Data.csv";
    public static final String WIKI_MOVIE_DATA = DATA_SETS_DIR + "wikimovies";
    
    public static final String WORDS_DICT = DATA_SETS_DIR + "UK_Advanced_Cryptics_Dictionary.txt";
}
