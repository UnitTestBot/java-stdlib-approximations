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
        testCollection(new Vector<>());
        testCollection(new CopyOnWriteArrayList<>());
        testCollection(new CopyOnWriteArraySet<>());
    }

    private static void testCollection(Collection<Object> c) {
        System.out.println(c.getClass());
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
    static void fail() { failed++; Thread.dumpStack(); }
    static void fail(String msg) { System.out.println(msg); fail(); }
    static void unexpected(Throwable t) { failed++; t.printStackTrace(); }
    static void check(boolean cond) { if (cond) pass(); else fail(); }
    static void equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) pass();
        else {System.out.println(x + " not equal to " + y); fail(); }}

    @Test
    public static int test_toString(int execution) {
        try { realMain(); } catch (Throwable t) { unexpected(t); }

        if (failed > 0) {
            return -1;
        } else {
            return execution;
        }
    }
}
