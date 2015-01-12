package net.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by karrie on 1/11/15.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private class Node{
        Item item;
        Node next;
    }

    public Deque()  {}                         // construct an empty deque

    public boolean isEmpty(){
        return  false;
    }                 // is the deque empty?
    public int size(){
        return -1;
    }                        // return the number of items on the deque
    public void addFirst(Item item) {

    }         // insert the item at the front
    public void addLast(Item item)  {}         // insert the item at the end
    public Item removeFirst()       {

        return first.item;
    }         // delete and return the item at the front
    public Item removeLast()   {
        return  last.item;
    }              // delete and return the item at the end
    public Iterator<Item> iterator(){
        return new ListIterator();
    }         // return an iterator over items in order from front to end

    /* unit testing */
    public static void main(String[] args){}


    private class ListIterator implements Iterator<Item>{

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            if(current.next == null) throw new NoSuchElementException();
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
