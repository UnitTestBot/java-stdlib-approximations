package approximations.java.util.Random;

import approximations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Test
public class DistinctSeedsTest {
    @Test
    public static int test_DistinctSeeds (int execution) throws InterruptedException {
        // Strictly speaking, it is possible for these to randomly fail,
        // but the probability should be small (approximately 2**-48).
        if (new Random().nextLong() == new Random().nextLong() ||
                new Random().nextLong() == new Random().nextLong())
            throw new RuntimeException("Random() seeds not unique.");

        // Now try generating seeds concurrently
        class RandomCollector implements Runnable {
            long[] randoms = new long[1<<17];
            public void run() {
                for (int i = 0; i < randoms.length; i++)
                    randoms[i] = new Random().nextLong();
            }
        }
        final int threadCount = 2;
        List<RandomCollector> collectors = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            RandomCollector r = new RandomCollector();
            collectors.add(r);
            threads.add(new Thread(r));
        }
        for (Thread thread : threads)
            thread.start();
        for (Thread thread : threads)
            thread.join();
        int collisions = 0;
        HashSet<Long> s = new HashSet<>();
        for (RandomCollector r : collectors) {
            for (long x : r.randoms) {
                if (s.contains(x))
                    collisions++;
                s.add(x);
            }
        }
        if (collisions > 10) {
            return -1;
        }
        return execution;
    }
}
