package approximations.java.util.set;

import approximations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Test
public class LinkedHashSet_Tests {
    static final int numItr =  1;
    static final int setSize = 10;

    @Test
    // TODO: wrong size after union()
    public static int test_LinkedHashSet1(int execution) throws Exception {
        for (int i=0; i<numItr; i++) {
            Set<Integer> s1 = new LinkedHashSet<>();
            AddNumbers(s1, 1);

            Set<Integer> s2 = new LinkedHashSet<>();
            AddNumbers(s2, 6);

            try {
                Set<Integer> intersection = clone1(s1);
                intersection.retainAll(s2);
                Set<Integer> diff1 = clone1(s1);
                diff1.removeAll(s2);
                Set<Integer> diff2 = clone1(s2);
                diff2.removeAll(s1);
                Set<Integer> union = clone1(s1);
                union.addAll(s2);

                if (diff1.removeAll(diff2))
                    return -1;
                if (diff1.removeAll(intersection))
                    return -1;
                if (diff2.removeAll(diff1))
                    return -1;
                if (diff2.removeAll(intersection))
                    return -1;
                if (intersection.removeAll(diff1))
                    return -1;
                if (intersection.removeAll(diff1))
                    return -1;

                intersection.addAll(diff1);
                intersection.addAll(diff2);
                /*if (!intersection.equals(union))
                    return -1;*/
                if (intersection.size() != 15) {
                    return -1;
                }
                if (union.size() != 20) {
                    return -1;
                }

                /*Iterator e = union.iterator();
                while (e.hasNext())
                    if (!intersection.remove(e.next()))
                        return -1;
                if (!intersection.isEmpty())
                    return -1;

                e = union.iterator();
                while (e.hasNext()) {
                    Object o = e.next();
                    if (!union.contains(o))
                        return -1;
                    e.remove();
                    if (union.contains(o))
                        return -1;
                }
                if (!union.isEmpty())
                    return -1;

                s1.clear();
                if (!s1.isEmpty())
                    return -1;*/
            } catch (Exception e) {
                return -1;
            }
        }
        return execution;
    }

    @Test
    public static int test_LinkedHashSet2(int execution) throws Exception {
        for (int i=0; i<numItr; i++) {
            Set s1 = new LinkedHashSet();
            AddNumbers(s1, setSize);

            Set s2 = new LinkedHashSet();
            AddNumbers(s2, setSize);

            try {
                Set intersection = clone2(s1);
                /*intersection.retainAll(s2);
                Set diff1 = clone2(s1);
                diff1.removeAll(s2);
                Set diff2 = clone2(s2);
                diff2.removeAll(s1);
                Set union = clone2(s1);
                union.addAll(s2);

                if (diff1.removeAll(diff2))
                    return -1;
                if (diff1.removeAll(intersection))
                    return -1;
                if (diff2.removeAll(diff1))
                    return -1;
                if (diff2.removeAll(intersection))
                    return -1;
                if (intersection.removeAll(diff1))
                    return -1;
                if (intersection.removeAll(diff1))
                    return -1;

                intersection.addAll(diff1);
                intersection.addAll(diff2);
                if (!intersection.equals(union))
                    return -1;

                Iterator e = union.iterator();
                while (e.hasNext())
                    if (!intersection.remove(e.next()))
                        return -1;
                if (!intersection.isEmpty())
                    return -1;

                e = union.iterator();
                while (e.hasNext()) {
                    Object o = e.next();
                    if (!union.contains(o))
                        return -1;
                    e.remove();
                    if (union.contains(o))
                        return -1;
                }
                if (!union.isEmpty())
                    return -1;

                s1.clear();
                if (!s1.isEmpty())
                    return -1;*/
            } catch (Exception e) {
                return -1;
            }
        }
        return execution;
    }

    static Set<Integer> clone1(Set<Integer> s) throws Exception {
        Set<Integer> clone;
        clone = (Set<Integer>) ((LinkedHashSet<Integer>) s).clone();
        if (!s.equals(clone))
            throw new Exception("Set not equal to copy: ");
        if (!s.containsAll(clone))
            throw new Exception("Set does not contain copy.");
        if (!clone.containsAll(s))
            throw new Exception("Copy does not contain set.");
        return clone;
    }

    static Set<Integer> clone2(Set<Integer> s) throws Exception {
        Set clone;
        clone = new LinkedHashSet(Arrays.asList(s.toArray()));
        if (!s.equals(clone))
            throw new Exception("Set not equal to copy: ");
        if (!s.containsAll(clone))
            throw new Exception("Set does not contain copy.");
        if (!clone.containsAll(s))
            throw new Exception("Copy does not contain set.");
        return clone;
    }

    static void AddNumbers(Set s, int minElem) throws Exception {
        for (int i = 0; i < setSize; i++) {
            Integer e = minElem + i;

            int preSize = s.size();
            boolean prePresent = s.contains(e);
            boolean added = s.add(e);
            if (!s.contains(e))
                throw new Exception("Element not present after addition.");
            if (added == prePresent)
                throw new Exception("added == alreadyPresent");
            int postSize = s.size();
            if (added && preSize == postSize)
                throw new Exception("Add returned true, but size didn't change.");
            if (!added && preSize != postSize)
                throw new Exception("Add returned false, but size changed.");
        }
    }
}
