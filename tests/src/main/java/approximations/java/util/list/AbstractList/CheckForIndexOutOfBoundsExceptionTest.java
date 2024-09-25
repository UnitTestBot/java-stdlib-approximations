package approximations.java.util.list.AbstractList;

import approximations.Test;

import java.util.*;

@Test
public class CheckForIndexOutOfBoundsExceptionTest {
    List<String> list = new ArrayList<>(2);

    @Test
    public int checkIteratorNext1(int execution) {
        List<String> list = new ArrayList<>(2);
        list.add("x");
        list.add("x");
        Iterator<String> iterator = list.iterator();
        try {
            for (int i = 0; i <= list.size(); i++) {
                iterator.next();
            }
            return -1;
        } catch (NoSuchElementException e) {
            return execution;
        }
    }

    @Test
    public int checkIteratorNext2(int execution) {
        List<String> list = new ArrayList<>(2);
        list.add("x");
        list.add("x");
        Iterator<String> iterator = list.iterator();
        for (int i = 0; i <= list.size(); i++) {
            try {
                iterator.next();
            } catch (NoSuchElementException e) {
                if (i == list.size()) {
                    return execution;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    @Test
    public int checkListIteratorNext(int execution) {
        ListIterator<String> iterator = list.listIterator(list.size());
        try {
            iterator.next();
            return -1;
        } catch (NoSuchElementException e) {
            return execution;
        }
    }

    @Test
    public int checkListIteratorPrevious(int execution) {
        ListIterator<String> iterator = list.listIterator(0);
        try {
            iterator.previous();
            return -1;
        } catch (NoSuchElementException e) {
            return execution;
        }
    }
}
