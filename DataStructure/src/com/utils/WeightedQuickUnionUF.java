package com.utils;

/**
 * Реализация вззвешенного связного списка-дерева
 */
public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;
    
    public WeightedQuickUnionUF(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    public int count(){ return count; }
    
    public boolean connected(int p, int q){
        return (find(p) == find(q));
    }
    
    public int find(int p){
        while (p != id[p]) p = id[p];
        return p;
    }
    
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (sz[i] < sz[j])  { id[i] = j; sz[j] += sz[i]; }
        else                { id[j] = i; sz[i] += sz[j]; }
        count--;
    }
    
    public void buildTree(){
        int[] val = new int[id.length];
        for (int i = 0; i < val.length; i++) val[i] = i;
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) nodes[i] = new Node(i);
        for (int i = 0; i < val.length; i++){
            int j = i;
            int oldJ = j;
            while (j != id[j]){                
                oldJ = j;
                j = id[j];
                if (!nodes[oldJ].isParent()){
                    nodes[oldJ].setParent(nodes[j]);
                    nodes[j].insertNode(nodes[oldJ]);
                }
            }
        }
        for (int i = 0; i < nodes.length; i++)
            if (!nodes[i].isParent()) System.out.println(nodes[i]);
    }
    
    private class Node{
        private Node[] nodes = new Node[20];
        private int val;
        private int count;
        private Node parentnode;
        
        public Node(){};
        public Node(int val) { this.val = val; count = 0;}
        public boolean isEmpty(){ return count == 0;}
        public int count(){ return count; }
        public void setValue(int val) { this.val = val; }
        public int getValue(){ return val; }
        public void setParent(Node parent) { this.parentnode = parent; }
        public boolean isParent(){ return parentnode != null; }
        public void insertNode(Node node){
            nodes[count] = node;   
            count++;
        }
        public Node[] getNodes(){ return nodes; }
        
        public String toString(){
            if (isEmpty()) return "(val - " + val + ") ";
            String str = "";
            str += "(val - " + val + " ";
            for (int i = 0; i < nodes.length; i++) if (nodes[i] != null) str += nodes[i].toString();
            str += ") ";            
            return str;
        }
    }
    
    public String toString(){
        String str = "";
        for (int i = 0; i < id.length; i++)
            str += id[i] + " ";
        return str;
    }
    
}
