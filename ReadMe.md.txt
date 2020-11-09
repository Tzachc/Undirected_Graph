# Undirected unwighet graphs

This Object oriented programming task written by:
* Tzach Cohen - 205650310

The main topic of this task is undirected graphs and algorithems.

I've got 3 classes in my project:
-Graph_DS: implements the interface "graph" and represents an undirected unweighted graph.

-NodeData: implements the interface "node_data" that represents a node in the graph.

-Graph_Algo: implements the interface "graph_algorithms", represents sevral of algorithms based on the graph we created in out Graph_DS class.


My graph have sevral importent methods:
Graph_DS:

1) **getV** - return a collection that contain all the vertecis in the graph.
2) **getV(int node_id)** - return collection of Neigbors with that node_id.
3) **removeNode** - remove node from the graph,and cancel all his edges.
4) **removeEdge** - remove edge with two nodes.
5) **addNode** - add node to the graph.
6) **getNode** - return the node based on given key.
7) **connect** - initialize an edge with 2 given nodes.


Graph_Algo:
1) **BFS/run_BFS** - method to find if we can reach all the nodes from a source node.
2) **shortestPathDist** - returns the shortest distance between source to destination node, using the BFS algorithem.
3) **shortestPath** - returns the shortest path itself (an actuall List).
4) **Initialize** -  init the array/hash before entring the BFS algorithm.
5) **isConnected** - check the connectivity of the graph, return true/false.
6) **copy** - Compute a deep copy of this graph.