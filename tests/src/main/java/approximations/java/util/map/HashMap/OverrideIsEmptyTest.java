package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.HashMap;

@Test
public class OverrideIsEmptyTest {
    public static class NotEmptyHashMap<K,V> extends HashMap<K,V> {
        private K alwaysExistingKey;
        private V alwaysExistingValue;

        @Override
        public V get(Object key) {
            if (key == alwaysExistingKey) {
                return alwaysExistingValue;
            }
            return super.get(key);
        }

        @Override
        public int size() {
            return super.size() + 1;
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }
    }

    @Test
    public static int test_OverrideIsEmpty (int execution) {
        NotEmptyHashMap<Object, Object> map = new NotEmptyHashMap<>();
        Object key = new Object();
        Object value = new Object();
        map.get(key);
        map.remove(key);
        map.replace(key, value, null);
        map.replace(key, value);
        map.computeIfPresent(key, (key1, oldValue) -> oldValue);
        return execution;
    }
}
