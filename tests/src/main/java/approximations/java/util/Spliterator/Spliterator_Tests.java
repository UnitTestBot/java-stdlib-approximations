package approximations.java.util.Spliterator;

import approximations.Test;
import java.util.Arrays;
import java.util.Spliterators;

@Test
public class Spliterator_Tests {
    @Test
    public int test_SpliteratorNPEs(int execution) {
        try {
            Arrays.spliterator((int[]) null, 0, 0);
            Arrays.spliterator((long[]) null, 0, 0);
            Arrays.spliterator((double[]) null, 0, 0);
            Arrays.spliterator((String[]) null, 0, 0);
        } catch (NullPointerException e) {
            return execution;
        }
        return -1;
    }

    @Test
    public int test_SpliteratorAIOBEs(int execution) {
        // origin > fence
        try {
            Arrays.spliterator(new int[]{}, 1, 0);
            Arrays.spliterator(new long[]{}, 1, 0);
            Arrays.spliterator(new double[]{}, 1, 0);
            Arrays.spliterator(new String[]{}, 1, 0);

            // bad origin
            Arrays.spliterator(new int[]{}, -1, 0);
            Arrays.spliterator(new long[]{}, -1, 0);
            Arrays.spliterator(new double[]{}, -1, 0);
            Arrays.spliterator(new String[]{}, -1, 0);

            // bad fence
            Arrays.spliterator(new int[]{}, 0, 1);
            Arrays.spliterator(new long[]{}, 0, 1);
            Arrays.spliterator(new double[]{}, 0, 1);
            Arrays.spliterator(new String[]{}, 0, 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return execution;
        }
        return -1;
    }


    @Test
    public int test_SpliteratorNPEsFromSpliterators(int execution) {
        try {
            Spliterators.spliterator((int[]) null, 0, 0, 0);
            Spliterators.spliterator((long[]) null, 0, 0, 0);
            Spliterators.spliterator((double[]) null, 0, 0, 0);
            Spliterators.spliterator((String[]) null, 0, 0, 0);
        } catch (NullPointerException e) {
            return execution;
        }
        return -1;
    }

    @Test
    public int test_SpliteratorAIOBEsFromSpliterators(int execution) {
        // origin > fence
        try {
            Spliterators.spliterator(new int[]{}, 1, 0, 0);
            Spliterators.spliterator(new long[]{}, 1, 0, 0);
            Spliterators.spliterator(new double[]{}, 1, 0, 0);
            Spliterators.spliterator(new String[]{}, 1, 0, 0);

            // bad origin
            Spliterators.spliterator(new int[]{}, -1, 0, 0);
            Spliterators.spliterator(new long[]{}, -1, 0, 0);
            Spliterators.spliterator(new double[]{}, -1, 0, 0);
            Spliterators.spliterator(new String[]{}, -1, 0, 0);

            // bad fence
            Spliterators.spliterator(new int[]{}, 0, 1, 0);
            Spliterators.spliterator(new long[]{}, 0, 1, 0);
            Spliterators.spliterator(new double[]{}, 0, 1, 0);
            Spliterators.spliterator(new String[]{}, 0, 1, 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return execution;
        }
        return -1;
    }
}
