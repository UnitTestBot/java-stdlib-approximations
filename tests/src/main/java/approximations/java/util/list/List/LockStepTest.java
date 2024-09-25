package approximations.java.util.list.List;

import approximations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Test
public class LockStepTest {
    //static final int DEFAULT_SIZE = 5;
    static final int size = 5;           // Running time is O(size**2)

    /*boolean maybe(int n) { return rnd.nextInt(n) == 0; }

    void test() {
        size = DEFAULT_SIZE;

        lockSteps(new ArrayList(),
                new LinkedList());
    }*/

    static boolean equalLists(List... lists) {
        for (List list : lists) {
            if (!equalLists(list, lists[0])) {
                return false;
            }
        }
        return true;
    }

    static boolean equalLists(List<Integer> x, List<Integer> y) {
        /*equal(x, y);
        equal(y, x);
        equal(x.size(),     y.size());
        equal(x.isEmpty(),  y.isEmpty());
        equal(x.hashCode(), y.hashCode());
        equal(x.toString(), y.toString());
        equal(x.toArray(),  y.toArray());*/
        if (!equal(x, y)) { return false; }
        if (!equal(y, x)) { return false; }
        if (!equal(x.size(), y.size())) { return false; }
        if (!equal(x.isEmpty(), y.isEmpty())) { return false; }
        if (!equal(x.toString(), y.toString())) { return false; }
        if (!equal((Object) x.toArray(), y.toArray())) { return false; }
        return true;
    }

    @Test(executionMax = 1)
    public static int test_EmptyLists(int execution) {
        List<Integer>[] lists = new List[] { new ArrayList<>(), new LinkedList<>() };
        if(testEmptyList(lists[execution])) {
            return execution;
        } else {
            return -1;
        }
    }

    public static List<Integer>[] prepareLists() throws Exception {
        List<Integer>[] lists = new List[] { new ArrayList<>(), new LinkedList<>() };
        for (int i = 0; i < size; i++) {
            ListFrobber adder = new RandomAdder();
            for (final List list : lists) {
                if (!adder.frob(list)) { throw new Exception(); }
                if (!equal(list.size(), i+1)) {
                    throw new Exception();
                }
            }
            if (!equalLists(lists)) {
                throw new Exception();
            }
        }
        return lists;
    }

    @Test
    public static int test_RightAfterPreparation(int execution) {
        try {
            prepareLists();
            return execution;
        } catch (Exception e) {
            return -1;
        }
    }

