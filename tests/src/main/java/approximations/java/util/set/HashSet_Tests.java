package approximations.java.util.set;

import approximations.Test;

import java.util.HashSet;
import java.util.Random;

@Test
public class HashSet_Tests {
    private static final int NUM_SETS = 1;
    private static final int MAX_CAPACITY = 2;

    //private static final Random rnd = new Random();

    /*private static HashSet<Integer> createHashSet() {

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < MAX_CAPACITY; i++) {
            hashSet.add(rnd.nextInt());
        }
        return hashSet;
    }*/

    @Test
    public static int test_SetClone1(int execution) {
        for (int i = 0; i < NUM_SETS; i++) {
            //HashSet<Integer> hashSet = createHashSet();
            HashSet<Integer> hashSet = new HashSet<>();
            Random rnd = new Random();
            for (int j = 0; j < MAX_CAPACITY; j++) {
                hashSet.add(rnd.nextInt());
            }

            HashSet<Integer> result = (HashSet<Integer>) hashSet.clone();

            if (!hashSet.equals(result)) {
                return -1;
            }
        }
        return execution;
    }

    @Test
    public static int test_SetClone2(int execution) {
        for (int i = 0; i < NUM_SETS; i++) {
            //HashSet<Integer> hashSet = createHashSet();
            HashSet<Double> hashSet = new HashSet<>();
            Random rnd = new Random();
            for (int j = 0; j < MAX_CAPACITY; j++) {
                hashSet.add(rnd.nextDouble());
            }

            HashSet<Double> result = (HashSet<Double>) hashSet.clone();

            if (!hashSet.equals(result)) {
                return -1;
            }
        }
        return execution;
    }
}
