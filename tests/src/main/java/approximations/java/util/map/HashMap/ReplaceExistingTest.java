package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@Test
public class ReplaceExistingTest {
    /* Number of entries required to trigger a resize for cap=16, load=0.75*/
    private static int ENTRIES = 13;

    @Test
    public static int test_ReplaceExisting (int execution) {
        for (int i = 0; i <= ENTRIES; i++) {
            HashMap<Integer,Integer> hm = prepHashMap();
            testItr(hm, i);
        }
        return execution;
    }

    /* Prepare a HashMap that will resize on next put() */
    private static HashMap<Integer,Integer> prepHashMap() {
        HashMap<Integer,Integer> hm = new HashMap<>(16, 0.75f);
        // Add items to one more than the resize threshold
        for (int i = 0; i < ENTRIES; i++) {
            hm.put(i*10, i*10);
        }
        return hm;
    }

    /* Iterate hm for elemBeforePut elements, then call put() to replace value
     * for existing key.  With bug 8025173, this will also cause a resize, but
     * not increase the modCount.
     * Finish the iteration to check for a corrupt iterator.
     */
    private static void testItr(HashMap<Integer,Integer> hm, int elemBeforePut) {
        if (elemBeforePut > hm.size()) {
            throw new IllegalArgumentException("Error in test: elemBeforePut must be <= HashMap size");
        }
        // Create a copy of the keys
        HashSet<Integer> keys = new HashSet<>(hm.size());
        keys.addAll(hm.keySet());

        HashSet<Integer> collected = new HashSet<>(hm.size());

        // Run itr for elemBeforePut items, collecting returned elems
        Iterator<Integer> itr = hm.keySet().iterator();
        for (int i = 0; i < elemBeforePut; i++) {
            Integer retVal = itr.next();
            if (!collected.add(retVal)) {
                throw new RuntimeException("Corrupt iterator: key " + retVal + " already encountered");
            }
        }

        // Do put() to replace entry (and resize table when bug present)
        if (null == hm.put(0, 100)) {
            throw new RuntimeException("Error in test: expected key 0 to be in the HashMap");
        }

        // Finish itr + collecting returned elems
        while(itr.hasNext()) {
            Integer retVal = itr.next();
            if (!collected.add(retVal)) {
                throw new RuntimeException("Corrupt iterator: key " + retVal + " already encountered");
            }
        }

        // Compare returned elems to original copy of keys
        if (!keys.equals(collected)) {
            throw new RuntimeException("Collected keys do not match original set of keys");
        }
    }
}
