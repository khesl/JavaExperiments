package com;

public class Example {
    
    public static void sort(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
            for (int j = i; j < a.length; j++)
                if (less(a[j], a[i])) exch(a, j, i);
    }
    
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
    
    private static void show(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i] + " ");
        System.out.println();
    }
    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    public static void main(String[] args){
        //String[] a = {"wqe", "qqqw", "arsd", "abnc", "oiu"};
        String[] a = {"3", "5", "1", "4", "2"};
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
