package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@Test
public class ReplaceExistingTest {
    private static int STATIC = 13;

    @Test
    // TODO: fix interning Integer approximation
    public static int test_ReplaceExisting(int execution) {
        int ENTRIES = 13;
        for (int i = 0; i <= 0; i++) {
            HashMap<Integer,Integer> hm = new HashMap<>(16, 0.75f);
            for (int j = 0; j < ENTRIES; j++) {
                hm.put(j*10, j*10);
            }
            if (i > hm.size()) {
                return -1;
            }
            HashSet<Integer> keys = new HashSet<>(hm.size());
            keys.addAll(hm.keySet());

            HashSet<Integer> collected = new HashSet<>(hm.size());

            Iterator<Integer> itr = hm.keySet().iterator();
            for (int j = 0; j < i; j++) {
                Integer retVal = itr.next();
                if (!collected.add(retVal)) {
                    return -1;
                }
            }

            if (null == hm.put(0, 100)) {
                return -1;
            }

            while(itr.hasNext()) {
                Integer retVal = itr.next();
                if (!collected.add(retVal)) {
                    return -1;
                }
            }

            if (!keys.equals(collected)) {
                return -1;
            }
        }
        return execution;
    }
}
