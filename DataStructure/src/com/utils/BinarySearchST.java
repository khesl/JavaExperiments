package com.utils;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    
    public BinarySearchST(){
        this(1);
    }
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    
    private void resize(int max){
        Key[] tempKeys = (Key[]) new Comparable[max];
        Value[] tempValues = (Value[]) new Object[max];
        for (int i = 0; i < N; i++){
            tempKeys[i] = keys[i];
            tempValues[i] = vals[i];
        }
        keys = tempKeys;
        vals = tempValues;  
    }
    
    int size(){ return N; }
    public void put(Key key, Value val) {
        if (N == keys.length) resize(2*keys.length); // на изменение длины массива
        // Поиск ключа, если найден, изменяется значение; иначе таблица увеличивается.
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0){
            vals[i] = val; return; 
        }
        for (int j = N; j > i; j--){
            keys[j] = keys[j-1]; vals[j] = vals[j-1];
        }
        keys[i] = key; vals[i] = val;
        N++;
    }
    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else                                      return null;
    }
    int rank(Key key){
        // бинарный поиск в упорядоченном массиве
        int lo = 0, hi = N-1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
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
        int cmp = key.compareTo(keys[mid]);
        
        if (cmp < 0)
            return rank(key, lo, mid-1);
        else if (cmp > 0)
            return rank(key, mid+1, hi);
        else return mid;
    }
    
    void delete(Key key){ put(key, null); }
    boolean contains (Key key){ return get(key) != null; }
    boolean isEmpty(){ return size() == 0; }
    Key min(){ return keys[0]; }
    Key max(){ return keys[N-1]; }
    Key floor(Key key){ 
        int search = rank(key);
        if (search == N) return key;
        return keys[search++];
    }
    Key ceiling(Key key){
        int search = rank(key);
        if (search == 0) return key;
        return keys[search--];
    }
    Key select(int k){ return keys[k]; }
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
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }
}
