package pt.mleiria.graph.undirected;

import pt.mleiria.graph.Bag;

public class GraphProcesor {


    /**
     * @param graph
     * @param v     the vertice number
     * @return the degree of that vertice
     */
    public static int degree(final Graph graph, final int v) {
        int degree = 0;
        for (int w : graph.adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * @param graph
     * @return
     */
    public static int maxDegree(final Graph graph) {
        int max = 0;
        for (int v = 0; v < graph.getVertices(); v++) {
            if (degree(graph, v) > max) {
                max = degree(graph, v);
            }
        }
        return max;
    }

    /**
     * @param graph
     * @return
     */
    public static double averageDegree(final Graph graph) {
        return 2.0 * graph.getEdges() / graph.getVertices();
    }

    /**
     * @param graph
     * @return
     */
    public static int numberOfSelfLoops(final Graph graph) {
        int count = 0;
        for (int v = 0; v < graph.getVertices(); v++) {
            for (int w : graph.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        //Each edge counted twice
        return count / 2;
    }


}
