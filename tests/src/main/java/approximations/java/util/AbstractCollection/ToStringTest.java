package approximations.java.util.AbstractCollection;

import approximations.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Test
public class ToStringTest {
    private static void realMain() {
        testCollection(new LinkedHashSet<Object>() {
            public int size() {
                return super.size() + 1; // Lies, lies, all lies!
            }});
        testCollection(new ArrayList<>());
        //testCollection(new Vector<>());
        //testCollection(new CopyOnWriteArrayList<>());
        //testCollection(new CopyOnWriteArraySet<>());
    }

    private static void testCollection(Collection<Object> c) {
        equal(c.toString(), "[]");
        check(c.add("x"));
        equal(c.toString(), "[x]");
        check(c.add("y"));
        equal(c.toString(), "[x, y]");
        check(c.add(null));
        equal(c.toString(), "[x, y, null]");
        if (c instanceof AbstractCollection) {
            check(c.add(c));
            equal(c.toString(), "[x, y, null, (this Collection)]");
        }
    }

    //--------------------- Infrastructure ---------------------------
    static volatile int passed = 0, failed = 0;
    static void pass() { passed++; }
    static void fail() { failed++; }
    static void unexpected() { failed++; }
    static void check(boolean cond) { if (cond) pass(); else fail(); }
    static void equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) pass();
        else { fail(); }}

    @Test(disabled = true)
    public static int test_toString(int execution) {
        try { realMain(); } catch (Throwable t) { unexpected(); }

        if (failed > 0) {
            return -1;
        } else {
            return execution;
        }
    }
}
