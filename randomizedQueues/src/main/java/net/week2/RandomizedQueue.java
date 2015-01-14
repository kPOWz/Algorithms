package net.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdRandom;

/**
 * Created by karrie on 1/11/15.
 * Must support in constant amortized time (besides creating the iterator)
 * Must use space proportional to the number of items currently in the queue
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;         // number of elements on queue
    private Node first, last; //TODO: needed?
    private class Node{
        Item item;
        Node next;
    }

    /*
    Construct an empty randomized queue
     */
    public RandomizedQueue()    {
        first = null;
        last = null;
        N = 0;

    }

    /*
    Is the queue empty?
     */
    public boolean isEmpty() {
            return  first == null;
    }

    public int size() { return N;}                        // return the number of items on the queue

    /*
    Add item to the Queue
     */
    public void enqueue(Item item){
        if(item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldLast.next = last;
        N++;
    }

    //TODO:    set removed item to null to avoid loitering
    //TODO: doesn't a changing N ruin uniform random b/c probability of being picked goes up after each dequeue op? Should all items stay and just be set to null?
    /*
    Delete and return a random item
    Must chose item for removal uniformly at random
     */
    public Item dequeue()
    {
        if(isEmpty()) throw new NoSuchElementException();
        int rnd = StdRandom.uniform(1,N +1); //TODO: N+1?
        ListIterator itr = iterator();
        Item selectedItem = null;
        Node oldCurrent = null;
        int iterations =1;
        while(iterations < rnd){
            oldCurrent = itr.current;
            selectedItem = itr.next();
            iterations++;
        }
        //Dequeue by connecting prior node to next node
        Node nextNode = itr.current == null ? null : itr.current.next;

        //if first item for removal ...
        if(rnd  == 1){
            first = nextNode;
            selectedItem = itr.current.item;
        }
        else oldCurrent.next = nextNode;

        N--;
        itr.current = null; // to avoid loitering
        if (isEmpty()) last = null;   // to avoid loitering
        return selectedItem;
    }

    /*
    return (but do not delete) a random item
     */
    public Item sample()            {
        if(isEmpty()) throw new NoSuchElementException();
        int rnd = StdRandom.uniform(1,N +1); //TODO: N+1?
        Iterator<Item> itr = iterator();
        Item selectedItem;
        int iterations =0;
        do{
            selectedItem = itr.next();
            iterations++;

        }while (iterations < rnd);
        return selectedItem;
    }

    /*
    Return an independent iterator over items in random order
     */
    public ListIterator iterator() { return new ListIterator(); }

    public static void main(String[] args)  {} // unit testing

    /*
    Must be mutually independent; each iterator must maintain its own random order.
    Must support operations next() and hasNext() in constant worst-case time
    Must support construction in linear time
    May use a linear amount of extra memory per iterator
     */
    private class ListIterator implements Iterator<Item>{

        public Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

