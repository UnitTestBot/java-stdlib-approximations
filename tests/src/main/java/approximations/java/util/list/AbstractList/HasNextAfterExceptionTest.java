package approximations.java.util.list.AbstractList;

import approximations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

@Test
public class HasNextAfterExceptionTest {
    @Test
    public static int test_hasNextAfterExceptionTest(int execution) {
        List list = new ArrayList();
        ListIterator i = list.listIterator();
        try {
            i.previous();
        }
        catch (NoSuchElementException e) {
        }
        if (i.hasNext()) {
            return -1;
        }
        return execution;
    }
}
