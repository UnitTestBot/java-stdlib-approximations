package approximations.java.util.list.LinkedList;

import approximations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Test
public class AddAllTest {
    @Test(disabled = true)
    // TODO: size of the copied list is 0
    public static int test_AddAll (int execution) {
        List head = Collections.nCopies(7, "deadly sin");
        List tail = Collections.nCopies(4, "basic food group");
        List l1 = new ArrayList(head);
        if (l1.size() == 0) {
            return -1;
        }
        List l2 = new LinkedList(head);
        if (l2.size() == 0) {
            return -1;
        }
        l1.addAll(tail);
        l2.addAll(tail);
        if (!l1.equals(l2))
            return -1;
        return execution;
    }
}
