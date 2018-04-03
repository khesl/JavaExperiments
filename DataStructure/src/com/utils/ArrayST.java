package com.utils;

import java.util.ArrayList;

/** Реализация ArrayST на основе BinarySearchST.
 * */
public class ArrayST<Key extends Comparable<Key>, Value> {
    private ArrayList<Key> keys;
    private ArrayList<Value> vals;
    private int N;
    
    public ArrayST() {
        keys = new ArrayList<Key>();
        vals = new ArrayList<Value>();
    }
    
    int size(){ return N; }
    public void put(Key key, Value val) {
        // Поиск ключа, если найден, изменяется значение; иначе таблица увеличивается.
        int i = rank(key);
        if (i < N && keys.get(i).compareTo(key) == 0){
            vals.set(i, val); return; 
        }
        keys.add(key);
        vals.add(val);
        N++;
    }
    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        System.out.println("i " + i + " " + vals.get(i));
        if (i < N && keys.get(i).compareTo(key) == 0) return vals.get(i);
        else                                          return null;
    }
    int rank(Key key){
        // бинарный поиск в упорядоченном массиве
        int lo = 0, hi = N-1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys.get(mid));
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid +1;
            else return mid;
        }    
        return lo;
    }
    
    int rank_2(Key key){ return rank(key, 0, N-1); }
    int rank(Key key, int lo, int hi){
        // рекурсивный бинарный поиск
        if (hi < lo) return lo;
        
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys.get(mid));
        
        if (cmp < 0)
            return rank(key, lo, mid-1);
        else if (cmp > 0)
            return rank(key, mid+1, hi);
        else return mid;
    }
    
    void delete(Key key){ put(key, null); }
    boolean contains (Key key){ return get(key) != null; }
    boolean isEmpty(){ return size() == 0; }
    Key min(){ return keys.get(0); }
    Key max(){ return keys.get(N-1); }
    Key floor(Key key){ 
        int search = rank(key);
        if (search == N) return key;
        return keys.get(search++);
    }
    Key ceiling(Key key){
        int search = rank(key);
        if (search == 0) return key;
        return keys.get(search--);
    }
    Key select(int k){ return keys.get(k); }
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
            q.enqueue(keys.get(i));
        if (contains(hi))
            q.enqueue(keys.get(rank(hi)));
        return q;
    }
}
