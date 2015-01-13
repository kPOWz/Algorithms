package net.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by karrie on 1/11/15.
 * Must support constant worst-case time.
 * Must use space proportional to the number of items currently in the deque.
 */
public class Deque<Item> implements Iterable<Item> {
    private int N;         // number of elements on queue
    private Node<Item> first, last;
    private class Node<Item>{
        Item item;
        Node<Item> next;
    }

    public Deque()  {
        first = null;
        last  = null;
        N = 0;
    }                         // construct an empty deque

    public boolean isEmpty(){
        return  N < 1;
    }                 // is the deque empty?
    public int size(){
        return N;
    }                        // return the number of items on the deque

    /*
     *  insert the item at the front
     *  TODO: may need to fill in oldLast here as well...otherwise addfirst & removelast will leave remove last w/out enough info
     */
    public void addFirst(Item item) {
        if(item == null) throw new NullPointerException();
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    /*
    *   insert the item at the end
    */
    public void addLast(Item item)  {
        if(item == null) throw new NullPointerException();
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = oldLast; //this is the trick
        N++;
    }

    /*
    *  delete and return the item at the front
    */
    public Item removeFirst()       {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty()) last = null;
        return item;
    }

    /*
    *  delete and return the item at the end
    */
    public Item removeLast()   {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.next;
        N--;
        if(isEmpty()) first = null;
        return item;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }         // return an iterator over items in order from front to end

    /* unit testing */
    public static void main(String[] args){}


    private class ListIterator implements Iterator<Item>{

        private Node<Item> current = first;
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
