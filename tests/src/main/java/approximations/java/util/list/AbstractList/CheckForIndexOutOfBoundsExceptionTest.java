package approximations.java.util.list.AbstractList;

import approximations.Test;

import java.util.*;

@Test
public class CheckForIndexOutOfBoundsExceptionTest { // instead of returning execution, original class was checking if exception message was right
    @Test
    public static int checkIteratorNext1(int execution) { // for whatever reason, results of 1st and 2nd versions may differ
        List<String> list = new ArrayList<>(2);
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
    public static int checkIteratorNext2(int execution) {
        List<String> list = new ArrayList<>(2);
        list.add("x");
        list.add("x");
        Iterator<String> iterator = list.iterator(); // position at start
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
    public static int checkListIteratorNext(int execution) {
        List<String> list = new ArrayList<>(2);
        list.add("x");
        list.add("x");
        ListIterator<String> iterator = list.listIterator(list.size()); // position at end
        try {
            iterator.next();
            return -1;
        } catch (NoSuchElementException e) {
            return execution;
        }
    }

    @Test
    public static int checkListIteratorPrevious(int execution) {
        List<String> list = new ArrayList<>(2);
        list.add("x");
        list.add("x");
        ListIterator<String> iterator = list.listIterator(0); // position at start
        try {
            iterator.previous();
            return -1;
        } catch (NoSuchElementException e) {
            return execution;
        }
    }
}
