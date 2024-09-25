package approximations.java.util.map.LinkedHashMap;

import approximations.Test;

import java.util.LinkedHashMap;

@Test
public class ComputeIfAbsentAccessOrderTest {
    @Test
    public static int test_ComputeIfAbsentAccessOrder(int execution) {
        try {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>(2, 0.75f, true);
            map.put("first", null);
            map.put("second", null);

            map.computeIfAbsent("first", l -> null);

            String key = map.keySet().stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("no value"));
            if (!"first".equals(key) && !"second".equals(key)) {
                return -1;
            }
        } catch (Throwable t) {
            return -1;
        }
        return execution;
    }
}
