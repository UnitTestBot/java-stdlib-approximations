package approximations.java.util.map.AbstractMap;

import approximations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Test
public class MapEntriesTest {
    private static int testEntry(Map.Entry<String,Long> e) {
        String k = "foo";
        Long v = 1L;
        Long v2 = 2L;
        /*if (!equal(e.getKey(), k)) {
            return -1;
        }
        if (!equal(e.getValue(), v)) {
            return -1;
        }
        Map<String, Long> mapWithSameEntry = new LinkedHashMap<>();
        mapWithSameEntry.put(k, v);
        if (!equal(e, mapWithSameEntry.entrySet().iterator().next())) {
            return -1;
        }*/
        Map<String, Long> mapWithAnotherEntry = new LinkedHashMap<>();
        mapWithAnotherEntry.put(k, v2);
        if (e.equals(mapWithAnotherEntry.entrySet().iterator().next())) {
            return -1;
        }
        /*if (e.equals(null)) {
            return -1;
        }*/
        String test = k + "=" + v;
        /*if (!equal(e.toString(), k+"="+v)) {
            return -1;
        }
        if (!equal(e.setValue(v2), v)) {
            return -1;
        }
        if (!equal(e.getValue(), v2)) {
            return -1;
        }
        if (!equal(e.setValue(null), v2)) {
            return -1;
        }
        if (!equal(e.getValue(), null)) {
            return -1;
        }*/
        return 0;
    }

    private static int testNullEntry(Map.Entry<String,Long> e) {
        Long v = 1L;
        if (!equal(e.getKey(), null)) {
            return -1;
        }
        if (!equal(e.getValue(), null)) {
            return -1;
        }
        Map<String, Long> mapWithSameEntry = new LinkedHashMap<>();
        mapWithSameEntry.put(null, null);
        if (!equal(e, mapWithSameEntry.entrySet().iterator().next())) {
            return -1;
        }
        if (!equal(e.toString(), "null=null")) {
            return -1;
        }
        if (!equal(e.setValue(v), null)) {
            return -1;
        }
        if (!equal(e.getValue(), v)) {
            return -1;
        }
        return 0;
    }

    static boolean equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) return true;
        else return false;}

    @Test
    public static int test_ConcreteEntry(int execution) {
        String k = "foo";
        Long v = 1L;
        Map<String, Long> map = new HashMap<>();
        map.put(k, v);
        return testEntry(map.entrySet().iterator().next());
    }

    @Test
    public static int test_NullEntry(int execution) {
        Map<String, Long> map = new LinkedHashMap<>();
        map.put(null, null);
        return testNullEntry(map.entrySet().iterator().next());
    }
}
