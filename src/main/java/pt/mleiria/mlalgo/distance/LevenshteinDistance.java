/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.mlalgo.distance;

/**
 *
 * @author Manuel Leiria <manuel.leiria at gmail.com>
 */
public class LevenshteinDistance implements DistanceMetric<String, String, Integer> {

    @Override
    public Integer calculate(String string1, String string2) {

        final int[][] distances = new int[string1.length() + 1][string2.length() + 1];

        for (int i = 1; i <= string1.length(); i++) {
            distances[i][0] = i;
        }
        for (int j = 1; j <= string2.length(); j++) {
            distances[0][j] = j;
        }
        for (int i = 1; i <= string1.length(); i++) {
            for (int j = 1; j <= string2.length(); j++) {
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    distances[i][j] = distances[i - 1][j - 1];
                } else {
                    distances[i][j] = minimum(distances[i - 1][j],
                            distances[i][j - 1], distances[i - 1][j - 1]) + 1;
                }
            }
        }
        return distances[string1.length()][string2.length()];
    }

    private int minimum(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }

}
