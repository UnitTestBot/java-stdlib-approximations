package approximations.java.util.map.AbstractMap;

import approximations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class NullAsKeyTest {
    @Test
    static int test_NullAsKey(int execution) {
        Map m = new HashMap();
        m.put(null, "");
        return execution;
    }
}
