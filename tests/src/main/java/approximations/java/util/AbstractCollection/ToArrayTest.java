package approximations.java.util.AbstractCollection;

import approximations.Test;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;

@Test
public class ToArrayTest {
    static class TestCollection<E> extends AbstractCollection<E> {
        private final E[] elements;
        private int[] sizes;
        private int nextSize;

        public TestCollection(E[] elements) {
            this.elements = elements;
            setSizeSequence(new int[] { elements.length });
        }

        /*
         * Sets the values that size() will return on each use. The next
         * call to size will return sizes[0], then sizes[1] etc. This allows us
         * to emulate a concurrent change to the contents of the collection
         * without having to perform concurrent changes. If sizes[n+1] contains
         * a larger value, the collection will appear to have shrunk when
         * iterated; if a smaller value then the collection will appear to have
         * grown when iterated.
         */
        void setSizeSequence(int... sizes) {
            this.sizes = sizes;
            nextSize = 0;
        }

        /* can change collection's size after each invocation */
        @Override
        public int size() {
            return sizes[nextSize == sizes.length - 1 ? nextSize : nextSize++];
        }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {
                int pos = 0;

                public boolean hasNext() {
                    return pos < sizes[nextSize];
                }
                public E next() {
                    return elements[pos++];
                }
                public void remove() {
                    throw new UnsupportedOperationException(
                            "Not supported yet.");
                }
            };
        }
    }

    static final Object[] OBJECTS = { new Object(), new Object(), new Object() };
    static final TestCollection<?> CANDIDATE = new TestCollection<>(OBJECTS);
    static final int CAP = OBJECTS.length; // capacity of the CANDIDATE
    static final int LAST = CAP - 1; // last possible array index
    Object[] a;
    Object[] res;

    int last() {
        return a.length - 1;
    }

    protected void test() throws Throwable {
        // Check array type conversion
        res = new TestCollection<>(new Object[] { "1", "2" }).toArray(new String[0]);
        check(res instanceof String[]);
        check(res.length == 2);
        check(res[1] == "2");

        // Check incompatible type of target array
        try {
            res = CANDIDATE.toArray(new String[CAP]);
            check(false);
        } catch (Throwable t) {
            check(t instanceof ArrayStoreException);
        }

        // Check more elements than a.length
        a = new Object[CAP - 1]; // appears too small
        res = CANDIDATE.toArray(a);
        check(res != a);
        check(res[LAST] != null);

        // Check equal elements as a.length
        a = new Object[CAP]; // appears to match
        res = CANDIDATE.toArray(a);
        check(res == a);
        check(res[last()] != null);

        // Check equal elements as a.length
        a = new Object[CAP + 1]; // appears too big
        res = CANDIDATE.toArray(a);
        check(res == a);
        check(res[last()] == null);

        // Check less elements than expected, but more than a.length
        a = new Object[CAP - 2]; // appears too small
        CANDIDATE.setSizeSequence(CAP, CAP - 1);
        res = CANDIDATE.toArray(a);
        check(res != a);
        check(res.length == CAP - 1);
        check(res[LAST - 1] != null);

        // Check less elements than expected, but equal as a.length
        a = Arrays.copyOf(OBJECTS, CAP); // appears to match
        CANDIDATE.setSizeSequence(CAP, CAP - 1);
        res = CANDIDATE.toArray(a);
        check(res == a);
        check(res[last()] == null);

        // Check more elements than expected and more than a.length
        a = new Object[CAP - 1]; // appears to match
        CANDIDATE.setSizeSequence(CAP - 1, CAP);
        res = CANDIDATE.toArray(a);
        check(res != a);
        check(res[LAST] != null);

        // Check more elements than expected, but equal as a.length
        a = new Object[CAP - 1]; // appears to match
        CANDIDATE.setSizeSequence(CAP - 2, CAP - 1);
        res = CANDIDATE.toArray(a);
        check(res == a);
        check(res[last()] != null);

        // Check more elements than expected, but less than a.length
        a = Arrays.copyOf(OBJECTS, CAP); // appears to match
        CANDIDATE.setSizeSequence(CAP - 2, CAP - 1);
        res = CANDIDATE.toArray(a);
        check(res == a);
        check(res[last()] == null);

        test_7121314();
    }

    /*
     * Major target of this testcase, bug 7121314.
     */
    protected void test_7121314() {
        // Check equal elements as a.length, but less than expected
        a = new Object[CAP - 1]; // appears too small
        CANDIDATE.setSizeSequence(CAP, CAP - 1);
        res = CANDIDATE.toArray(a);
        check(res == a);
        check(res[last()] != null);

        // Check less elements than a.length and less than expected
        a = Arrays.copyOf(OBJECTS, CAP - 1); // appears too small
        CANDIDATE.setSizeSequence(CAP, CAP - 2);
        res = CANDIDATE.toArray(a);
        check(res == a);
        check(res[last()] == null);

    }

    @Test(executionMax = 0)
    public static int test_toArray(int execution) {
        ToArrayTest testcase = new ToArrayTest();
        try {
            testcase.test();
        } catch (Throwable t) {
            unexpected();
        }
        if (failed > 0) {
            return -1;
        } else {
            return execution;
        }
    }

    //--------------------- Infrastructure ---------------------------
    static volatile int passed = 0, failed = 0;
    static void pass() { passed++; }
    static void fail() { failed++; }
    static void unexpected() { failed++; }
    static void check(boolean cond) { if (cond) pass(); else fail(); }
    static void equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) pass();
        else { fail(); }
    }
}
