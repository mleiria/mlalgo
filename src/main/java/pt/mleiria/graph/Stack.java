package pt.mleiria.graph;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * LIFO
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item> {

    /**
     * top of stack. Most recently added node
     */
    private Node first;
    /**
     * Number of items
     */
    private int n;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
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


    /**
     * Nested class to define nodes
     */
    private class Node {
        Item item;
        Node next;
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        return null == first;
    }

    /**
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * Add item to the top of the stack
     *
     * @param item
     */
    public void push(final Item item) {
        final Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * Remove item from top of stack
     *
     * @return
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }


}
