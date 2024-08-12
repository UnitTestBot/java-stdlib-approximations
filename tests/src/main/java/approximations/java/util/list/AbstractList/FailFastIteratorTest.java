package approximations.java.util.list.AbstractList;

import approximations.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;

@Test
public class FailFastIteratorTest {
    @Test
    public static int test_failFastIteratorTest(int execution) {
        List orig = new ArrayList(100);
        for (int i=0; i<100; i++)
            orig.add(i);

        List copy = new ArrayList(orig);
        try {
            ListIterator i = copy.listIterator();
            i.next();
            copy.remove(99);
            copy.add(99);
            i.remove();
            return -1;
        } catch (ConcurrentModificationException e) {
        }
        if (!copy.equals(orig))
            return -1;

        try {
            ListIterator i = copy.listIterator();
            i.next();
            copy.remove(99);
            copy.add(99);
            i.set(666);
            return -1;
        } catch (ConcurrentModificationException e) {
        }
        if (!copy.equals(orig))
            return -1;

        try {
            ListIterator i = copy.listIterator();
            copy.remove(99);
            copy.add(99);
            i.add(666);
            return -1;
        } catch (ConcurrentModificationException e) {
        }
        if (!copy.equals(orig))
            return -1;
        return execution;
    }
}
