package com.utils;

import java.util.Iterator;

public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first;
    private int count = 0;
    private class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    public OrderedSequentialSearchST() { }
        
    public void put(Key key, Value val) {
        for (Node x = first; x!= null; x = x.next)
            if (key.equals(x.key)){
                x.val = val; 
                return; 
            }
        if (isEmpty()) {
            first = new Node(key, val, first);               
            count ++;
            return;   
        }
        if (less(key, first.key)) {
            first = new Node(key, val, first);               
            count ++;
            return;   
        } else {
            first = new Node(key, val, first);               
            count ++;
            first = sort(first);
            return;
        }
    }
    private Node sort(Node nodeSort) {
        for (int i = count - 1; i >= 1; i--) {
            Node finalval = nodeSort;
            Node tempNode = nodeSort;
            for (int j = 0; j < i; j++) {
                Key val1 = nodeSort.key;
                Node nextnode = nodeSort.next;
                Key val2 = nextnode.key;
                if (!less(val1, val2)) {
                    if (nodeSort.next.next != null) {
                        Node CurrentNext = nodeSort.next.next;
                        nextnode.next = nodeSort;
                        nextnode.next.next = CurrentNext;
                        if (j == 0) {
                            finalval = nextnode;
                        } else
                            nodeSort = nextnode;

                        for (int l = 1; l < j; l++) {
                            tempNode = tempNode.next;
                        }

                        if (j != 0) {
                            tempNode.next = nextnode;
                            nodeSort = tempNode;
                        }
                    } else if (nodeSort.next.next == null) {
                        nextnode.next = nodeSort;
                        nextnode.next.next = null;
                        for (int l = 1; l < j; l++) {
                            tempNode = tempNode.next;
                        }
                        tempNode.next = nextnode;
                        nextnode = tempNode;
                        nodeSort = tempNode;
                    }
                } else
                    nodeSort = tempNode;
                nodeSort = finalval;
                tempNode = nodeSort;
                for (int k = 0; k <= j && j < i - 1; k++) {
                    nodeSort = nodeSort.next;
                }
            }
        }
        return nodeSort;
    }
    
    public Value get(Key key){
        for (Node x = first; x!= null; x = x.next)
            if (key.equals(x.key))
                return x.val;
        return null;
    }
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    void delete(Key key){ put(key, null); }
    boolean contains (Key key){ return get(key) != null; }
    boolean isEmpty(){ return size() == 0; }
    int size(){ return count; }
    Key min(){ return first.key; }
    //Key max(){ return last.key; }
    /*Key floor(Key key){ 
        for (Node x = first; x!= null; x = x.next){
            if (x.key.equals(max())) return (Key)max();
            if (x.key.equals(key)) return x.next.key; 
        }
        return first.key;
    }*/
    Key ceiling(Key key){ 
        Node prev = first;
        for (Node x = first; x!= null; x = x.next){
            if (x.key.equals(min())) return (Key)min();
            if (x.key.equals(key)) return prev.key; 
            prev = x;
        }
        return prev.key;
    }
    
    int rank(Key key){
        int rank = 0;
        for (Node x = first; x!= null; x = x.next){
            if (x.key.equals(key)) return rank;
            rank ++;
        }
        return 0;    
    }
    Key select(int k){
        int rank = 0;
        for (Node x = first; x!= null; x = x.next){
            if (rank == k) return x.key;
            rank ++;
        }
        return null;
    }
    void deleteMin(){ delete(min()); }
    //void deleteMax(){ delete(max()); }
    int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) 
            return 0;
        else if (contains(hi)) 
            return rank(hi) - rank(lo) + 1;
        else 
            return rank(hi) - rank(lo);
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(select(i));
        if (contains(hi))
            q.enqueue(select(rank(hi)));
        return q;
    }
    //public Iterable<Key> keys(){ return keys(min(), max()); }
    
    public Iterator<Key> iterator() 
    { return new OrderedSequentialSearchSTIterator();}
    
    private class OrderedSequentialSearchSTIterator implements Iterator<Key>{
        private int i = 0;
        private Node next = first;
        public boolean hasNext()    { return i < count; }
        public Key next()          { 
            Key item = next.key;
            next = next.next;
            i++;
            return item;
        }
        public void remove()        {               }
    }
}
