package approximations.java.util.map.AbstractMap;

import approximations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

@Test
public class ToStringTest {
    @Test
    public static int test_toString(int execution) {
        Map m = new LyingMap();
        if (!m.toString().equals("{}"))
            return -1;

        m.put("x", "1");
        if (!m.toString().equals("{x=1}"))
            return -1;

        m.put("y", "2");
        String toStringResult = m.toString();
        if (!toStringResult.equals("{x=1, y=2}") && !toStringResult.equals("{y=2, x=1}"))
            return -1;
        return execution;
    }
}

class LyingMap extends LinkedHashMap {
    public int size() {
        return super.size() + 1; // Lies, lies, all lies!
    }
}
