package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Test
public class SubListModCoundTest {
    @Test
    public static int test_subListModCound(int execution) {
        ArrayList<Integer> root = new ArrayList<>();
        java.util.List<Integer> subList = root.subList(0, 0);
        root.add(42);
        try {
            subList.size();
            return -1;
        } catch (ConcurrentModificationException expected) {
        }
        List<Integer> subSubList = subList.subList(0, 0);
        try {
            subSubList.size();
            return -1;
        } catch (ConcurrentModificationException expected) {
        }
        try {
            subSubList.add(42);
            return -1;
        } catch (ConcurrentModificationException expected) {
        }
        try {
            subList.size();
            return -1;
        } catch (ConcurrentModificationException expected) {
        }
        return execution;
    }
}
