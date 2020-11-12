package ex0;

import java.util.HashSet;
import java.util.*;

public class NodeData implements node_data {
    /**
     * All the elements of NodeDate type
     * uniqueKey is static, use it to increase key so every node will have unique key.
     */
    public static int uniqueKey=0;
    private int _key;
    private String _info;
    private Integer _tag;
    private HashMap<Integer,node_data> NeighborsList;

    public NodeData(node_data nodeOriginal) {
        this._key = nodeOriginal.getKey();
        this._info = nodeOriginal.getInfo();
        this._tag = nodeOriginal.getTag();
        NeighborsList = new HashMap<Integer, node_data>();
    }

    public NodeData() {

        this._info = "";
        this._tag = null;
        this._key = uniqueKey++;
        NeighborsList = new HashMap<Integer, node_data>();
    }
    @Override
    public int getKey() {
        return this._key;
    }

    /**
     * get the node all edges (Neighbors)
     * @return collection of Neighbors
     */
    @Override
    public Collection<node_data> getNi() {
        return this.NeighborsList.values();
    }

    /**
     * check if the node have this connection with this key.
     * @param key
     * @return true or false
     */
    @Override
    public boolean hasNi(int key) {
        return NeighborsList.containsKey(key);
    }

    @Override
    public void addNi(node_data t) {
        this.NeighborsList.put(t.getKey(),t);
    }

    /**
     * remove node
     * @param node
     */
    @Override
    public void removeNode(node_data node) {
        NeighborsList.remove(node.getKey());
    }

    @Override
    public String getInfo() {
        return _info;
    }

    @Override
    public void setInfo(String s) {
        this._info = s;
    }

    @Override
    public int getTag() {
        if (_tag != null) {
            return this._tag;
        }
        return -1;

    }

    @Override
    public void setTag(int t) {
        this._tag = t;
    }

    /**
     * this will be use to nullify back to 0
     */
    public static void zeroUniqueKey(){
        NodeData.uniqueKey=0;
    }
}
