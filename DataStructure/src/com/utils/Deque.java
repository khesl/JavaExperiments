package com.utils;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty()    { return N == 0; }
    public int size()           { return N;}
    public void pushLeft(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) {
            last = first;
        } else oldfirst.prev = first;
        N++;
    }
    public void pushRight(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        if (isEmpty()) {
            first = last;
        } else oldlast.next = last;
        N++;
    }
    public Item popLeft(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public Item popRight(){
        Item item = last.item;
        last = last.prev;
        N--;
        return item;
    }

    public Iterator<Item> iterator() 
    { return new ReverseArrayIterator();}
    
    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;
        private Node next = first;
        public boolean hasNext()    { return i > 0; }
        public Item next()          {
            Item item = next.item;
            next = next.next;
            i--;
            return item;
        }
        public void remove()        {               }
    }
}