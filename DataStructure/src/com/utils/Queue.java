package com.utils;

import java.util.Iterator;
/** Реализация Очереди как связного списка
 * */
public class Queue<Item> implements Iterable<Item>  {
    private Node first;
    private Node last;
    private int N;
    private class Node {
        Item item;
        Node next;
    }
    
    public Queue(){}
    public Queue(Queue<Item> q){
        Queue<Item> temp = new Queue<Item>();
        for (Item i: q)
            temp.enqueue(i);
        for (Item i : temp){
            enqueue(i);
        }
    }
    
    public boolean isEmpty()    { return first == null; }
    public int size()           { return N;}
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
    
    public String toString(){
        String str = " ";
        if (!isEmpty()) {
            str = "";
            Node node = first;
            while (node != null) {
                str += node.item.toString() + " ";
                node = node.next;
            }
        }
        return str.substring(0, str.length()-1);
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
