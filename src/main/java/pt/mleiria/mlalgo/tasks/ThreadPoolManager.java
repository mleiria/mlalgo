/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public abstract class ThreadPoolManager {

    public final int numThreads;
    public final ThreadPoolExecutor executor;
    public final int numCores;

    /**
     * @param factor
     */
    public ThreadPoolManager(final int factor) {
        numCores = Runtime.getRuntime().availableProcessors();
        numThreads = factor * numCores;
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
    }

    /**
     *
     */
    public ThreadPoolManager() {
        this(1);
    }


}
