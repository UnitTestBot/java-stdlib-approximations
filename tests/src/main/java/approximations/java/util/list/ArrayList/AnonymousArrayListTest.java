package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Test
public class AnonymousArrayListTest {
    @Test(disabled = true)
    // TODO: implement anonymous classes
    public int test_anonymousArrayList(int execution) {
        final List<Integer> superstitious = new ArrayList<Integer>() {
            public void add(int index, Integer i) {
                if (i == 0) throw new Error("unlucky");
                else super.add(index, i); }};
        final ListIterator it = superstitious.listIterator(0);
        try {
            it.add(123);
            return -1;
        } catch (Error e) {
        }
        it.add(42);
        return execution;
    }
}
