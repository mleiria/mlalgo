package pt.mleiria.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class BagTest {

    @Test
    public void test_bag_size() {
        Bag<String> bag = new Bag<>();
        bag.add("to");
        bag.add("be");
        bag.add("or");
        bag.add("not");
        bag.add("to");
        bag.add("be");
        Assert.assertEquals(6, bag.size());
        Iterator<String> iterator = bag.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        bag.forEach(elem -> System.out.println(elem.toString()));
    }

    @Test
    public void test_empty_bag() {
        Bag<String> bag = new Bag<>();
        Assert.assertEquals(true, bag.isEmpty());
    }

    @Test
    public void test_add_element_to_bag() {
        Bag<String> bag = new Bag<>();
        Assert.assertEquals(0, bag.size());
        bag.add("Hello");
        Assert.assertEquals(1, bag.size());
    }
}
