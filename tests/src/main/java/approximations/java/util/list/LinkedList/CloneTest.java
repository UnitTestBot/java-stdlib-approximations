package approximations.java.util.list.LinkedList;

import approximations.Test;

import java.util.LinkedList;

@Test
public class CloneTest {
    @Test
    public static int test_Clone(int execution) {
        LinkedList2 l = new LinkedList2();
        LinkedList2 lClone = (LinkedList2) l.clone();
        if (!(l.equals(lClone) && lClone.equals(l)))
            return -1;
        l.add("a");
        lClone = (LinkedList2) l.clone();
        if (!(l.equals(lClone) && lClone.equals(l)))
            return -1;
        l.add("b");
        lClone = (LinkedList2) l.clone();
        if (!(l.equals(lClone) && lClone.equals(l)))
            return -1;
        return execution;
    }

    private static class LinkedList2 extends LinkedList {}
}
