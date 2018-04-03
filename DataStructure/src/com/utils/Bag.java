package com.utils;

import java.util.Iterator;

/** Реализация класса Bag для хранения элементов, без их удаления
 * */
public class Bag<Item> implements Iterable<Item> {
    private int N;
    private Node first;
    private class Node {
        Item item;
        Node next;
    }
    public boolean isEmpty()    { return first == null; }
    public int size()           { return N;}
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Iterator<Item> iterator() 
    { return new ListIterator();}
    
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext()    { return current != null; }
        public Item next()          { 
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove()        {               }
    }
}
