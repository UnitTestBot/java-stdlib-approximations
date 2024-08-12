package approximations.java.util.list.ArrayList;

import approximations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Test
public class Bug6533203Test {
    void test() {
        final List<Integer> superstitious = new ArrayList<Integer>() {
            public void add(int index, Integer i) {
                if (i == 13) throw new Error("unlucky");
                else super.add(index, i); }};
        final ListIterator it = superstitious.listIterator(0);
        equal(it.nextIndex(), 0);
        THROWS(Error.class, new F(){void f(){it.add(13);}});
        equal(it.nextIndex(), 0);
    }

    //--------------------- Infrastructure ---------------------------
    volatile int passed = 0;
    volatile int failed = 0;
    void pass() {passed++;}
    void fail() {failed++; Thread.dumpStack();}
    void fail(String msg) {System.out.println(msg); fail();}
    void unexpected(Throwable t) {failed++; t.printStackTrace();}
    void check(boolean cond) {if (cond) pass(); else fail();}
    void equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) pass();
        else fail(x + " not equal to " + y);}

    @Test
    public int test_bug6533203(int execution) {
        try {test();} catch (Throwable t) {unexpected(t);}
        if (failed > 0) {
            return -1;
        } else {
            return execution;
        }
    }

    abstract class F {abstract void f() throws Throwable;}
    void THROWS(Class<? extends Throwable> k, F... fs) {
        for (F f : fs)
            try {f.f(); fail("Expected " + k.getName() + " not thrown");}
            catch (Throwable t) {
                if (k.isAssignableFrom(t.getClass())) pass();
                else unexpected(t);}}
}
