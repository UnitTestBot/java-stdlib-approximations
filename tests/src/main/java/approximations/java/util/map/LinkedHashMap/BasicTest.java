package approximations.java.util.map.LinkedHashMap;

import approximations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;

@Test
public class BasicTest {
    static final Random rnd = new Random(666);
    static final Integer nil = 0;

    @Test
    public static int test_Basic (int execution)  throws Exception {
        int numItr =  20;
        int mapSize = 20;

        // Linked List testk
        for (int i=0; i<numItr; i++) {
            Map<Integer,Integer> m = new LinkedHashMap();
            Integer head = nil;

            for (int j=0; j<mapSize; j++) {
                Integer newHead;
                do {
                    newHead = rnd.nextInt();
                } while (m.containsKey(newHead));
                m.put(newHead, head);
                head = newHead;
            }
            if (m.size() != mapSize)
                return -1;

            if (new HashMap(m).hashCode() != m.hashCode())
                return -1;

            Map<Integer,Integer> m2 = new LinkedHashMap(); m2.putAll(m);
            m2.values().removeAll(m.keySet());
            if (m2.size()!= 1 || !m2.containsValue(nil))
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

        /*try {
            m2 = serClone(m);
        } catch (Exception e) {
            return -1;
        }
        if (!m.equals(m2))
            return -1;
        if (!m2.equals(m))
            return -1;
        s = m.entrySet(); s2 = m2.entrySet();
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
            return -1;*/

        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        if (!m.isEmpty())
            return -1;

        // Test ordering properties with insert order
        m = new LinkedHashMap();
        List<Integer> l = new ArrayList(mapSize);
        for (int i=0; i<mapSize; i++) {
            Integer x = i;
            m.put(x, x);
            l.add(x);
        }
        if (!new ArrayList(m.keySet()).equals(l))
            return -1;
        for (int i=mapSize-1; i>=0; i--) {
            Integer x = (Integer) l.get(i);
            if (!m.get(x).equals(x))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l))
            return -1;

        for (int i=mapSize-1; i>=0; i--) {
            Integer x = l.get(i);
            m.put(x, x);
        }
        if (!new ArrayList(m.keySet()).equals(l))
            return -1;

        m2 = (Map) ((LinkedHashMap)m).clone();
        if (!m.equals(m2))
            return -1;

        List<Integer> l2 = new ArrayList(l);
        Collections.shuffle(l2);
        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!m2.get(x).equals(x))
                return -1;
        }
        if (!new ArrayList(m2.keySet()).equals(l))
            return -1;

        // Test ordering properties with access order
        m = new LinkedHashMap(2*mapSize, .75f, true);
        for (int i=0; i<mapSize; i++) {
            Integer x = i;
            m.put(x, x);
        }
        if (!new ArrayList(m.keySet()).equals(l))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!m.get(x).equals(x))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!m.getOrDefault(x, i + 1000).equals(x))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!m.replace(x, x).equals(x))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!m.replace(x, x, x))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        BiFunction<Integer,Integer,Integer> f = (Integer y, Integer z) -> {
            if (!Objects.equals(y,z))
                return -1;
            return z;
        };

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!x.equals(m.merge(x, x, f)))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!x.equals(m.compute(x, f)))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!x.equals(m.remove(x)))
                return -1;
            if (!x.equals(m.computeIfAbsent(x, Integer::valueOf)))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = l2.get(i);
            if (!x.equals(m.computeIfPresent(x, f)))
                return -1;
        }
        if (!new ArrayList(m.keySet()).equals(l2))
            return -1;

        for (int i=0; i<mapSize; i++) {
            Integer x = i;
            m.put(x, x);
        }
        if (!new ArrayList(m.keySet()).equals(l))
            return -1;

        m2 = (Map) ((LinkedHashMap)m).clone();
        if (!m.equals(m2))
            return -1;
        for (int i=0; i<mapSize; i++) {
            Integer x = l.get(i);
            if (!m2.get(x).equals(x))
                return -1;
        }
        if (!new ArrayList(m2.keySet()).equals(l))
            return -1;

        return execution;
    }

    private static Map serClone(Map m) throws IOException, ClassNotFoundException {
        Map result = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(m);
        out.flush();

            // Deserialize
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        out.close();
        ObjectInputStream in = new ObjectInputStream(bis);
        result = (Map)in.readObject();
        in.close();
        return result;
    }
}
