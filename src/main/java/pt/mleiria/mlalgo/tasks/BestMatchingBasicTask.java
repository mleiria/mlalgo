/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import pt.mleiria.mlalgo.distance.DistanceMetric;
import pt.mleiria.mlalgo.utils.Pair;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class BestMatchingBasicTask implements Callable<Pair<Integer, List<String>>> {

    private final int startIndex;
    private final int endIndex;
    private final List<String> dictionary;
    private final String word;
    private final DistanceMetric<String, String, Integer> dm;

    public BestMatchingBasicTask(final int startIndex, final int endIndex, final List<String> dictionary,
            final String word, final DistanceMetric<String, String, Integer> dm) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dictionary = dictionary;
        this.word = word;
        this.dm = dm;
    }

    @Override
    public Pair<Integer, List<String>> call() throws Exception {
        final List<String> results = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        int distance;
        for (int i = startIndex; i < endIndex; i++) {
            distance = dm.calculate(word, dictionary.get(i));
            if (distance < minDistance) {
                results.clear();
                minDistance = distance;
                results.add(dictionary.get(i));
            } else if (distance == minDistance) {
                results.add(dictionary.get(i));
            }
        }
        return new Pair<>(minDistance, results);
    }

}
