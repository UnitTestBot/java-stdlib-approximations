package approximations.java.util.map.LinkedHashMap;

import approximations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

@Test
public class CacheTest {
    private static final int MAP_SIZE = 10;
    private static final int NUM_KEYS = 100;

    @Test(disabled = true)
    // TODO: LinkedHashMap can't currently handle removeEldestEntry
    public static int test_Cache(int execution) {
        try {
            Map m = new LinkedHashMap() {
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > MAP_SIZE;
                }
            };

            for (int i = 0; i < NUM_KEYS; i++) {
                m.put(i, ""); //
                int eldest = ((Integer) m.keySet().iterator().next()).intValue();
                if (eldest != Math.max(i - 9, 0))
                    return -1;
            }
        } catch (Throwable t) {
            return -1;
        }
        return execution;
    }
}
