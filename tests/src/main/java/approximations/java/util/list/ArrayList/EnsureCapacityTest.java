package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.util.ArrayList;
import java.util.Vector;

@Test
public class EnsureCapacityTest {
    @Test
    public static int test_ensureCapacity(int execution) {
        testArrayList();
        //testVector();
        return execution;
    }

    private static void checkCapacity(int before, int after) {
        if (before != after) {
            throw new RuntimeException("capacity is expected to be unchanged: " +
                    "before=" + before + " after=" + after);
        }
    }

    private static void testArrayList() {
        ArrayList<String> al = new ArrayList<>();
        al.add("abc");
        al.ensureCapacity(Integer.MIN_VALUE);

        // there is no method to query the capacity of ArrayList
        // so before and after capacity are not checked
    }

    private static void testVector() {
        Vector<String> vector = new Vector<>();
        vector.add("abc");

        int cap = vector.capacity();
        vector.ensureCapacity(Integer.MIN_VALUE);
        checkCapacity(cap, vector.capacity());
    }
}
