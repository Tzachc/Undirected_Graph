package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    graph graph;
    private int V;
    Hashtable<Integer, Integer> distHash;
    Hashtable<Integer, Integer> colors;
    boolean visited[];
    int[] father;

    public Graph_Algo() {
        this.graph = new Graph_DS();
        V = graph.nodeSize();
        father = new int[V];
        distHash = new Hashtable<>();
        colors = new Hashtable<>();
        visited = new boolean[V];
    }

    public Graph_Algo(graph g0) {
        this.graph = g0;
        V = graph.nodeSize();
        father = new int[V];
        distHash = new Hashtable<>();
        colors = new Hashtable<>();
        visited = new boolean[V];

    }

    @Override
    public void init(graph g) { // O(1)
        this.graph = g;
        V = graph.nodeSize();
        father = new int[V];
        distHash = new Hashtable<>();
        colors = new Hashtable<>();
        visited = new boolean[V];
    }

    /**
     * this method based on bfs algorithm,and used to find
     * the shortest path and distance stored in father array
     *
     * @param s - key of node
     *          **NOTES** : white = 0, grey = 1, black = 2.
     */
    private void Run_bfs(int s) { // O(V+E)
        Queue<Integer> q = new LinkedList<>();
        Initialize();
        q.add(s);
        // 0 = white, 1 = grey, 2 = black
        colors.put(s, 1);
        distHash.put(s, 0);
        father[s] = -1;
        int v;
        while (!q.isEmpty()) {
            v = q.poll();
            for (int u = 0; u < V; u++) {
                if (colors.get(u) == 0 && this.graph.getNode(v).hasNi(u)) {
                    colors.put(u, 1);
                    distHash.put(u, distHash.get(v) + 1);
                    father[u] = v;
                    q.add(u);
                }
            }
            colors.put(v, 2);
        }
    }

    /**
     * this method check if path is exist.
     *
     * @param v node
     * @param u node
     * @return true/false
     */
    public boolean IsTherePathBetween(int v, int u) { // (Run_bfs = O(V+E) )+O(1)
        Run_bfs(v);
        if (colors.get(u) == 2)
            return true;
        return false;
    }

    /**
     * This method used the father array to get the path between v and u.
     * every iteration we add the father, if there is not a father (-1) then there is no path
     * once we reach the destination node we finish and return the list.
     *
     * @param v node
     * @param u node
     * @return List contain the path between v and u.
     * **NOTES** not the shortest path, but only a path.
     */
    public List<node_data> GetPathBetween(int v, int u) { // O(V)
        List<node_data> path = new ArrayList<node_data>();
        int vertex = u;
        while (father[vertex] != -1) {
            path.add(this.graph.getNode(vertex));
            vertex = father[vertex];
        }
        path.add(this.graph.getNode(vertex));

        //reverse the list
        node_data temp;
        for (int i = 0; i < path.size() / 2; i++) {
            temp = path.get(i);
            path.set(i, path.get(path.size() - 1 - i));
            path.set(path.size() - 1 - i, temp);
        }

        return path;
    }

    /**
     * used to initialize the arrays/hashmaps .
     */
    private void Initialize() {
        for (int v = 0; v < V; v++) {
            visited[v] = false;
            colors.put(v, 0);
            if (graph.getNode(v) == null) {
                visited[v] = true;
            }
        }
    }

    /**
     * deep copy algorithm
     *
     * @return new graph with deep copy
     */
    @Override
    public graph copy() {
        graph newGraph = new Graph_DS();
        if (this.graph == null) {
            return newGraph;
        }
        Collection<node_data> OriginalNodes = graph.getV();
        for (node_data vert : OriginalNodes) {
            newGraph.addNode(new NodeData(vert));
            Collection<node_data> Neighbors = graph.getV(vert.getKey());
            if (newGraph.nodeSize() > 1) {
                for (node_data edge : Neighbors) {
                    newGraph.connect(vert.getKey(), edge.getKey());
                }
            }
        }
        return newGraph;
    }

    /**
     * algorithm to check the conctivity of the graph
     *
     * @return true/ false
     */
    @Override
    public boolean isConnected() {
        ArrayList<node_data> list = new ArrayList<node_data>(graph.getV());
        if (graph.nodeSize() == 0)
            return true;
        BFS(graph.getV().iterator().next().getKey());
        for (int i = 0; i < V; i++) {
            if (this.graph.getNode(i) != null) {
                if (this.graph.getNode(i).getTag() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * get the distance of the shortest path
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return size of the shortest path
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        boolean flag = IsTherePathBetween(src, dest);
        if (flag) {
            return distHash.get(dest);
        }
        return -1;
    }

    /**
     * get the path between src and dest
     * if there isnt path, return empty list
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return the shortest path
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (src == dest)
            return new ArrayList<>();
        if (IsTherePathBetween(src, dest)) {
            return GetPathBetween(src, dest);
        }
        return new ArrayList<>();
    }

    /**
     * regular BFS algorithm.
     * used in the isConnected method.
     * based on linkedList.
     *
     * @param s
     */
    void BFS(int s) {
        // init(graph);
        //Initialize();
        InitBFS();
        LinkedList<Integer> queue = new LinkedList<>();
        node_data src = this.graph.getNode(s);
        src.setTag(1);
        // visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            s = queue.poll();
            node_data nodeS = this.graph.getNode(s);
            // System.out.printf(s + " ");
            // Iterator<node_data> it = this.graph.getNode(s).getNi().iterator();
            Iterator<node_data> it = this.graph.getV(s).iterator();
            while (it.hasNext()) {
                node_data n = it.next();
                if (n.getTag() == 0) {
                    //visited[n.getKey()] = true;
                    n.setTag(1);
                    queue.add(n.getKey());
                }
            }
            nodeS.setTag(3);
        }
    }

    private void InitBFS() {
        for (int v = 0; v < V; v++) {
            if (this.graph.getNode(v) != null) {
                this.graph.getNode(v).setTag(0);
            }
        }
    }
}