    @Test(executionMax = 1)
    public static int test_CME1(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        final ListFrobber adder = new RandomAdder();
        List<Integer> list = lists[execution];
        try {
            Iterator it = list.iterator();
            if (!adder.frob(list)) { return -1; }
            it.next();
        } catch (ConcurrentModificationException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_CME2(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        final ListFrobber remover = new RandomRemover();
        List<Integer> list = lists[execution];
        try {
            Iterator it = asSubList(list).iterator();
            if (!remover.frob(list)) { return -1; }
            it.next();
        } catch (ConcurrentModificationException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_CME3(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        final ListFrobber adder = new RandomAdder();
        List<Integer> list = lists[execution];
        try {
            Iterator it = asSubList(asSubList(list)).iterator();
            if (!adder.frob(list)) { return -1; }
            it.next();
        } catch (ConcurrentModificationException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_CME4(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        final ListFrobber remover = new RandomRemover();
        List<Integer> list = lists[execution];
        try {
            List subList = asSubList(list);
            if (!remover.frob(list)) { return -1; }
            subList.get(0);
        } catch (ConcurrentModificationException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_CME5(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        final ListFrobber adder = new RandomAdder();
        List<Integer> list = lists[execution];
        try {
            List sl = asSubList(list);
            List ssl = asSubList(sl);
            if (!adder.frob(sl)) { return -1; }
            ssl.get(0);
        } catch (ConcurrentModificationException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_CME6(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        final ListFrobber remover = new RandomRemover();
        List<Integer> list = lists[execution];
        try {
            List sl = asSubList(list);
            List ssl = asSubList(sl);
            if (!remover.frob(sl)) { return -1; }
            ssl.get(0);
        } catch (ConcurrentModificationException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_IndexOutOfBounds1(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        List<Integer> l = lists[execution];
        final List sl = asSubList(l);
        final List ssl = asSubList(sl);
        ssl.add(0, 42);
        if (!equal(ssl.get(0), 42)) { return -1; }
        if (!equal(sl.get(0), 42)) { return -1; }
        if (!equal(l.get(0), 42)) { return -1; }
        final int rndIndex = rnd.nextInt(l.size());
        try {
            l.subList(rndIndex, rndIndex).get(0);
        } catch (IndexOutOfBoundsException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_IndexOutOfBounds2(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        List<Integer> l = lists[execution];
        final List sl = asSubList(l);
        final List ssl = asSubList(sl);
        ssl.add(0, 42);
        if (!equal(ssl.get(0), 42)) { return -1; }
        if (!equal(sl.get(0), 42)) { return -1; }
        if (!equal(l.get(0), 42)) { return -1; }
        final int s = l.size();
        try {
            l.subList(s/2, s).get(s/2 + 1);
        } catch (IndexOutOfBoundsException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_IndexOutOfBounds3(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        List<Integer> l = lists[execution];
        final List sl = asSubList(l);
        final List ssl = asSubList(sl);
        ssl.add(0, 42);
        if (!equal(ssl.get(0), 42)) { return -1; }
        if (!equal(sl.get(0), 42)) { return -1; }
        if (!equal(l.get(0), 42)) { return -1; }
        final int s = l.size();
        try {
            l.subList(s/2, s).get(-1);
        } catch (IndexOutOfBoundsException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_IllegalArgument1(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        List<Integer> l = lists[execution];
        final List sl = asSubList(l);
        final List ssl = asSubList(sl);
        ssl.add(0, 42);
        if (!equal(ssl.get(0), 42)) { return -1; }
        if (!equal(sl.get(0), 42)) { return -1; }
        if (!equal(l.get(0), 42)) { return -1; }
        try {
            l.subList(1, 0);
        } catch (IllegalArgumentException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_IllegalArgument2(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        List<Integer> l = lists[execution];
        final List sl = asSubList(l);
        final List ssl = asSubList(sl);
        ssl.add(0, 42);
        if (!equal(ssl.get(0), 42)) { return -1; }
        if (!equal(sl.get(0), 42)) { return -1; }
        if (!equal(l.get(0), 42)) { return -1; }
        try {
            sl.subList(1, 0);
        } catch (IllegalArgumentException e) {
            return execution;
        }
        return -1;
    }

    @Test(executionMax = 1)
    public static int test_IllegalArgument3(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        List<Integer> l = lists[execution];
        final List sl = asSubList(l);
        final List ssl = asSubList(sl);
        ssl.add(0, 42);
        if (!equal(ssl.get(0), 42)) { return -1; }
        if (!equal(sl.get(0), 42)) { return -1; }
        if (!equal(l.get(0), 42)) { return -1; }
        try {
            ssl.subList(1, 0);
        } catch (IllegalArgumentException e) {
            return execution;
        }
        return -1;
    }

    @Test
    public static int test_Final(int execution) {
        List<Integer>[] lists;
        try {
            lists = prepareLists();
        } catch (Exception e) {
            return -1;
        }

        for (final List list : lists) {
            if (!equalLists(list, asSubList(list))) {
                return -1;
            }
            if (!equalLists(list, asSubList(asSubList(list)))) {
                return -1;
            }
        }
        for (int i = lists[0].size(); i > 0; i--) {
            ListFrobber remover = new RandomRemover();
            for (final List list : lists)
                if (!remover.frob(list)) { return -1; }
            if (!equalLists(lists)) {
                return -1;
            }
        }
        return execution;
    }

    /*void lockSteps(List... lists) {
        for (final List list : lists)
            testEmptyList(list);
        for (int i = 0; i < size; i++) {
            ListFrobber adder = new RandomAdder();
            for (final List list : lists) {
                adder.frob(list);
                equal(list.size(), i+1);
            }
            equalLists(lists);
        }
        {
            final ListFrobber adder = new RandomAdder();
            final ListFrobber remover = new RandomRemover();
            for (final List list : lists) {

                THROWS(ConcurrentModificationException.class,
                        new F(){void f(){
                            Iterator it = list.iterator();
                            adder.frob(list);
                            it.next();}},
                        new F(){void f(){
                            Iterator it = asSubList(list).iterator();
                            remover.frob(list);
                            it.next();}},
                        new F(){void f(){
                            Iterator it = asSubList(asSubList(list)).iterator();
                            adder.frob(list);
                            it.next();}},
                        new F(){void f(){
                            List subList = asSubList(list);
                            remover.frob(list);
                            subList.get(0);}},
                        new F(){void f(){
                            List sl = asSubList(list);
                            List ssl = asSubList(sl);
                            adder.frob(sl);
                            ssl.get(0);}},
                        new F(){void f(){
                            List sl = asSubList(list);
                            List ssl = asSubList(sl);
                            remover.frob(sl);
                            ssl.get(0);}});
            }
        }

        for (final List l : lists) {
            final List sl = asSubList(l);
            final List ssl = asSubList(sl);
            ssl.add(0, 42);
            equal(ssl.get(0), 42);
            equal(sl.get(0), 42);
            equal(l.get(0), 42);
            final int s = l.size();
            final int rndIndex = rnd.nextInt(l.size());
            THROWS(IndexOutOfBoundsException.class,
                    new F(){void f(){l.subList(rndIndex, rndIndex).get(0);}},
                    new F(){void f(){l.subList(s/2, s).get(s/2 + 1);}},
                    new F(){void f(){l.subList(s/2, s).get(-1);}}
            );
            THROWS(IllegalArgumentException.class,
                    new F(){void f(){  l.subList(1, 0);}},
                    new F(){void f(){ sl.subList(1, 0);}},
                    new F(){void f(){ssl.subList(1, 0);}});
        }

        equalLists(lists);

        for (final List list : lists) {
            equalLists(list, asSubList(list));
            equalLists(list, asSubList(asSubList(list)));
        }
        for (final List list : lists)
            System.out.println(list);

        for (int i = lists[0].size(); i > 0; i--) {
            ListFrobber remover = randomRemover();
            for (final List list : lists)
                remover.frob(list);
            equalLists(lists);
        }
    }*/

    static <T> List<T> asSubList(List<T> list) {
        return list.subList(0, list.size());
    }

    static boolean testEmptyCollection(Collection<?> c) {
        /*check(c.isEmpty());
        equal(c.size(), 0);
        equal(c.toString(),"[]");
        equal(c.toArray().length, 0);
        equal(c.toArray(new Object[0]).length, 0);

        Object[] a = new Object[1]; a[0] = Boolean.TRUE;
        equal(c.toArray(a), a);
        equal(a[0], null);*/
        if (!c.isEmpty()) { return false; }
        if (!equal(c.size(), 0)) { return false; }
        if (!equal(c.toString(),"[]")) { return false; }
        if (!equal(c.toArray().length, 0)) { return false; }
        if (!equal(c.toArray(new Object[0]).length, 0)) { return false; }

        Object[] a = new Object[1]; a[0] = Boolean.TRUE;
        if (!equal(c.toArray(a), a)) { return false; }
        if (!equal(a[0], null)) { return false; }
        return true;
    }

    static boolean testEmptyList(List list) {
        if (!testEmptyCollection(list)) {
            return false;
        }
        //equal(list, Collections.emptyList());
        return list.equals(new ArrayList<Integer>());
    }

    static final Random rnd = new Random();

    abstract static class ListFrobber { abstract boolean frob(List<Integer> l); }

    static class RandomAdder extends ListFrobber {
        final Integer e = rnd.nextInt(1024);
        final int subListCount = rnd.nextInt(3);
        final boolean atBeginning = rnd.nextBoolean();
        final boolean useIterator = rnd.nextBoolean();
        @Override
        boolean frob(List<Integer> l) {
            final int s = l.size();
            List ll = l;
            for (int i = 0; i < subListCount; i++)
                ll = asSubList(ll);
            if (!useIterator) {
                if (atBeginning) {
                    switch (rnd.nextInt(3)) {
                        case 0: ll.add(0, e); break;
                        case 1: ll.subList(0, rnd.nextInt(s+1)).add(0, e); break;
                        case 2: ll.subList(0, rnd.nextInt(s+1)).subList(0,0).add(0,e); break;
                        default: throw new Error();
                    }
                } else {
                    switch (rnd.nextInt(3)) {
                        case 0: if (!ll.add(e)) { return false; } break;
                        case 1: ll.subList(s/2, s).add(s - s/2, e); break;
                        case 2: ll.subList(s, s).subList(0, 0).add(0, e); break;
                        default: throw new Error();
                    }
                }
            } else {
                if (atBeginning) {
                    ListIterator it = ll.listIterator();
                    if (!equal(it.nextIndex(), 0)) { return false; }
                    if(it.hasPrevious()) { return false; }
                    it.add(e);
                    if (!equal(it.previousIndex(), 0)) { return false; }
                    if (!equal(it.nextIndex(), 1)) { return false; }
                    if (!it.hasPrevious()) { return false; }
                } else {
                    final int siz = ll.size();
                    ListIterator it = ll.listIterator(siz);
                    if (!equal(it.previousIndex(), siz-1)) { return false; }
                    if (it.hasNext()) { return false; }
                    it.add(e);
                    if (!equal(it.previousIndex(), siz)) { return false; }
                    if (!equal(it.nextIndex(), siz+1)) { return false; }
                    if (it.hasNext()) { return false; }
                    if (!it.hasPrevious()) { return false; }
                }
            }
            return true;
        }
    }

    /*ListFrobber randomAdder() {
        final Integer e = rnd.nextInt(1024);
        final int subListCount = rnd.nextInt(3);
        final boolean atBeginning = rnd.nextBoolean();
        final boolean useIterator = rnd.nextBoolean();
        return new ListFrobber() {void frob(List l) {
            final int s = l.size();
            List ll = l;
            for (int i = 0; i < subListCount; i++)
                ll = asSubList(ll);
            if (! useIterator) {
                if (atBeginning) {
                    switch (rnd.nextInt(3)) {
                        case 0: ll.add(0, e); break;
                        case 1: ll.subList(0, rnd.nextInt(s+1)).add(0, e); break;
                        case 2: ll.subList(0, rnd.nextInt(s+1)).subList(0,0).add(0,e); break;
                        default: throw new Error();
                    }
                } else {
                    switch (rnd.nextInt(3)) {
                        case 0: check(ll.add(e)); break;
                        case 1: ll.subList(s/2, s).add(s - s/2, e); break;
                        case 2: ll.subList(s, s).subList(0, 0).add(0, e); break;
                        default: throw new Error();
                    }
                }
            } else {
                if (atBeginning) {
                    ListIterator it = ll.listIterator();
                    equal(it.nextIndex(), 0);
                    check(! it.hasPrevious());
                    it.add(e);
                    equal(it.previousIndex(), 0);
                    equal(it.nextIndex(), 1);
                    check(it.hasPrevious());
                } else {
                    final int siz = ll.size();
                    ListIterator it = ll.listIterator(siz);
                    equal(it.previousIndex(), siz-1);
                    check(! it.hasNext());
                    it.add(e);
                    equal(it.previousIndex(), siz);
                    equal(it.nextIndex(), siz+1);
                    check(! it.hasNext());
                    check(it.hasPrevious());
                }
            }}};
    }*/

    static class RandomRemover extends ListFrobber {
        final int position = rnd.nextInt(3);
        final int subListCount = rnd.nextInt(3);
        @Override
        boolean frob(List<Integer> l) {
            final int s = l.size();
            List ll = l;
            for (int i = 0; i < subListCount; i++)
                ll = asSubList(ll);
            switch (position) {
                case 0: // beginning
                    switch (rnd.nextInt(3)) {
                        case 0: ll.remove(0); break;
                        case 1: {
                            final Iterator it = ll.iterator();
                            if (!it.hasNext()) { return false; }
                            /*THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});*/
                            try {
                                it.remove();
                                return false;
                            } catch (IllegalStateException e) {}
                            it.next();
                            it.remove();
                            /*THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});*/
                            try {
                                it.remove();
                                return false;
                            } catch (IllegalStateException e) {}
                            break;}
                        case 2: {
                            final ListIterator it = ll.listIterator();
                            if (!it.hasNext()) { return false; }
                            /*THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});*/
                            try {
                                it.remove();
                                return false;
                            } catch (IllegalStateException e) {}
                            it.next();
                            it.remove();
                            /*THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});*/
                            try {
                                it.remove();
                                return false;
                            } catch (IllegalStateException e) {}
                            break;}
                        default: throw new Error();
                    }
                    break;
                case 1: // midpoint
                    switch (rnd.nextInt(3)) {
                        case 0: ll.remove(s/2); break;
                        case 1: {
                            final ListIterator it = ll.listIterator(s/2);
                            it.next();
                            it.remove();
                            break;
                        }
                        case 2: {
                            final ListIterator it = ll.listIterator(s/2+1);
                            it.previous();
                            it.remove();
                            break;
                        }
                        default: throw new Error();
                    }
                    break;
                case 2: // end
                    switch (rnd.nextInt(3)) {
                        case 0: ll.remove(s-1); break;
                        case 1: ll.subList(s-1, s).clear(); break;
                        case 2:
                            final ListIterator it = ll.listIterator(s);
                            if (it.hasNext()) { return false; }
                            if (!it.hasPrevious()) { return false; }
                            /*THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});*/
                            try {
                                it.remove();
                                return false;
                            } catch (IllegalStateException e) {}
                            it.previous();
                            if (!equal(it.nextIndex(), s-1)) { return false; }
                            if (!it.hasNext()) { return false; }
                            it.remove();
                            if (!equal(it.nextIndex(), s-1)) { return false; }
                            if (it.hasNext()) { return false; }
                            /*THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});*/
                            try {
                                it.remove();
                            } catch (IllegalStateException e) {}
                            break;
                        default: throw new Error();
                    }
                    break;
                default: throw new Error();
            }
            return true;
        }
    }

    /*ListFrobber randomRemover() {
        final int position = rnd.nextInt(3);
        final int subListCount = rnd.nextInt(3);
        return new ListFrobber() {void frob(List l) {
            final int s = l.size();
            List ll = l;
            for (int i = 0; i < subListCount; i++)
                ll = asSubList(ll);
            switch (position) {
                case 0: // beginning
                    switch (rnd.nextInt(3)) {
                        case 0: ll.remove(0); break;
                        case 1: {
                            final Iterator it = ll.iterator();
                            check(it.hasNext());
                            THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});
                            it.next();
                            it.remove();
                            THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});
                            break;}
                        case 2: {
                            final ListIterator it = ll.listIterator();
                            check(it.hasNext());
                            THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});
                            it.next();
                            it.remove();
                            THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});
                            break;}
                        default: throw new Error();
                    }
                    break;
                case 1: // midpoint
                    switch (rnd.nextInt(3)) {
                        case 0: ll.remove(s/2); break;
                        case 1: {
                            final ListIterator it = ll.listIterator(s/2);
                            it.next();
                            it.remove();
                            break;
                        }
                        case 2: {
                            final ListIterator it = ll.listIterator(s/2+1);
                            it.previous();
                            it.remove();
                            break;
                        }
                        default: throw new Error();
                    }
                    break;
                case 2: // end
                    switch (rnd.nextInt(3)) {
                        case 0: ll.remove(s-1); break;
                        case 1: ll.subList(s-1, s).clear(); break;
                        case 2:
                            final ListIterator it = ll.listIterator(s);
                            check(! it.hasNext());
                            check(it.hasPrevious());
                            THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});
                            it.previous();
                            equal(it.nextIndex(), s-1);
                            check(it.hasNext());
                            it.remove();
                            equal(it.nextIndex(), s-1);
                            check(! it.hasNext());
                            THROWS(IllegalStateException.class,
                                    new F(){void f(){it.remove();}});
                            break;
                        default: throw new Error();
                    }
                    break;
                default: throw new Error();
            }}};
    }*/

    //--------------------- Infrastructure ---------------------------
    /*volatile int passed = 0, failed = 0;
    void pass() {passed++;}
    void fail() {failed++;}
    void unexpected() {failed++;}
    //static void check(boolean cond) {if (cond) pass(); else fail();}*/
    static boolean equal(Object x, Object y) {
        if (x == null ? y == null : x.equals(y)) {
            return true;
        }
        else {
            return false;
        }
    }
    //<T> void equal(T[] x, T[] y) {check(Arrays.equals(x,y));}

    /*@Test
    int test_LockStep (int execution) {
        try {test();} catch (Throwable t) {unexpected();}
        if (failed > 0) {
            return -1;
        } else {
            return execution;
        }
    }

    abstract class F {abstract void f() throws Throwable;}
    static void THROWS(Class<? extends Throwable> k, F... fs) {
        for (F f : fs)
            try {f.f(); fail();}
            catch (Throwable t) {
                if (k.isAssignableFrom(t.getClass())) pass();
                else unexpected();}}*/
}
