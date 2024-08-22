package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Test
public class KeySetRemoveTest {
    @Test
    public static int test_KeySetRemove (int execution) {
        Map[] m = {new HashMap()};
        for (int i=0; i<m.length; i++) {
            m[i].put("bananas", null);
            if (!m[i].keySet().remove("bananas"))
                return -1;
        }
        return execution;
    }
}
