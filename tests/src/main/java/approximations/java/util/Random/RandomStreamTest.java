package approximations.java.util.Random;

import approximations.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Test
public class RandomStreamTest {
    private static final int SIZE = 1000;

    public Object[][] getRandomSuppliers() {
        return new Object[][] {
                {new Random(), SIZE},
                {new SecureRandom(), SIZE}
        };
    }
    Object[][] randomSuppliers = getRandomSuppliers();

    @Test(executionMax = 1)
    public int test_RandomIntStream (int execution) {
        Random random = (Random) randomSuppliers[execution][0];
        int count = (int) randomSuppliers[execution][1];
        final List<Integer> destination = new ArrayList<>(count);
        random.ints().limit(count).forEach(destination::add);
        if (destination.size() != count) {
            return -1;
        } else {
            return execution;
        }
    }

    @Test(executionMax = 1)
    public int test_RandomLongStream (int execution) {
        Random random = (Random) randomSuppliers[execution][0];
        int count = (int) randomSuppliers[execution][1];
        final List<Long> destination = new ArrayList<>(count);
        random.longs().limit(count).forEach(destination::add);
        if (destination.size() != count) {
            return -1;
        } else {
            return execution;
        }
    }

    @Test(executionMax = 1)
    public int test_RandomDoubleStream (int execution) {
        Random random = (Random) randomSuppliers[execution][0];
        int count = (int) randomSuppliers[execution][1];
        final List<Double> destination = new ArrayList<>(count);
        random.doubles().limit(count).forEach(destination::add);

        AtomicBoolean failed = new AtomicBoolean(false);
        random.doubles().limit(count).forEach(d -> failed.set(!(d >= 0.0 && d < 1.0) && !failed.get()));

        if (destination.size() != count || failed.get()) {
            return -1;
        } else {
            return execution;
        }
    }

    @Test
    public int test_IntStream (int execution) {
        final long seed = System.currentTimeMillis();
        final Random r1 = new Random(seed);
        final int[] a = new int[SIZE];
        for (int i=0; i < SIZE; i++) {
            a[i] = r1.nextInt();
        }

        final Random r2 = new Random(seed); // same seed
        final int[] b = r2.ints().limit(SIZE).toArray();
        if (a != b) {
            return -1;
        } else {
            return execution;
        }
    }

    @Test
    public int test_LongStream (int execution) {
        final long seed = System.currentTimeMillis();
        final Random r1 = new Random(seed);
        final long[] a = new long[SIZE];
        for (int i=0; i < SIZE; i++) {
            a[i] = r1.nextLong();
        }

        final Random r2 = new Random(seed); // same seed
        final long[] b = r2.longs().limit(SIZE).toArray();
        if (a != b) {
            return -1;
        } else {
            return execution;
        }
    }

    @Test
    public int test_DoubleStream (int execution) {
        final long seed = System.currentTimeMillis();
        final Random r1 = new Random(seed);
        final double[] a = new double[SIZE];
        for (int i=0; i < SIZE; i++) {
            a[i] = r1.nextDouble();
        }

        final Random r2 = new Random(seed); // same seed
        final double[] b = r2.doubles().limit(SIZE).toArray();
        if (a != b) {
            return -1;
        } else {
            return execution;
        }
    }

    @Test
    public int test_ThreadLocalIntStream (int execution) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return testRandomResultSupplierConcurrently(() -> tlr.ints().limit(SIZE).boxed().collect(toList()), execution);
    }

    @Test
    public int test_ThreadLocalLongStream (int execution) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return testRandomResultSupplierConcurrently(() -> tlr.longs().limit(SIZE).boxed().collect(toList()), execution);
    }

    @Test
    public int test_ThreadLocalDoubleStream (int execution) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return testRandomResultSupplierConcurrently(() -> tlr.doubles().limit(SIZE).boxed().collect(toList()), execution);
    }

    <T> int testRandomResultSupplierConcurrently(Supplier<T> s, int execution) throws InterruptedException, ExecutionException, TimeoutException {
        // Produce 10 completable future tasks
        final int tasks = 10;
        List<CompletableFuture<T>> cfs = Stream.generate(() -> CompletableFuture.supplyAsync(s)).
                limit(tasks).collect(toList());

        // Wait for all tasks to complete
        // Timeout is beyond reasonable doubt that completion should
        // have occurred unless there is an issue
        CompletableFuture<Void> all = CompletableFuture.allOf(cfs.stream().toArray(CompletableFuture[]::new));
        all.get(1, TimeUnit.MINUTES);

        // Count the distinct results, which should equal the number of tasks
        long rc = cfs.stream().map(CompletableFuture::join).distinct().count();
        if (rc != tasks) {
            return -1;
        } else {
            return execution;
        }
    }
}
