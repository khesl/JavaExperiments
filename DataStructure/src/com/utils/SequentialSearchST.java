package com.utils;

/** последовательный поиск (в неупорядоченном связном списке) */
public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first;
    private Node last;
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
    public SequentialSearchST() {}
    
    void put(Key key, Value val) {
        for (Node x = first; x!= null; x = x.next)
            if (key.equals(x.key)){
                x.val = val; 
                last = x;
                count++;
                return; 
            }
        first = new Node(key, val, first);
        last = first;
        count ++;
    }
    
    Value get(Key key){
        for (Node x = first; x!= null; x = x.next)
            if (key.equals(x.key))
                return x.val;
        return null;
    }
    void delete(Key key){ put(key, null); }
    boolean contains (Key key){ return get(key) != null; }
    boolean isEmpty(){ return size() == 0; }
    int size(){ return count; }
    Key min(){ return first.key; }
    Key max(){ return last.key; }
    Key floor(Key key){ 
        for (Node x = first; x!= null; x = x.next){
            if (x.key.equals(max())) return (Key)max();
            if (x.key.equals(key)) return x.next.key; 
        }
        return first.key;
    }
    Key ceiling(Key key){ 
        Node prev = first;
        for (Node x = first; x!= null; x = x.next){
            if (x.key.equals(min())) return (Key)min();
            if (x.key.equals(key)) return prev.key; 
            prev = x;
        }
        return last.key;
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
    void deleteMax(){ delete(max()); }
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
    Iterable<Key> keys(){ return keys(min(), max()); }
    
}
