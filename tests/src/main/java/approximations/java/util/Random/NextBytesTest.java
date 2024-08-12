package approximations.java.util.Random;

import approximations.Test;

import java.util.Arrays;
import java.util.Random;

@Test
public class NextBytesTest {
    private static void realMain() {
        byte[] expected = new byte[]
                {27, -105, -24, 83, -77, -29, 119, -74, -106, 68, 54};
        Random r = new java.util.Random(2398579034L);
        for (int i = 0; i <= expected.length; i++) {
            r.setSeed(2398579034L);
            byte[] actual = new byte[i];
            r.nextBytes(actual);
            //System.out.println(Arrays.toString(actual));
            check(Arrays.equals(actual, Arrays.copyOf(expected,i)));
        }
    }

    //--------------------- Infrastructure ---------------------------
    static volatile int passed = 0, failed = 0;
    static void pass() {passed++;}
    static void fail() {failed++; Thread.dumpStack();}
    static void fail(String msg) {System.out.println(msg); fail();}
    static void unexpected(Throwable t) {failed++; t.printStackTrace();}
    static void check(boolean cond) {if (cond) pass(); else fail();}
    static void equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) pass();
        else fail(x + " not equal to " + y);}

    @Test
    public static int test_NextBytes (int execution) {
        try {
            realMain();
        } catch (Throwable t) {
            unexpected(t);
        }
        if (failed > 0) {
            return -1;
        } else {
            return execution;
        }
    }
}
