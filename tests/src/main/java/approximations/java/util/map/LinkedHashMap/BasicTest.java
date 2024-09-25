package approximations.java.util.map.LinkedHashMap;

import approximations.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Test
public class BasicTest {
    @Test
    public static int test_Basic1(int execution) {
        int nil = 0;
        int numItr =  1;
        int mapSize = 10;
        for (int i=0; i<numItr; i++) {
            Map<Integer,Integer> m = new LinkedHashMap();
            int head = nil;

            for (int j=0; j<mapSize; j++) {
                Integer newHead;
                do {
                    newHead = j + 1;
                } while (m.containsKey(newHead));
                m.put(newHead, head);
                head = newHead;
            }
            if (m.size() != mapSize)
                return -1;

            Map<Integer,Integer> m2 = new LinkedHashMap(); m2.putAll(m);
            if (m2.size() != 10) {
                return -1;
            }
            Set<Integer> keySet = m.keySet();
            if (keySet.size() != 10) {
                return -1;
            }
            m2.values().removeAll(m.keySet());
            if (m2.size() != 1 || !m2.containsValue(nil))
                return -1;

            int j=0;
            while (head != nil) {
                if (!m.containsKey(head))
                    return -1;
                Integer newHead = m.get(head);
                if (newHead == null)
                    return -1;
                m.remove(head);
                head = newHead;
                j++;
            }
            if (!m.isEmpty())
                return -1;
            if (j != mapSize)
                return -1;
        }

        return execution;
    }

    @Test
    public static int test_Basic2(int execution) {
        int nil = 0;
        int mapSize = 10;
        Map<Integer,Integer> m = new LinkedHashMap();
        for (int i=0; i<mapSize; i++)
            if (m.put(i, 2*i) != null)
                return -1;
        for (int i=0; i<2*mapSize; i++)
            if (m.containsValue(i) != (i%2==0))
                return -1;
        if (m.put(nil, nil) == null)
            return -1;
        Map<Integer,Integer> m2 = new LinkedHashMap(); m2.putAll(m);
        if (!m.equals(m2))
            return -1;
        if (!m2.equals(m))
            return -1;
        Set<Map.Entry<Integer,Integer>> s = m.entrySet(), s2 = m2.entrySet();
        if (!s.equals(s2))
            return -1;
        if (!s2.equals(s))
            return -1;
        if (!s.containsAll(s2))
            return -1;
        if (!s2.containsAll(s))
            return -1;
        s2.removeAll(s);
        if (!m2.isEmpty())
            return -1;

        m2.putAll(m);
        m2.clear();
        if (!m2.isEmpty())
            return -1;

        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        if (!m.isEmpty())
            return -1;

        return execution;
    }
}
