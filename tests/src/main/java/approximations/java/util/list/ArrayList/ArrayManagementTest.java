package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.lang.reflect.Field;
import java.util.*;

@Test
public class ArrayManagementTest {
    static final int DEFAULT_CAPACITY = 10;
    static final Field ELEMENT_DATA;
    static final Field MODCOUNT;
    static final SplittableRandom rnd = new SplittableRandom();

    static {
        try {
            ELEMENT_DATA = ArrayList.class.getDeclaredField("elementData");
            ELEMENT_DATA.setAccessible(true);
            MODCOUNT = AbstractList.class.getDeclaredField("modCount");
            MODCOUNT.setAccessible(true);
        } catch (ReflectiveOperationException huh) {
            throw new AssertionError(huh);
        }
    }

    static Object[] elementData(ArrayList<?> list) {
        try {
            return (Object[]) ELEMENT_DATA.get(list);
        } catch (ReflectiveOperationException huh) {
            throw new AssertionError(huh);
        }
    }

    static int modCount(ArrayList<?> list) {
        try {
            return MODCOUNT.getInt(list);
        } catch (ReflectiveOperationException huh) {
            throw new AssertionError(huh);
        }
    }

    static int capacity(ArrayList<?> list) {
        return elementData(list).length;
    }

    static int newCapacity(int oldCapacity) {
        return oldCapacity + (oldCapacity >> 1);
    }

    static void failAssert() {
        throw new AssertionError();
    }

    static void ensureCapacity(ArrayList<Object> list, int capacity) {
        int oldCapacity = capacity(list);
        int oldModCount = modCount(list);
        list.ensureCapacity(capacity);
        if (!(capacity(list) >= capacity || capacity(list) == 0)) {
            failAssert();
        }
        if (modCount(list) != ((capacity(list) == oldCapacity) ? oldModCount : oldModCount + 1)) {
            failAssert();
        }
    }

    static List<Object> singletonList() {
        return Collections.singletonList(Boolean.TRUE);
    }

    /** Opportunistically randomly test various add operations. */
    static void addOneElement(ArrayList<Object> list) {
        int size = list.size();
        int modCount = modCount(list);
        switch (rnd.nextInt(4)) {
            case 0:
                if (!list.add(Boolean.TRUE)) {
                    failAssert();
                }
                break;
            case 1:
                list.add(size, Boolean.TRUE);
                break;
            case 2:
                if (!list.addAll(singletonList())) {
                    failAssert();
                }
                break;
            case 3:
                if (!list.addAll(size, singletonList())) {
                    failAssert();
                }
                break;
            default: throw new AssertionError();
        }
        if (modCount(list) != modCount + 1) {
            failAssert();
        }
        if (list.size() != size + 1) {
            failAssert();
        }
    }

    @Test
    public int test_defaultCapacity(int execution) {
        ArrayList<Object> list = new ArrayList<>();
        if (capacity(new ArrayList<>()) != 0) {
            failAssert();
        }
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            addOneElement(list);
            if (capacity(list) != DEFAULT_CAPACITY) {
                failAssert();
            }
        }
        addOneElement(list);
        if (capacity(list) != newCapacity(DEFAULT_CAPACITY)) {
            failAssert();
        }
        return execution;
    }

    @Test
    public int test_defaultCapacityEnsureCapacity(int execution) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i <= DEFAULT_CAPACITY; i++) {
            ensureCapacity(list, i);     // no-op!
            if (capacity(list) != 0) {
                failAssert();
            }
        }
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            addOneElement(list);
            if (capacity(list) != DEFAULT_CAPACITY) {
                failAssert();
            }
        }
        addOneElement(list);
        /*assertEquals(capacity(list), newCapacity(DEFAULT_CAPACITY)); // i'm not quite sure what to do about this lambda
        {
            int capacity = capacity(list);
            ensureCapacity(list, capacity + 1);
            assertEquals(capacity(list), newCapacity(capacity));
        }
        {
            int capacity = capacity(list);
            ensureCapacity(list, 3 * capacity);
            assertEquals(capacity(list), 3 * capacity);
        }*/
        return execution;
    }

    @Test
    public int test_ensureCapacityBeyondDefaultCapacity(int execution) {
        ArrayList<Object> list = new ArrayList<>();
        list.ensureCapacity(DEFAULT_CAPACITY + 1);
        if (capacity(list) != DEFAULT_CAPACITY + 1) {
            failAssert();
        }
        for (int i = 0; i < DEFAULT_CAPACITY + 1; i++) {
            addOneElement(list);
            if (capacity(list) != DEFAULT_CAPACITY + 1) {
                failAssert();
            }
        }
        addOneElement(list);
        if (capacity(list) != newCapacity(DEFAULT_CAPACITY + 1)) {
            failAssert();
        }
        return execution;
    }

    @Test
    public int test_explicitZeroCapacity(int execution) {
        ArrayList<Object> list = new ArrayList<>(0);
        if (capacity(list) != 0) {
            failAssert();
        }
        addOneElement(list);
        if (capacity(list) != 1) {
            failAssert();
        }
        addOneElement(list);
        if (capacity(list) != 2) {
            failAssert();
        }
        addOneElement(list);
        if (capacity(list) != 3) {
            failAssert();
        }
        addOneElement(list);
        if (capacity(list) != 4) {
            failAssert();
        }
        addOneElement(list);
        if (capacity(list) != 6) {
            failAssert();
        }
        addOneElement(list);
        if (capacity(list) != 6) {
            failAssert();
        }
        addOneElement(list);
        if (capacity(list) != 9) {
            failAssert();
        }
        list.clear();
        if (capacity(list) != 9) {
            failAssert();
        }
        return execution;
    }

    @Test
    public int test_explicitLargeCapacity(int execution) {
        int n = DEFAULT_CAPACITY * 3;
        ArrayList<Object> list = new ArrayList<>(n);
        if (capacity(list) != n) {
            failAssert();
        }
        ensureCapacity(list, 0);
        ensureCapacity(list, n);
        for (int i = 0; i < n; i++) addOneElement(list);
        if (capacity(list) != n) {
            failAssert();
        }

        addOneElement(list);
        if (capacity(list) != newCapacity(n)) {
            failAssert();
        }
        return execution;
    }

    @Test
    public int test_emptyArraysAreShared(int execution) {
        if (elementData(new ArrayList<>()) != elementData(new ArrayList<>())) {
            failAssert();
        }
        if (elementData(new ArrayList<>(0)) != elementData(new ArrayList<>(0))) {
            failAssert();
        }
        return execution;
    }

    @Test
    public int test_emptyArraysDifferBetweenDefaultAndExplicit(int execution) {
        if (elementData(new ArrayList<>()) == elementData(new ArrayList<>(0))) {
            failAssert();
        }
        return execution;
    }

    @Test
    public int test_negativeCapacity(int execution) {
        for (int capacity : new int[] { -1, Integer.MIN_VALUE }) {
            try {
                new ArrayList<>(capacity);
                return -1;
            } catch (IllegalArgumentException success) {
            }
        }
        return execution;
    }
}
