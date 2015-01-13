package net.week2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdRandom;

/**
 * Created by karrie on 1/11/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first, last;
    private class Node{
        Item item;
        Node next;
    }
    public RandomizedQueue()    {}            // construct an empty randomized queue
    public boolean isEmpty()     { return  first == null;}            // is the queue empty?
    public int size() { return -1;}                        // return the number of items on the queue
    public void enqueue(Item item){
        Node oldLast = last;
        Node last = new Node();
        last.item = item; //some random item?
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
    }           // add the item (to the beginning?)

//TODO:    set removed item to null to avoid loitering
    public Item dequeue()
    {
        Item item = first.item;
        first = first.next;

        if(isEmpty()) last = null;
        return item;
    }          // delete and return a random item

    public Item sample()            {
        return first.item;
    }         // return (but do not delete) a random item

    public Iterator<Item> iterator() { return new ListIterator(); } // return an independent iterator over items in random order
    public static void main(String[] args)  {} // unit testing

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

