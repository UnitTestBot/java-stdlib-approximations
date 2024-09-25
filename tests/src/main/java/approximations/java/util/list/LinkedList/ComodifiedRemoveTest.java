package approximations.java.util.list.LinkedList;

import approximations.Test;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@Test
public class ComodifiedRemoveTest {
    @Test
    public static int test_ComodifiedRemove(int execution) {
        List list = new LinkedList();
        Object o1 = 1;
        list.add(o1);
        ListIterator e = list.listIterator();
        e.next();
        Object o2 = 2;
        list.add(o2);

        try {
            e.remove();
        } catch (ConcurrentModificationException cme) {
            return execution;
        }

        return -1;
    }
}
