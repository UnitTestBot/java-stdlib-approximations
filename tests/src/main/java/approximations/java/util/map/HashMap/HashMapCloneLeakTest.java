package approximations.java.util.map.HashMap;

import approximations.Test;

import java.lang.ref.WeakReference;
import java.util.HashMap;

@Test
public class HashMapCloneLeakTest {
    static WeakReference<Object> wr = null;

    // helper method to keep testObject and map out of main method's scope
    private static HashMap<Integer, Object> makeMap() {
        HashMap<Integer, Object> map = new HashMap<>();
        Object testObject = new Object();
        wr = new WeakReference<>(testObject);
        map.put(42, testObject);
        return map;
    }

    @Test
    public static int test_HashMapCloneLeak(int execution) throws Exception {
        HashMap<Integer, Object> hm = makeMap();
        hm = (HashMap<Integer, Object>)hm.clone();
        hm.clear();
        // There should no longer be a strong reference to testObject
        // the WeakReference should be nulled out by GC. If not,
        // we will hang here until timed out by the test harness.
        Object[] chain = null;
        while (wr.get() != null) {
            try {
                Object[] allocate = new Object[1000000];
                allocate[0] = chain;
                chain = allocate;
            } catch (OutOfMemoryError oome) {
                chain = null;
            }
            System.gc();
            Thread.sleep(100);
        }
        return execution;
    }
}
