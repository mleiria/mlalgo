/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.tasks;

import pt.mleiria.mlalgo.distance.DistanceMetric;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class ExistBasicTask implements Callable<Boolean> {

    private final int startIndex;
    private final int endIndex;
    private final List<String> dictionary;
    private final String word;
    private final DistanceMetric<String, String, Integer> dm;

    /**
     * @param startIndex
     * @param endIndex
     * @param dictionary
     * @param word
     * @param dm
     */
    public ExistBasicTask(final int startIndex, final int endIndex, final List<String> dictionary,
                          final String word, final DistanceMetric<String, String, Integer> dm) {

        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dictionary = dictionary;
        this.word = word;
        this.dm = dm;
    }

    @Override
    public Boolean call() throws Exception {
        for (int i = startIndex; i < endIndex; i++) {
            if (dm.calculate(word, dictionary.get(i)) == 0) {
                return true;
            }
            if (Thread.interrupted()) {
                return false;
            }
        }
        throw new Exception("The word " + word + " doesn't exists.");
    }


}
