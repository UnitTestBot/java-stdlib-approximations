package approximations.java.util.list.List;

import approximations.Test;

import java.util.*;

@Test
public class NestedSubListTest {
    static final int NEST_LIMIT = 5;

    @Test(executionMax = 2)
    public static int test_AccessToSublists(int execution) {
        List<Integer> list;
        boolean modifiable = true;
        if (execution == 0) {
            list = lists.get(0);
        } else if (execution == 1) {
            list = lists.get(1);
        } else if (execution == 2) {
            list = lists.get(2);
            modifiable = false;
        } else {
            return execution;
        }
        for (int i = 0; i < NEST_LIMIT; ++i) {
            list = list.subList(0, 1);
        }

        try {
            list.get(0);
            if (modifiable) {
                list.remove(0);
                list.add(0, 42);
            }
        } catch (StackOverflowError e) {
            return -1;
        }
        return execution;
    }

    public static List<List<Integer>> getLists() {
        List<Integer> c = new ArrayList<>();
        c.add(42);
        List<List<Integer>> lists = new LinkedList();
        lists.add(new ArrayList<>(c));
        lists.add(new LinkedList<>(c));
        lists.add(new MyList());
        return lists;
    }
    static final List<List<Integer>> lists = getLists();

    static class MyList extends AbstractList<Integer> {
        public Integer get(int index) { return 42; }
        public int size() { return 1; }
    }
}
