package approximations.java.util.map.HashMap;

import approximations.Test;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Test
public class HashMapCloneLeakTest {

    @Test
    public static int test_HashMapCloneLeak(int execution) {
        int size = 2;
        Random random = new Random(777);
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map1.put(random.nextInt(), random.nextInt());
        }

        Map<Integer, Integer> map2 = (Map<Integer, Integer>) ((HashMap<Integer, Integer>) map1).clone();
        if (!map1.equals(map2)) {
            return -1;
        }
        return execution;
    }
}
