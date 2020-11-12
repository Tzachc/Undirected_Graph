package ex0;

import java.util.*;

public class Graph_DS implements graph {
    private Hashtable<Integer, node_data> nodes;
    private int edgesCounter;
    private int MC;

    /**
     * this method is used to init the static variable back to zero.
     */
    public static void zeroUniqueKey()
    {
        NodeData.uniqueKey=0;
    }
    public Graph_DS() {
        zeroUniqueKey();
        nodes = new Hashtable<>();
        edgesCounter = 0;
        MC=0;
    }

    @Override
    public node_data getNode(int key) {
        return nodes.get(key);
    }

    /**
     * check if there's edge between two nodes
     * @param node1
     * @param node2
     * @return true or false
     */
    @Override
    public boolean hasEdge(int node1, int node2) { // O(1)
        if(nodes.containsKey(node1)){
            return nodes.get(node1).hasNi(node2);
        }
        else{
            return false;
        }
    }

    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(), n);
        ++this.MC;
    }

    /**
     * make a connection between nodes,mean put edge between them.
     * @param node1
     * @param node2
     */
    @Override
    public void connect(int node1, int node2) { // O(1)
        if(node1 != node2 && nodes.containsKey(node1) && nodes.containsKey(node2) && !nodes.get(node1).hasNi(node2)){
            nodes.get(node1).addNi(nodes.get(node2));
            nodes.get(node2).addNi(nodes.get(node1));
            edgesCounter++;
            ++MC;
        }
    }

    /**
     * return collection of all nodes in the graph
     * @return
     */
    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    /**
     * return collection of Neigbors with that node_id
     * @param node_id
     * @return collection
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        node_data tempNode = getNode(node_id);
        return tempNode.getNi();
    }

    /**
     * remove node from the graph,and cancel all his edges.
     * @param key
     * @return
     */
    @Override
    public node_data removeNode(int key) {
        boolean flag = nodes.containsKey(key);
        if(flag) {
            node_data nodeToRemove = nodes.get(key);
            edgesCounter -= nodeToRemove.getNi().size();
            MC += nodeToRemove.getNi().size() + 1;

            for(node_data node : nodeToRemove.getNi()) {
                node.removeNode(nodeToRemove);
            }
            nodeToRemove.getNi().clear();
        }
        return nodes.remove(key);
    }

    /**
     * remove edge with two nodes
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) { // O(1)
        node_data nodeOne = nodes.get(node1);
        node_data nodeTwo = nodes.get(node2);
        if (nodeOne.hasNi(node2)) {
            nodeOne.getNi().remove(nodeTwo);
            nodeTwo.getNi().remove(nodeOne);
            ++MC;
            edgesCounter--;
            // System.out.printf("Successfully remove edge! \n");
        }
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.edgesCounter;
    }

    @Override
    public int getMC() {
        return 0;
    }
}
