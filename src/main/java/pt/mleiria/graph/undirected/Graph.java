package pt.mleiria.graph.undirected;

import pt.mleiria.graph.Bag;

import java.util.List;

public class Graph {

    /**
     * Number of vertices
     */
    private final int vertices;
    /**
     * Number of edges
     */
    private int edges = 0;
    /**
     * Adjacency lists
     */
    private Bag<Integer>[] adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        //Create array of lists
        adj = (Bag<Integer>[]) new Bag[vertices];
        //Initialize all lists to empty
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag();
        }
    }

    public Graph(List<Integer[]> in) {
        //First line is V. Read it and construct this Graph
        this(Integer.parseInt(String.valueOf(in.get(0)[0]) + String.valueOf(in.get(0)[1])));
        int edges = Integer.parseInt(String.valueOf(in.get(1)[0]) + String.valueOf(in.get(1)[1]));
        for (int i = 2; i < edges + 2; i++) {
            //Read a vertex
            int v = in.get(i)[0];
            //Read another vertex
            int w = in.get(i)[1];
            //Add edge connectiong them
            addEdge(v, w);
        }
    }

    /**
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        //Add w to v's list
        adj[v].add(w);
        //Add v to w's list
        adj[w].add(v);
        edges++;
    }

    /**
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * @return The number of vertices
     */
    public int getVertices() {
        return vertices;
    }

    /**
     * @return the number of edges
     */
    public int getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        String s = vertices + " vertices " + edges + " edges\n";
        for (int v = 0; v < vertices; v++) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    public String toString(List<String> descLst) {
        String s = vertices + " vertices " + edges + " edges\n";
        for (int v = 0; v < vertices; v++) {
            s += v + " " + descLst.get(v) + ": ";
            for (int w : this.adj(v)) {
                s += w +  " " + descLst.get(w) + " ";
            }
            s += "\n";
        }
        return s;
    }


}
