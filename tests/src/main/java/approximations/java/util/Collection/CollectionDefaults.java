package approximations.java.util.Collection;

import approximations.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Test
public class CollectionDefaults {
    public static final Predicate<Integer> pEven = x -> 0 == x % 2;
    public static final Predicate<Integer> pOdd = x -> 1 == x % 2;

    private static final int SIZE = 5;

    private static Function<Collection<Integer>, Collection<Integer>> getSupplier(int execution) {
            // Collection
            //ExtendsAbstractCollection<Integer>::new,
            // Lists
            //ArrayList::new,
            //LinkedList::new,
            //ExtendsAbstractList<Integer>::new,

            // Sets
            //HashSet::new,
            //LinkedHashSet::new
            //ExtendsAbstractSet<Integer>::new
        if (execution == 0) {
            return ArrayList::new;
        } else if (execution == 1) {
            return LinkedList::new;
        } else if (execution == 2) {
            return HashSet::new;
        } else {
            return LinkedHashSet::new;
        }
    }

    public static Set<Integer> setCases(int execution) {
        if (execution == 0) {
            return new HashSet<>();
        } else if (execution == 1) {
            return new LinkedHashSet<>();
        } else if (execution == 2) {
            return new HashSet<Integer>(){{add(42);}};
        } else {
            return new LinkedHashSet<Integer>(){{add(42);}};
        }
    }

    @Test(executionMax = 3)
    public static int test_ProvidedWithNull(int execution) {
        if (execution < 0 || execution > 3) {
            return execution;
        }
        Set<Integer> set = setCases(execution);
        try {
            set.forEach(null);
            return -1;
        } catch (NullPointerException expected) { // expected
        }
        try {
            set.removeIf(null);
            return -1;
        } catch (NullPointerException expected) { // expected
        }
        return execution;
    }

    public static List<List<Collection<Integer>>> prepareTests(Function<Collection<Integer>, Collection<Integer>> supplier) {
        List<List<Collection<Integer>>> tests = new ArrayList<>();
        List<Collection<Integer>> test1 = new ArrayList<>();
        test1.add(supplier.apply(new ArrayList<>()));
        test1.add(supplier.apply(new ArrayList<>()));
        tests.add(test1);
        //Collection<Integer> test1 = supplier.apply(new ArrayList<>());
        //Collection<Integer> test2 = supplier.apply(new ArrayList<>());
        //tests.add(new Collection[] { supplier.apply(new ArrayList<>()), supplier.apply(new ArrayList<>()) });
        //tests.add(new Collection[] { test1, test2 });

        /*List<Integer> test21 = new ArrayList<>(), test22 = new ArrayList<>();
        test21.add(42); test22.add(42);
        tests.add(new Collection[] { supplier.apply(test21), supplier.apply(test22) });

        List<Integer> test31 = new LinkedList<>(), test32 = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            test31.add(i); test32.add(i);
        }
        tests.add(new Collection[] { supplier.apply(test31), supplier.apply(test32) });

        List<Integer> test41 = new LinkedList<>(), test42 = new LinkedList<>();
        for (int i = SIZE; i >= 0; i--) {
            test41.add(i); test42.add(i);
        }
        tests.add(new Collection[] { supplier.apply(test41), supplier.apply(test42) });

        List<Integer> test51 = new ArrayList<>(), test52 = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            test51.add((i * 2) + 1); test52.add((i * 2) + 1);
        }
        tests.add(new Collection[] { supplier.apply(test51), supplier.apply(test52) });

        List<Integer> test61 = new LinkedList<>(), test62 = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            test61.add(i * 2); test62.add(i * 2);
        }
        tests.add(new Collection[] { supplier.apply(test61), supplier.apply(test62) });

        List<Integer> test71 = new LinkedList<>(), test72 = new ArrayList<>();
        test71.add(42); test72.add(42);
        test71.remove(0); test72.remove(0);
        tests.add(new Collection[] { supplier.apply(test71), supplier.apply(test72) });

        List<Integer> test81 = new LinkedList<>(), test82 = new ArrayList<>();
        test81.add(42); test82.add(42);
        test81.add(43); test82.add(43);
        test81.remove(1); test82.remove(1);
        tests.add(new Collection[] { supplier.apply(test81), supplier.apply(test82) });

        List<Integer> test91 = new ArrayList<>(), test92 = new LinkedList<>();
        for (int i = 0; i < 2 * SIZE; i++) {
            test91.add(i); test92.add(i);
        }
        test91.removeIf(x -> x < SIZE); test92.removeIf(x -> x < SIZE);
        tests.add(new Collection[] { supplier.apply(test91), supplier.apply(test92) });*/

        return tests;
    }

    @Test(executionMax = 3)
    public static int test_ForEach(int execution) {
        if (execution < 0 || execution > 3) {
            return execution;
        }
        final Function<Collection<Integer>, Collection<Integer>> supplier = getSupplier(execution);
        List<List<Collection<Integer>>> tests = prepareTests(supplier);
        for (final List<Collection<Integer>> test : tests) {
            final Collection<Integer> original = test.get(0); //test.expected;
            final Collection<Integer> set = test.get(1); // test.collection;

            try {
                set.forEach(null);
                return -1;
            } catch (NullPointerException expected) { // expected
            }
            /*if (set instanceof Set && !((set instanceof SortedSet) || (set instanceof LinkedHashSet))) {
                CollectionAsserts.assertContentsUnordered(set, original, test.toString());
            } else {
                CollectionAsserts.assertContents(set, original, test.toString());
            }*/
            if (!original.equals(set) || !set.equals(original)) {
                return -1;
            }
        }
        return execution;
    }

    /*@Test(executionMax = 3)
    public static int test_RemoveIf(int execution) {
        if (execution < 0 || execution > 3) {
            return execution;
        }
        final Function<Collection<Integer>, Collection<Integer>> supplier = getSupplier(execution);
        List<Collection<Integer>[]> tests = prepareTests(supplier);
        for (final Collection<Integer>[] test : tests) {
            final Collection<Integer> original = test[0]; //test.expected;
            final Collection<Integer> set = test[1]; // test.collection;

            try {
                set.removeIf(null);
                return -1;
            } catch (NullPointerException expected) { // expected
            }
            /*if (set instanceof Set && !((set instanceof SortedSet) || (set instanceof LinkedHashSet))) {
                CollectionAsserts.assertContentsUnordered(set, original, test.toString());
            } else {
                CollectionAsserts.assertContents(set, original, test.toString());
            }*/
            /*if (!original.equals(set) || !set.equals(original)) {
                return -1;
            }

            set.removeIf(pEven);
            for (int i : set) {
                if (i % 2 == 0) {
                    return -1;
                }
            }
            for (int i : original) {
                if (i % 2 == 1) {
                    if (!set.contains(i)) {
                        return -1;
                    }
                }
            }
            set.removeIf(pOdd);
            if (!set.isEmpty()) {
                return -1;
            }
        }
        return execution;
    }*/
}
