package com.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBuffer<Item> implements Iterable<Item>  {
    private Item[] items;
    private int N;
    private int capacity;
    
    public RingBuffer(int capacity) {
        items = (Item[]) new Object[capacity];
        this.capacity = capacity;
        N = 0;
    }
    public boolean isEmpty(){ return N == 0; }
    private void resize(int N) {
        Item[] temp = (Item[]) new Object[N];
        for (int i = 0; i < N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }
    public void insert(Item item){
        if (N < capacity) items[N++] = item;
        else {
            delete();
            insert(item);
        }
        /*if (N == items.length) {
            resize(2*N);
        }*/
    }
    public Item delete(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = items[0];        
        Item[] temp = (Item[])new Object[N];

        for (int j = 1; j < N; j++) {
            temp[j-1] = items[j];
        }
        items = temp;
        N--;
        return item;
    }
    
    
    
    public String toString(){
        String str = "";
        for (int i = 0; i<N;i++)
            str += items[i] + ", ";
        return str.substring(0, str.length()-2);
    }
    
    public Iterator<Item> iterator() 
    { return new RandomizedQueueIterator();}

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] temp;

        public RandomizedQueueIterator() {
            temp = (Item[])new Object[N];

            for (int j = 0; j < N; j++) {
                temp[j] = items[j];
            }
        }

        public boolean hasNext() {
            return i < N;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            //int k = (int)(Math.random() * (N - i));
            Item item = temp[i++];
            //temp[0] = temp[N - (++i)];
            //temp[N - i] = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
