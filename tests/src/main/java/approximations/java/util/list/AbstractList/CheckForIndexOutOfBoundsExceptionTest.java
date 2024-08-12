package approximations.java.util.list.AbstractList;

import approximations.Test;

import java.util.*;

class MyList extends AbstractList<String> {

    private static final int SIZE = 2;

    public String get(int i) {
        if (i >= 0 && i < SIZE) {
            return "x";
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size() {
        return SIZE;
    }
}

@Test
public class CheckForIndexOutOfBoundsExceptionTest { // instead of returning execution, original class was checking if exception message was right
    List<String> list = new MyList();

    @Test
    public int checkIteratorNext(int execution) {
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
