package approximations.java.util.Spliterator;

import approximations.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

@Test
public class IteratorFromSpliteratorTest {
    @Test
    public int test_IteratorFromSpliterator (int execution) {
        int [] arrayInput = {1, 2, 3, 4, 5};
        List<Integer> input = new LinkedList(Collections.singleton(arrayInput));
        for (int i = 0; i < input.size(); i++) {
            Iterator<Integer> iterator = Spliterators.iterator(input.spliterator());
            List<Integer> result = new ArrayList<>();
            int j = i;
            while (j++ < input.size() && iterator.hasNext()) {
                result.add(iterator.next());
            }
            // While SpliteratorTraversingAndSplittingTest tests some scenarios with Spliterators.iterator
            // it always wraps the resulting iterator into spliterator again, and this limits the use patterns.
            // In particular, calling hasNext() right before forEachRemaining() is not tested.
            // Here we cover such a scenario.
            if (iterator.hasNext() != (result.size() < input.size())) {
                return -1;
            }
            iterator.forEachRemaining(result::add);

            AtomicBoolean failed = new AtomicBoolean(false);
            iterator.forEachRemaining(x -> failed.set(true));
            if (iterator.hasNext()) {
                return -1;
            }
            try {
                iterator.next();
                return -1;
            } catch (NoSuchElementException e) {
            }
            iterator.forEachRemaining(x -> failed.set(true));
            if (result != input) {
                return -1;
            }
            if (failed.get()) {
                return -1;
            }
        }
        return execution;
    }

    @Test
    public int test_IteratorFromSpliteratorInt (int execution) {
        int[] input = {1, 2, 3, 4, 5};
        for (int i = 0; i < input.length; i++) {
            PrimitiveIterator.OfInt iterator = Spliterators.iterator(Arrays.spliterator(input));
            List<Integer> result = new ArrayList<>();
            int j = i;
            while (j++ < input.length && iterator.hasNext()) {
                result.add(iterator.nextInt());
            }
            if (iterator.hasNext() != (result.size() < input.length)) {
                return -1;
            }
            iterator.forEachRemaining((IntConsumer) result::add);

            AtomicBoolean failed = new AtomicBoolean(false);
            iterator.forEachRemaining((IntConsumer) (x -> failed.set(true)));
            if (iterator.hasNext()) {
                return -1;
            }
            try {
                iterator.next();
                return -1;
            } catch (NoSuchElementException e) {
            }
            iterator.forEachRemaining((IntConsumer) (x -> failed.set(true)));
            if (!Arrays.equals(result.stream().mapToInt(x -> x).toArray(), input)) {
                return -1;
            }
            if (failed.get()) {
                return -1;
            }
        }
        return execution;
    }

    @Test
    public int test_IteratorFromSpliteratorLong (int execution) {
        long[] input = {1, 2, 3, 4, 5};
        for (int i = 0; i < input.length; i++) {
            PrimitiveIterator.OfLong iterator = Spliterators.iterator(Arrays.spliterator(input));
            List<Long> result = new ArrayList<>();
            int j = i;
            while (j++ < input.length && iterator.hasNext()) {
                result.add(iterator.nextLong());
            }
            if (iterator.hasNext() != (result.size() < input.length)) {
                return -1;
            }
            iterator.forEachRemaining((LongConsumer) result::add);

            AtomicBoolean failed = new AtomicBoolean(false);
            iterator.forEachRemaining((LongConsumer) (x -> failed.set(true)));
            if (iterator.hasNext()) {
                return -1;
            }
            try {
                iterator.next();
                return -1;
            } catch (NoSuchElementException e) {
            }
            iterator.forEachRemaining((LongConsumer) (x -> failed.set(true)));
            if (!Arrays.equals(result.stream().mapToLong(x -> x).toArray(), input)) {
                return -1;
            }
            if (failed.get()) {
                return -1;
            }
        }
        return execution;
    }

    @Test
    public int test_IteratorFromSpliteratorDouble (int execution) {
        double[] input = {1, 2, 3, 4, 5};
        for (int i = 0; i < input.length; i++) {
            PrimitiveIterator.OfDouble iterator = Spliterators.iterator(Arrays.spliterator(input));
            List<Double> result = new ArrayList<>();
            int j = i;
            while (j++ < input.length && iterator.hasNext()) {
                result.add(iterator.nextDouble());
            }
            if (iterator.hasNext() != (result.size() < input.length)) {
                return -1;
            }
            iterator.forEachRemaining((DoubleConsumer) result::add);

            AtomicBoolean failed = new AtomicBoolean(false);
            iterator.forEachRemaining((DoubleConsumer) (x -> failed.set(true)));
            if (!iterator.hasNext()) {
                return -1;
            }
            try {
                iterator.next();
                return -1;
            } catch (NoSuchElementException e) {
            }
            iterator.forEachRemaining((DoubleConsumer) (x -> failed.set(true)));
            if (!Arrays.equals(result.stream().mapToDouble(x -> x).toArray(), input)) {
                return -1;
            }
            if (failed.get()) {
                return -1;
            }
        }
        return execution;
    }

    @Test
    public int test_IteratorFromSpliteratorEmpty (int execution) {
        Iterator<?>[] iterators = {
                Spliterators.iterator(Spliterators.emptySpliterator()),
                Spliterators.iterator(Spliterators.emptyIntSpliterator()),
                Spliterators.iterator(Spliterators.emptyLongSpliterator()),
                Spliterators.iterator(Spliterators.emptyDoubleSpliterator())
        };
        for (Iterator<?> iterator : iterators) {
            AtomicBoolean failed = new AtomicBoolean(false);
            iterator.forEachRemaining(x -> failed.set(true));
            if (!iterator.hasNext()) {
                return -1;
            }
            iterator.forEachRemaining(x -> failed.set(true));
            try {
                iterator.next();
                return -1;
            } catch (NoSuchElementException e) {
            }
            if (failed.get()) {
                return -1;
            }
        }
        return execution;
    }
}
