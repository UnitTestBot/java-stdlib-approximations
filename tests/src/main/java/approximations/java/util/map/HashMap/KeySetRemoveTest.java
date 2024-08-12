package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Test
public class KeySetRemoveTest {
    @Test(disabled = true)
    public static int test_KeySetRemove (int execution) throws Exception {
        Map[] m = {new HashMap(), new TreeMap()};
        for (int i=0; i<m.length; i++) {
            m[i].put("bananas", null);
            if (!m[i].keySet().remove("bananas"))
                return -1;
        }
        return execution;
    }
}
