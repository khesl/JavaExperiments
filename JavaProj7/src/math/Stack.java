package src.math;

import java.security.Key;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/** Реализация связного списка на основе стэка
 * */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    private int pushCount;
    private int popCount;
    private class Node {
        Item item;
        Node next;
    }

    public Stack(){}
    public Stack(Stack<Item> s){
        Stack<Item> temp = new Stack<Item>();
        for (Item i: s)
            temp.push(i);
        for (Item i : temp){
            push(i);
        }
    }

    public boolean isEmpty()    { return first == null; }
    public int size()           { return N;}
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
        pushCount++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        popCount++;
        return item;
    }
    public Item peek(){
        return first.item;
    }
    public Item delete(int k){
        int count = 0;
        Node needNode = first;
        Node oldNode = new Node();
        if (k == 0) return pop();
        else {
            do {
                oldNode = needNode;
                needNode = needNode.next;
                count++;}
            while (count < k);
            oldNode.next = needNode.next;
        }
        N--;
        return needNode.item;
    }
    public boolean find(int k){
        if (k > N) return false;
        int count = 0;
        Node needNode = first;
        while (count != k){
            needNode = needNode.next;
            count++;
        }
        if (needNode != null) return true;
        else return false;
    }
    private boolean find(Node a){
        Node needNode = first;
        while (needNode.next != null){
            if (needNode.item == a.item) return true;
            needNode = needNode.next;
        }
        return false;
    }
    public boolean findAndDelete(Item k){
        if (!isEmpty()) {
            Node needNode = first;
            int count = 0;
            while (needNode != null) {
                if (needNode.item.equals(k)) {
                    delete(count);
                    return true;
                }
                needNode = needNode.next;
                count++;
            }
        }
        return false;
    }
    public Node findAndGet(int key){
        if (!isEmpty()) {
            Node needNode = first;
            int count = 0;
            while (count != (N - key)) {
                needNode = needNode.next;
                count++;
            }
            if (needNode != null) return needNode;
        }
        return null;
    }
    public Item getItem(int key){
        if (!isEmpty()) {
            Node needNode = first;
            int count = 0;
            while (count != (N - key)) {
                needNode = needNode.next;
                count++;
            }
            if (needNode != null) return needNode.item;
        }
        return null;
    }
    public Node findNode(Item k){
        Node needNode = first;
        while (needNode.next != null){
            if (needNode.item == k) return needNode;
            needNode = needNode.next;
        }
        return null;
    }
    public void removeAfter(int k){
        int count = 0;
        Node needNode = first;
        Node oldNode = null;
        while (count != k){
            oldNode = needNode;
            needNode = needNode.next;
            count++;
        }
        if (k > 0) if (needNode.next != null) needNode.next = needNode.next.next;
        N--;
    }
    /** Реализация вставки связного списка в список после заданного узла, ищется по key*/
    public void insertAfter(Node a, Node b){
        if (a != null && b != null && find(a)){
            a = findNode(a.item);
            Node lastNode = b;
            while (lastNode.next != null){
                lastNode = lastNode.next;
                N++;
            }
            lastNode.next = a.next;
            a.next = b;
            N++;
        }
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
        private int pushCount_ = pushCount;
        private int popCount_ = popCount;
        public boolean hasNext()    {
            if (pushCount_ != pushCount || popCount_ != popCount)
                throw new ConcurrentModificationException();
            return i > 0;

        }
        public Item next()          {
            if (pushCount_ != pushCount || popCount_ != popCount)
                throw new ConcurrentModificationException();
            Item item = next.item;
            next = next.next;
            i--;
            return item;
        }
        public void remove()        {               }
    }
}
