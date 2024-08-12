package approximations.java.util.map.LinkedHashMap;

import approximations.Test;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

@Test
public class EmptyMapIteratorTest {
    @Test
    public static int test_EmptyMapIterator (int execution) throws Exception {
        HashMap map = new HashMap();
        Iterator iter = map.entrySet().iterator();
        map.put("key", "value");

        try {
            iter.next();
            return -1;
        } catch (ConcurrentModificationException e) {
        }
        return execution;
    }
}
