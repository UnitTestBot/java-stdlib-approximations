package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.Map;
import java.util.HashMap;

@Test
public class SetValueTest {
    static final String key = "key";
    static final String oldValue = "old";
    static final String newValue = "new";

    @Test
    public static int test_SetValue(int execution) {
        Map m = new HashMap();
        m.put(key, oldValue);
        Map.Entry e = (Map.Entry) m.entrySet().iterator().next();
        Object returnVal = e.setValue(newValue);
        if (!returnVal.equals(oldValue))
            return -1;
        return execution;
    }
}
