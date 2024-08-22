package approximations.java.util.set;

import approximations.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Test
public class LinkedHashSet_Tests {
    static Random rnd = new Random(666);

    @Test
    public static int test_LinkedHashSet (int execution) throws Exception {
        int numItr =  20;
        int setSize = 20;

        for (int i=0; i<numItr; i++) {
            Set s1 = new LinkedHashSet();
            AddRandoms(s1, setSize);

            Set s2 = new LinkedHashSet();
            AddRandoms(s2, setSize);

            try {
                Set intersection = clone(s1);
                intersection.retainAll(s2);
                Set diff1 = clone(s1);
                diff1.removeAll(s2);
                Set diff2 = clone(s2);
                diff2.removeAll(s1);
                Set union = clone(s1);
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

                if (new LinkedHashSet(union).hashCode() != union.hashCode())
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
                    return -1;
            } catch (Exception e) {
                return -1;
            }
        }
        return execution;
    }

    static Set clone(Set s) throws Exception {
        Set clone;
        int method = rnd.nextInt(3);
        clone = (method == 0 ? (Set) ((LinkedHashSet) s).clone() :
                (method == 1 ? new LinkedHashSet(Arrays.asList(s.toArray())) :
                        serClone(s)));
        if (!s.equals(clone))
            throw new Exception("Set not equal to copy: "+method);
        if (!s.containsAll(clone))
            throw new Exception("Set does not contain copy.");
        if (!clone.containsAll(s))
            throw new Exception("Copy does not contain set.");
        return clone;
    }

    private static Set serClone(Set m) throws IOException, ClassNotFoundException {
        Set result = null;
        // Serialize
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(m);
        out.flush();

        // Deserialize
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        out.close();
        ObjectInputStream in = new ObjectInputStream(bis);
        result = (Set)in.readObject();
        in.close();
        return result;
    }

    static void AddRandoms(Set s, int n) throws Exception {
        for (int i = 0; i < n; i++) {
            Integer e = rnd.nextInt(n);

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
