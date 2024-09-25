package approximations.java.util.map.AbstractMap;

import approximations.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Test
public class AbstractMapCloneTest extends AbstractMap implements Cloneable {
    private Map map = new HashMap();

    public Set entrySet() {
        return map.entrySet();
    }

    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    public Object clone() {
        final AbstractMapCloneTest clone;
        try {
            clone = (AbstractMapCloneTest)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
        clone.map = (Map)((HashMap)map).clone();
        return clone;
    }

    public int size() {
        return map.size();
    }

    public Set<?> keySet() {
        return map.keySet();
    }

    @Test
    public static int test_abstractMapClone(int execution) {
        AbstractMapCloneTest m1 = new AbstractMapCloneTest();
        m1.put("1", "1");
        Set k1 = m1.keySet();
        AbstractMapCloneTest m2 = (AbstractMapCloneTest)m1.clone();
        Set k2 = m2.keySet();
        m2.put("2","2");
        if (k1.equals(k2)) {
            return -1;
        }
        if (m2.size() != 2) {
            return -1;
        }
        return execution;
    }

    @Test
    public static int test_hashMapClone(int execution) {
        Map<Integer, Integer> m1 = new HashMap<>();
        m1.put(1, 1);
        Set<Integer> k1 = m1.keySet();
        Map<Integer, Integer> m2 = (Map<Integer, Integer>) ((HashMap<Integer, Integer>) m1).clone();
        Set<Integer> k2 = m2.keySet();
        m2.put(2, 2);
        if (k1.equals(k2)) {
            return -1;
        } else {
            return execution;
        }
    }
}
