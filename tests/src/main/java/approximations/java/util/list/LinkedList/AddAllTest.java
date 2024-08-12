package approximations.java.util.list.LinkedList;

import approximations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Test
public class AddAllTest {
    @Test
    public static int test_AddAll (int execution) {
        List head = Collections.nCopies(7, "deadly sin");
        List tail = Collections.nCopies(4, "basic food group");
        List l1 = new ArrayList(head);
        List l2 = new LinkedList(head);
        l1.addAll(tail);
        l2.addAll(tail);
        if (!l1.equals(l2))
            return -1;
        return execution;
    }
}
