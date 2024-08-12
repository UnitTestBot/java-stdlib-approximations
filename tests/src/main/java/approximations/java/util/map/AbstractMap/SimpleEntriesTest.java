package approximations.java.util.map.AbstractMap;

import approximations.Test;

import java.util.AbstractMap;
import java.util.Map;

@Test
public class SimpleEntriesTest {
    private static String k = "foo";
    private static Long v = 1L;
    private static Long v2 = 2L;
    private static void realMain() {
        testEntry(new AbstractMap.SimpleEntry<>(k, v));
        testEntry(new AbstractMap.SimpleImmutableEntry<>(k, v));
        //testEntry(Map.entry(k,v));
        //testEntry(Map.Entry.copyOf(Map.entry(k,v)));
        //testEntry(new NullableKeyValueHolder(k,v));
        testNullEntry(new AbstractMap.SimpleEntry<>(null, null));
        testNullEntry(new AbstractMap.SimpleImmutableEntry<>(null, null));
        //testNullEntry(new NullableKeyValueHolder(null,null));
    }

    private static void testEntry(Map.Entry<String,Long> e) {
        equal(e.getKey(), k);
        equal(e.getValue(), v);
        equal(e, new AbstractMap.SimpleEntry<>(k, v));
        check(! e.equals(new AbstractMap.SimpleEntry<>(k, v2)));
        check(! e.equals(null));
        equal(e, new AbstractMap.SimpleImmutableEntry<>(k, v));
        equal(e.toString(), k+"="+v);
        check(e.hashCode() == 101575); // hash("foo") ^ hash(1L)
        if (e instanceof AbstractMap.SimpleEntry) {
            equal(e.setValue(v2), v);
            equal(e.getValue(), v2);
            equal(e.setValue(null), v2);
            equal(e.getValue(), null);
        } else {
            try { e.setValue(v2); fail(); }
            catch (UnsupportedOperationException t) {}
            catch (Throwable t) { unexpected(t); }
        }
    }

    private static void testNullEntry(Map.Entry<String,Long> e) {
        equal(e.getKey(), null);
        equal(e.getValue(), null);
        equal(e, new AbstractMap.SimpleEntry<String,Long>(null, null));
        equal(e, new AbstractMap.SimpleImmutableEntry<String,Long>(null, null));
        equal(e.toString(), "null=null");
        check(e.hashCode() == 0);
        if (e instanceof AbstractMap.SimpleEntry) {
            equal(e.setValue(v), null);
            equal(e.getValue(), v);
        } else {
            try { e.setValue(null); fail(); }
            catch (UnsupportedOperationException t) {}
            catch (Throwable t) { unexpected(t); }
        }
    }

    //--------------------- Infrastructure ---------------------------
    static volatile int passed = 0, failed = 0;
    static void pass() {passed++;}
    static void fail() {failed++; Thread.dumpStack();}
    static void fail(String msg) {System.out.println(msg); fail();}
    static void unexpected(Throwable t) {failed++; t.printStackTrace();}
    static void check(boolean cond) {if (cond) pass(); else fail();}
    static void equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) pass();
        else fail(x + " not equal to " + y);}
    @Test
    public static int test_SimpleEntries(int execution) {
        try {
            realMain();
        } catch (Throwable t) {
            unexpected(t);
        }
        if (failed > 0) {
            return -1;
        } else {
            return execution;
        }
    }
}
