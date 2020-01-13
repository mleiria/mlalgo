/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.utils;

import static java.lang.System.currentTimeMillis;

/**
 * @author manuel
 */
public final class StopWatch {

    private final long start;

    /**
     * Create a TimeLog object.
     */
    public StopWatch() {
        start = currentTimeMillis();
    }

    /**
     * Return elapsed time (in seconds) since this object was created.
     *
     * @return
     */
    public double elapsedTime() {
        final long now = currentTimeMillis();
        return (now - start) / 1000.0;
    }

    /**
     * @return
     */
    public String elapsedTimeToString() {
        return "Elapsed time: " + elapsedTime() + " secs.";
    }
}
