package com.utils;

/** Базовая ST реализация */
public class ST<Key extends Comparable<Key>, Value> {
    
    /*
    public ST() {}
    
    void put(Key key, Value val) {}
    
    Value get(Key key){}
    void delete(Key key){ put(key, null); }
    boolean contains (Key key){ return get(key) != null; }
    boolean isEmpty(){ return size() == 0; }
    int size(){}
    Key min(){}
    Key max(){}
    Key floor(Key key){}
    Key ceiling(Key key){}
    int rank(Key key){}
    Key select(int k){}
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
    Iterable<Key> keys(Key lo, Key hi){}
    Iterable<Key> keys(){ return keys(min(), max()); }
    */
}
