package approximations.java.util.map.AbstractMap;

import approximations.Test;

import java.util.*;

@Test
public class EqualsTest {
    @Test
    public static int test_equalsTest(int execution) {
        Map m = new HashMap();
        m.put(null, "");
        Map h = new Hashtable();
        h.put("", "");
        if (m.equals(h))
            return -1;

        Map m1 = new TreeMap();
        m1.put(42, "The Answer");
        Map m2 = new TreeMap();
        m2.put("The Answer", 42);
        if (m1.equals(m2))
            return -1;

        Set s1 = new TreeSet();
        s1.add(666);
        Set s2 = new TreeSet();
        s2.add("Great googly moogly!");
        if (s1.equals(s2))
            return -1;
        return execution;
    }
}
