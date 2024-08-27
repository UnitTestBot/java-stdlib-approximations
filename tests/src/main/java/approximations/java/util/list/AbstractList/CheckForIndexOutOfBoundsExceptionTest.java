package approximations.java.util.list.AbstractList;

import approximations.Test;

import java.util.*;

@Test
public class CheckForIndexOutOfBoundsExceptionTest { // instead of returning execution, original class was checking if exception message was right
    List<String> list = new ArrayList<>(2);

    @Test
    public int checkIteratorNext(int execution) {
        list.add("x");
        list.add("x");
        Iterator<String> iterator = list.iterator(); // position at start
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
    public int checkListIteratorNext(int execution) {
        ListIterator<String> iterator = list.listIterator(list.size()); // position at end
        try {
            iterator.next();
            return -1;
        } catch (NoSuchElementException e) {
            return execution;
        }
    }

    @Test
    public int checkListIteratorPrevious(int execution) {
        ListIterator<String> iterator = list.listIterator(0); // position at start
        try {
            iterator.previous();
            return -1;
        } catch (NoSuchElementException e) {
            return execution;
        }
    }
}
