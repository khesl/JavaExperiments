package com.sorting;

public class Quick3way {
    public static void sort(Comparable[] a){
        //StdRandom.shuffle(a);
        sort (a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt)
        {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi+1;
        Comparable v = a[lo];
        while (true){
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
        }
        exch(a, lo, j);
        return j;
    }
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
}
