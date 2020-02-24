/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.dummy;

import pt.mleiria.mlalgo.conf.DatasetsLocation;
import pt.mleiria.mlalgo.loader.FileLoader;
import pt.mleiria.mlalgo.stats.words.WordsMatcher;
import pt.mleiria.mlalgo.utils.Tuple2;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class MainConcurrentCalculation {


    private static final Logger LOG = Logger.getLogger(MainConcurrentCalculation.class.getName());

    /**
     * @param args
     */
    public static void main(String[] args) {
        //existsBasic(args);
        bestMatchBasic(args);
    }

    private static void bestMatchBasic(String[] args) {

        args = new String[]{"manuel"};
        try {
            Date startTime;
            Date endTime;
            final List<String> dictionary = FileLoader.load(DatasetsLocation.WORDS_DICT);

            LOG.log(Level.INFO, "Dictionary Size: {0}", dictionary.size());

            startTime = new Date();
            final Tuple2<Integer, List<String>> result = new WordsMatcher().getBestMatchingWords(args[0], dictionary);
            final List<String> results = result.getY();
            endTime = new Date();
            LOG.log(Level.INFO, "Word: {0}", args[0]);
            LOG.log(Level.INFO, "Minimum distance: {0}", result.getX());
            LOG.log(Level.INFO, "List of best matching words: {0}", results.size());
            LOG.log(Level.INFO, "Similar words: {0}", results);
            LOG.log(Level.INFO, "Execution Time: {0}", (endTime.getTime() - startTime.getTime()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void existsBasic(String[] args) {
        args = new String[]{"mad"};
        try {
            Date startTime, endTime;
            final List<String> dictionary = FileLoader.load(DatasetsLocation.WORDS_DICT);

            LOG.log(Level.INFO, "Dictionary Size: {0}", dictionary.size());

            startTime = new Date();
            Boolean result;
            final WordsMatcher ebcc = new WordsMatcher();
            result = ebcc.existWord(args[0], dictionary);
            endTime = new Date();
            LOG.log(Level.INFO, "Word: {0}", args[0]);
            LOG.log(Level.INFO, "Exist: {0}", result);
            LOG.log(Level.INFO, "Execution Time: {0}", (endTime.getTime() - startTime.getTime()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
