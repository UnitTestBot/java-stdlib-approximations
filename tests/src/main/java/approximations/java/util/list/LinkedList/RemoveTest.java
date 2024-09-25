package approximations.java.util.list.LinkedList;

import approximations.Test;

import java.util.LinkedList;
import java.util.ListIterator;

@Test
public class RemoveTest {
    @Test
    public static int test_Remove(int execution) {
        LinkedList list = new LinkedList();
        ListIterator e = list.listIterator();
        Object o = 1;
        e.add(o);
        e.previous();
        e.next();
        e.remove();
        e.add(o);
        if (!o.equals(list.get(0)))
            return -1;
        return execution;
    }
}
