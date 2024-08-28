package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.lang.reflect.Field;
import java.util.*;

@Test
public class ArrayManagementTest {
    @Test
    public int test_negativeCapacity(int execution) {
        try {
            for (int capacity : new int[]{-1, Integer.MIN_VALUE}) {
                try {
                    new ArrayList<>(capacity);
                    return -1;
                } catch (IllegalArgumentException success) {
                }
            }
        } catch (Throwable t) {
            return -1;
        }
        return execution;
    }
}
