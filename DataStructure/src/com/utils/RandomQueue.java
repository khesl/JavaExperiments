package com.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomQueue<Item> implements Iterable<Item>  {
    private Item[] items;
    private int N;
    public RandomQueue(){
        items = (Item[]) new Object[1];
        N = 0;
    }

    public boolean isEmpty()    { return N == 0; }
    public int size()           { return N;}
    private void resize(int N) {
        Item[] temp = (Item[]) new Object[N];
        for (int i = 0; i < N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        items[N++] = item;
        if (N == items.length) {
            resize(2*N);
        }
    }
    public Item dequeue() {
        // remove and return a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = (int)(Math.random() * N);
        Item item = items[i];
        items[i] = items[--N];
        items[N] = null;
        if (N <= items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }
    public Item sample(){
        // return (but do not remove) a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = (int) (Math.random()*N);
        return items[i];       
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
            int k = (int)(Math.random() * (N - i));
            Item item = temp[k];
            temp[k] = temp[N - (++i)];
            temp[N - i] = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
