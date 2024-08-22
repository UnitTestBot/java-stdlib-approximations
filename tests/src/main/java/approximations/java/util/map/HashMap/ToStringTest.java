package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class ToStringTest {
    @Test
    public static int test_ToString (int execution) {
        try {
            Map m = new HashMap();
            m.put(null, null);
            m.entrySet().iterator().next().toString();
        } catch (Throwable t) {
            return -1;
        }
        return execution;
    }
}
