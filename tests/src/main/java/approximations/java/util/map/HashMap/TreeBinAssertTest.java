package approximations.java.util.map.HashMap;

import approximations.Test;

import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import java.util.LinkedHashSet;

@Test
public class TreeBinAssertTest {
    private static final int ITR_RM = -1;
    private static final int BIN352442_SIZE = 524288;
    private static final int BIN552165_SIZE = 1048576;

    static int[][] sizeAndHashes = {
                    new int[] {
                            2020958394,
                            631595194,
                            1984782522,
                            419782842,
                            285565114,
                            1432182970,
                            841310394,
                            320692410,
                            303390906,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            519397562,
                            ITR_RM,
                            626352314,
                            101540026
                    },
                    new int[] {
                            790129893,
                            1214803173,
                            1212706021,
                            608726245,
                            2073586917,
                            1433955557,
                            692612325,
                            370699493,
                            2061004005,
                            48786661,
                            ITR_RM,
                            ITR_RM,
                            1418226917,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            ITR_RM,
                            1487432933,
                            ITR_RM,
                            ITR_RM,
                            1880648933,
                            338193637
                    }
    };

    @Test(executionMax = 1)
    public static int test_Map(int execution) {
        if (!HashMap.class.desiredAssertionStatus()) {
            return execution;
        }

        int size;
        int[] hashes;
        if (execution == 0) {
            size = BIN352442_SIZE;
            hashes = sizeAndHashes[0];
        } else {
            size = BIN552165_SIZE;
            hashes = sizeAndHashes[1];
        }

        Map<Key,Integer> map = new HashMap<>(size);

        try {
            Iterator<Key> itr = null;
            for (int h : hashes) {
                if (h == ITR_RM) {
                    if (itr == null) {
                        itr = map.keySet().iterator();
                    }
                    itr.next();
                    itr.remove();
                } else {
                    itr = null;
                    map.put(new Key(h), 0);
                }
            }
        } catch (Throwable t) {
            return -1;
        }

        return execution;
    }

    @Test(executionMax = 1)
    public static int test_Set(int execution) {
        if (!HashMap.class.desiredAssertionStatus()) {
            return execution;
        }

        int size;
        int[] hashes;
        if (execution == 0) {
            size = BIN352442_SIZE;
            hashes = sizeAndHashes[0];
        } else {
            size = BIN552165_SIZE;
            hashes = sizeAndHashes[1];
        }

        Set<Key> set = new LinkedHashSet<>(size);

        try {
            Iterator<Key> itr = null;
            for (int h : hashes) {
                if (h == ITR_RM) {
                    if (itr == null) {
                        itr = set.iterator();
                    }
                    itr.next();
                    itr.remove();
                } else {
                    itr = null;
                    set.add(new Key(h));
                }
            }
        } catch (Throwable t) {
            return -1;
        }
        return execution;
    }

    static class Key implements Comparable<Key> {
        final int hash;

        public Key(int desiredHash) {
            this.hash = desiredHash ^ (desiredHash >>> 16);
        }

        @Override public int hashCode() { return this.hash; }

        @Override public boolean equals(Object o) {
            return o.hashCode() == this.hashCode();
        }

        @Override public int compareTo(Key k) {
            return Integer.compare(this.hash, k.hash);
        }
    }
}
