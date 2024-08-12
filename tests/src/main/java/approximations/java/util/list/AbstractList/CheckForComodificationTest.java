package approximations.java.util.list.AbstractList;

import approximations.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Test
public class CheckForComodificationTest {
    private static final int LENGTH = 10;
    @Test
    public static int test_checkForComodification(int execution) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < LENGTH; i++)
            list.add(i);
        try {
            for (int i : list)
                if (i == LENGTH - 2)
                    list.remove(i);
        } catch (ConcurrentModificationException e) {
            return execution;
        }
        return -1;
    }
}
