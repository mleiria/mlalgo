/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public abstract class ThreadPoolManager {

    private static final Logger LOG = Logger.getLogger(ThreadPoolManager.class.getName());

    public final int numThreads;
    public final ThreadPoolExecutor executor;
    public final int numCores;
    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    /**
     * @param factor
     */
    public ThreadPoolManager(final int factor) {
        numCores = AVAILABLE_PROCESSORS;
        numThreads = factor * numCores;
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        LOG.info("ThreadPool Running with: " + numCores + " cores and " + numThreads + " threads");
    }

    /**
     *
     */
    public ThreadPoolManager() {
        this(1);
    }

    /**
     * @param numCores
     * @param factor
     */
    public ThreadPoolManager(final int numCores, final int factor) {
        this.numCores = Math.max(numCores - 1, 1);
        numThreads = factor * numCores;
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        LOG.info("ThreadPool Running with: " + numCores + " cores and " + numThreads + " threads");
    }

}
