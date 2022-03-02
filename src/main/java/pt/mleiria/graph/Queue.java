package pt.mleiria.graph;

import java.util.Iterator;

/**
 * FIFO
 *
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item> {
    /**
     * Link to the least recently added node
     */
    private Node first;
    /**
     * Link to most recently added node
     */
    private Node last;
    /**
     * Number of items in the queue
     */
    private int n;

    /**
     * Private class to define nodes
     */
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    /**
     * Add item to the end of the list
     *
     * @param item
     */
    public void enqueue(final Item item) {
        final Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     * Remove item from the beginning of the list
     *
     * @return
     */
    public Item dequeue() {
        final Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {

        }

        @Override
        public Item next() {
            final Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
