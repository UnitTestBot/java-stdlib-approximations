package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.util.ArrayList;

public class Bug8146568Test { // i'm not sure if a test to get an OutOfMemoryError is relevant here
    /*@Test
    public static int test_bug8146568(int execution) {
        int size = Integer.MAX_VALUE - 2;
        ArrayList<Object> huge = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            huge.add(null);
        try {
            huge.addAll(huge);
            return -1;
        } catch (OutOfMemoryError success) {}
        return execution;
    }*/
}
