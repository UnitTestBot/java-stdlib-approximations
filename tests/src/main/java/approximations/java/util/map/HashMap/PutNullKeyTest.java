package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class PutNullKeyTest {
    static final int INITIAL_CAPACITY = 5;

    static final int SIZE = 10;

    static final float LOAD_FACTOR = 1.0f;

    public static class CollidingHash implements Comparable<CollidingHash> {

        private final int value;

        public CollidingHash(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (null == o) {
                return false;
            }

            if (o.getClass() != CollidingHash.class) {
                return false;
            }

            return value == ((CollidingHash) o).value;
        }

        @Override
        public int compareTo(CollidingHash o) {
            return value - o.value;
        }
    }

    @Test
    public static int test_PutNullKey(int execution) {
        Map<Object,Object> m = new HashMap<>(INITIAL_CAPACITY, LOAD_FACTOR);
        for (int i = 0; i < SIZE; i++) {
            CollidingHash newHash = new CollidingHash(i);
            m.put(newHash, newHash);
        }

        m.put(null, null);
        return execution;
    }
}
