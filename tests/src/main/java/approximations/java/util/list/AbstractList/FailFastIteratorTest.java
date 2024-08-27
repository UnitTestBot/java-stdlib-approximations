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
        List orig = new ArrayList(5);
        for (int i=95; i<100; i++)
            orig.add(i);

        List copy = new ArrayList(orig);
        return copy.size();
        //ListIterator i = copy.listIterator();
        //i.next();
        /*try {
            ListIterator i = orig.listIterator();
            i.next();
            orig.remove(4);
            orig.add(99);
            i.remove();
            return -1;
        } catch (ConcurrentModificationException e) {
        }
        /*if (!copy.equals(orig))
            return -1;*/

        /*try {
            ListIterator i = orig.listIterator();
            i.next();
            orig.remove(4);
            orig.add(99);
            i.set(666);
            return -1;
        } catch (ConcurrentModificationException e) {
        }
        /*if (!copy.equals(orig))
            return -1;*/

        /*try {
            ListIterator i = orig.listIterator();
            orig.remove(4);
            orig.add(99);
            i.add(666);
            return -1;
        } catch (ConcurrentModificationException e) {
        }
        /*if (!copy.equals(orig))
            return -1;*/
        //return execution;
    }
}
