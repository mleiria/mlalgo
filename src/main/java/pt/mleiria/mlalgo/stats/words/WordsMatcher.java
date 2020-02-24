/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.stats.words;

import pt.mleiria.mlalgo.distance.LevenshteinDistance;
import pt.mleiria.mlalgo.tasks.BestMatchingBasicTask;
import pt.mleiria.mlalgo.tasks.ExistBasicTask;
import pt.mleiria.mlalgo.tasks.ThreadPoolManager;
import pt.mleiria.mlalgo.utils.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class WordsMatcher extends ThreadPoolManager {

    private static final Logger LOG = Logger.getLogger(WordsMatcher.class.getName());

    /**
     * @param word
     * @param dictionary
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public Tuple2<Integer, List<String>> getBestMatchingWords(final String word, final List<String> dictionary)
            throws InterruptedException, ExecutionException {

        if (dictionary.isEmpty()) {
            throw new IllegalArgumentException("Dictionary is empty!");
        }

        final int size = dictionary.size();
        final int step = size / numCores;
        int startIndex;
        int endIndex;
        final List<Future<Tuple2<Integer, List<String>>>> results = new ArrayList<>();

        for (int i = 0; i < numCores; i++) {
            startIndex = i * step;
            if (i == numCores - 1) {
                endIndex = dictionary.size();
            } else {
                endIndex = (i + 1) * step;
            }
            final BestMatchingBasicTask task = new BestMatchingBasicTask(startIndex, endIndex, dictionary, word, new LevenshteinDistance());
            final Future<Tuple2<Integer, List<String>>> future = executor.submit(task);
            results.add(future);
        }

        executor.shutdown();

        final List<String> words = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (final Future<Tuple2<Integer, List<String>>> future : results) {
            final Tuple2<Integer, List<String>> data = future.get();
            if (data.getX() < minDistance) {
                words.clear();
                minDistance = data.getX();
                words.addAll(data.getY());
            } else if (data.getX() == minDistance) {
                words.addAll(data.getY());
            }

        }
        return new Tuple2<>(minDistance, words);

    }

    /**
     * @param word
     * @param dictionary
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public boolean existWord(final String word, final List<String> dictionary)
            throws InterruptedException, ExecutionException {

        final int numCores = Runtime.getRuntime().availableProcessors();
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numCores);

        final int size = dictionary.size();
        final int step = size / numCores;
        int startIndex, endIndex;
        final List<ExistBasicTask> tasks = new ArrayList<>();
        for (int i = 0; i < numCores; i++) {
            startIndex = i * step;
            if (i == numCores - 1) {
                endIndex = dictionary.size();
            } else {
                endIndex = (i + 1) * step;
            }
            final ExistBasicTask task = new ExistBasicTask(startIndex, endIndex, dictionary, word,
                    new LevenshteinDistance());
            tasks.add(task);
            LOG.log(Level.INFO, "Number of tasks:{0}", tasks.size());
        }
        try {
            return executor.invokeAny(tasks);
        } catch (final ExecutionException e) {
            if (e.getCause() instanceof NoSuchElementException) {
                return false;
            }
            throw e;
        } finally {
            executor.shutdown();
        }

    }
}
