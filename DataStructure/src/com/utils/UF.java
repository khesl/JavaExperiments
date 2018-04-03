package com.utils;

/**
 *  Связный список-дерево
 * */
public class UF {
    private int[] id;
    private int count;
    private int callArray;

    public UF(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    
    /** реализация на быстрый поиск */
    /*public void union(int p, int q){
        callArray = 0;
        int pId = find(p);
        int qId = find(q);
        
        if (pId == qId) return;
        
        for (int i = 0; i < id.length; i++){
            callArray++;
            if (id[i] == pId){
                id[i] = qId;
                callArray++;
            }
        }
        count--;
    }
    public int find(int p){
        callArray++;
        return id[p]; 
    }*/
    
    /** реализация быстрого объединения */
    public void union(int p, int q){
        callArray = 0;
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        callArray ++;
        count--;
    }    
    public int find(int p){
        while (p != id[p]){ p = id[p]; callArray += 2;}
        return p;
    }
    
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    
    public int count(){
        return count;
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
        str += "\n" + callArray;       
        
        return str;
    }

}
