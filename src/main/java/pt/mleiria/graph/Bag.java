package pt.mleiria.graph;

import java.util.Iterator;


public class Bag<Item> implements Iterable<Item> {

    /**
     * First node in the list
     */
    private Node first;
    /**
     * Number of items
     */
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    /**
     * Add an item to the beginning
     *
     * @param item
     */
    public void add(final Item item) {
        final Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * @return true if the bag is empty
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * @return the size of the bag
     */
    public int size() {
        return n;
    }

    /**
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     *
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("We don't remove items from the bag");
        }

        @Override
        public Item next() {
            final Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
