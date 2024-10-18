package approximations.java.util.Collection;

import approximations.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Test
public class IteratorAtEndTest {
    private static final int SIZE = 6;

    /*static void realMain(String[] args) throws Throwable {
        testCollection(new ArrayList());
        testCollection(new LinkedList());

        testMap(new HashMap());
        testMap(new LinkedHashMap());
        testMap(new ConcurrentHashMap());
    }*/

    @Test
    public static int test_ArrayList(int execution) {
        if (testCollection(new ArrayList())) {
            return execution;
        } else {
            return -1;
        }
    }

    @Test
    public static int test_LinkedList(int execution) {
        if (testCollection(new LinkedList())) {
            return execution;
        } else {
            return -1;
        }
    }

    @Test
    public static int test_HashMap(int execution) {
        if (testMap(new HashMap())) {
            return execution;
        } else {
            return -1;
        }
    }

    @Test
    public static int test_LinkedHashMap(int execution) {
        if (testMap(new LinkedHashMap())) {
            return execution;
        } else {
            return -1;
        }
    }

    @Test
    public static int test_ConcurrentHashMap(int execution) {
        if (testMap(new ConcurrentHashMap())) {
            return execution;
        } else {
            return -1;
        }
    }

    static boolean testCollection(Collection c) {
        try {
            for (int i = 0; i < SIZE; i++)
                c.add(i);
            return test(c);
        } catch (Throwable t) { return false; }
    }

    static boolean testMap(Map m) {
        try {
            for (int i = 0; i < 3*SIZE; i++)
                m.put(i, i);
            /*test(m.values());
            test(m.keySet());
            test(m.entrySet());*/
            return test(m.values()) & test(m.keySet()) & test(m.entrySet());
        } catch (Throwable t) { return false; }
    }

    static boolean test(Collection c) {
        try {
            final Iterator it = c.iterator();
            /*THROWS(NoSuchElementException.class,
                    () -> { while (true) it.next(); });*/
            try {
                //while (true) it.next();
                for (int i = 0; i < 2 * c.size(); i++) {
                    it.next();
                }
                return false;
            } catch (NoSuchElementException e) {
            }
            try { it.remove(); }
            catch (UnsupportedOperationException exc) { return true; }
        } catch (Throwable t) { return false; }

        if (c instanceof List) {
            final List list = (List) c;
            try {
                final ListIterator it = list.listIterator(0);
                it.next();
                final Object x = it.previous();
                //THROWS(NoSuchElementException.class, () -> it.previous());
                try {
                    it.previous();
                    return false;
                } catch (NoSuchElementException e) {
                }
                try { it.remove(); }
                catch (UnsupportedOperationException exc) { return true; }
                //check(! list.get(0).equals(x));
                if (list.get(0).equals(x)) {
                    return false;
                }
            } catch (Throwable t) { return false; }

            try {
                final ListIterator it = list.listIterator(list.size());
                it.previous();
                final Object x = it.next();
                //THROWS(NoSuchElementException.class, () -> it.next());
                try {
                    it.next();
                    return false;
                } catch (NoSuchElementException e) {
                }
                try { it.remove(); }
                catch (UnsupportedOperationException exc) { return true; }
                //check(! list.get(list.size()-1).equals(x));
                if (list.get(list.size()-1).equals(x)) {
                    return false;
                }
            } catch (Throwable t) { return false; }
        }
        return true;
    }
}
