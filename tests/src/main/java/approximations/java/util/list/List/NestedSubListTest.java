package approximations.java.util.list.List;

import approximations.Test;

import java.util.*;

@Test
public class NestedSubListTest {
    static final int NEST_LIMIT = 65536;

    @Test(executionMax = 13)
    public int test_AccessToSublists (int execution) {
        List<Integer> list = lists.get(execution);
        boolean modifiable;
        if (execution < 6) {
            modifiable = true;
        } else {
            modifiable = false;
        }
        Class<?> cls = list.getClass();
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
        List<Integer> c = Arrays.asList(42);
        List<List<Integer>> lists = new LinkedList();
        lists.add(c);
        lists.add(new ArrayList<>(c));
        lists.add(new LinkedList<>(c));
        lists.add(Collections.checkedList(new ArrayList<>(c), Integer.class));
        lists.add(Collections.checkedList(new LinkedList<>(c), Integer.class));
        lists.add(Collections.synchronizedList(new ArrayList<>(c)));
        lists.add(Collections.synchronizedList(new LinkedList<>(c)));
        lists.add(new MyList());
        lists.add(Collections.singletonList(42));
        lists.add(Collections.checkedList(c, Integer.class));
        lists.add(Collections.synchronizedList(c));
        lists.add(Collections.unmodifiableList(c));
        lists.add(Collections.unmodifiableList(new ArrayList<>(c)));
        lists.add(Collections.unmodifiableList(new LinkedList<>(c)));
        return lists;
    }

    List<List<Integer>> lists = getLists();

    static class MyList extends AbstractList<Integer> {
        public Integer get(int index) { return 42; }
        public int size() { return 1; }
    }
}
