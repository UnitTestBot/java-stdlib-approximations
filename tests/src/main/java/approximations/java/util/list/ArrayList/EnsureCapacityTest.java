package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.util.ArrayList;

@Test
public class EnsureCapacityTest {
    @Test
    public static int test_ensureCapacity(int execution) {
        testArrayList();
        return execution;
    }

    private static void testArrayList() {
        ArrayList<String> al = new ArrayList<>();
        al.add("abc");
        al.ensureCapacity(Integer.MIN_VALUE);
    }
}
