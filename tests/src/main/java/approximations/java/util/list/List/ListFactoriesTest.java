package approximations.java.util.list.List;

import approximations.Test;

import java.util.List;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

import static java.util.Arrays.asList;

public class ListFactoriesTest {
    /*public static <T> List<T> List_of() {
        return new ArrayList<>();
    }*/

    public static <T> List<T> List_of(T[] elements) {
        return new ArrayList<>(asList(elements));
    }

    static final int NUM_STRINGS = 20; // should be larger than the largest fixed-arg overload
    static final String[] stringArray;
    static {
        String[] sa = new String[NUM_STRINGS];
        for (int i = 0; i < NUM_STRINGS; i++) {
            sa[i] = String.valueOf((char)('a' + i));
        }
        stringArray = sa;
    }

    // returns array of [actual, expected]
    static Object[] a(List<String> act, List<String> exp) {
        return new Object[] { act, exp };
    }

    /*@DataProvider(name="empty")
    public Iterator<Object[]> empty() {
        return Collections.singletonList(
                a(List_of(), asList())
        ).iterator();
    }*/

    static final Object[] empty = a(List_of(new String[] {}), asList());

    /*@DataProvider(name="nonempty")
    public Iterator<Object[]> nonempty() {
        return asList(
                a(List_of(new String[] { "a" } ),
                        asList("a")),
                a(List_of(new String[] { "a", "b" }),
                        asList("a", "b")),
                a(List_of(new String[] { "a", "b", "c" }),
                        asList("a", "b", "c")),
                a(List_of(new String[] { "a", "b", "c", "d" }),
                        asList("a", "b", "c", "d")),
                a(List_of(new String[] { "a", "b", "c", "d", "e" }),
                        asList("a", "b", "c", "d", "e")),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f" }),
                        asList("a", "b", "c", "d", "e", "f")),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g" }),
                        asList("a", "b", "c", "d", "e", "f", "g")),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h" }),
                        asList("a", "b", "c", "d", "e", "f", "g", "h")),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" }),
                        asList("a", "b", "c", "d", "e", "f", "g", "h", "i")),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" }),
                        asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")),
                a(List_of(stringArray),
                        asList(stringArray))
        ).iterator();
    }*/

    static final List<Object[]> nonempty = asList(
            a(List_of(new String[] { "a" } ),
                    asList("a")),
            a(List_of(new String[] { "a", "b" }),
                    asList("a", "b")),
            a(List_of(new String[] { "a", "b", "c" }),
                    asList("a", "b", "c")),
            a(List_of(new String[] { "a", "b", "c", "d" }),
                    asList("a", "b", "c", "d")),
            a(List_of(new String[] { "a", "b", "c", "d", "e" }),
                    asList("a", "b", "c", "d", "e")),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f" }),
                    asList("a", "b", "c", "d", "e", "f")),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g" }),
                    asList("a", "b", "c", "d", "e", "f", "g")),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h" }),
                    asList("a", "b", "c", "d", "e", "f", "g", "h")),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" }),
                    asList("a", "b", "c", "d", "e", "f", "g", "h", "i")),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" }),
                    asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")),
            a(List_of(stringArray),
                    asList(stringArray)));

    /*@DataProvider(name="sublists")
    public Iterator<Object[]> sublists() {
        return asList(
                a((new ArrayList<String>()).subList(0,0),
                        asList()),
                a(List_of(new String[] { "a" }).subList(0,0),
                        asList("a").subList(0,0)),
                a(List_of(new String[] { "a", "b" }).subList(0,1),
                        asList("a", "b").subList(0,1)),
                a(List_of(new String[] { "a", "b", "c" }).subList(1,3),
                        asList("a", "b", "c").subList(1,3)),
                a(List_of(new String[] { "a", "b", "c", "d" }).subList(0,4),
                        asList("a", "b", "c", "d").subList(0,4)),
                a(List_of(new String[] { "a", "b", "c", "d", "e" }).subList(0,3),
                        asList("a", "b", "c", "d", "e").subList(0,3)),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f" }).subList(3, 5),
                        asList("a", "b", "c", "d", "e", "f").subList(3, 5)),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g" }).subList(0, 7),
                        asList("a", "b", "c", "d", "e", "f", "g").subList(0, 7)),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h" }).subList(0, 0),
                        asList("a", "b", "c", "d", "e", "f", "g", "h").subList(0, 0)),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" }).subList(4, 5),
                        asList("a", "b", "c", "d", "e", "f", "g", "h", "i").subList(4, 5)),
                a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" }).subList(1,10),
                        asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j").subList(1,10)),
                a(List_of(stringArray).subList(5, NUM_STRINGS),
                        asList(Arrays.copyOfRange(stringArray, 5, NUM_STRINGS)))
        ).iterator();
    }*/

    static final List<Object[]> sublists = asList(
            a((new ArrayList<String>()).subList(0,0),
                    asList()),
            a(List_of(new String[] { "a" }).subList(0,0),
                    asList("a").subList(0,0)),
            a(List_of(new String[] { "a", "b" }).subList(0,1),
                    asList("a", "b").subList(0,1)),
            a(List_of(new String[] { "a", "b", "c" }).subList(1,3),
                    asList("a", "b", "c").subList(1,3)),
            a(List_of(new String[] { "a", "b", "c", "d" }).subList(0,4),
                    asList("a", "b", "c", "d").subList(0,4)),
            a(List_of(new String[] { "a", "b", "c", "d", "e" }).subList(0,3),
                    asList("a", "b", "c", "d", "e").subList(0,3)),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f" }).subList(3, 5),
                    asList("a", "b", "c", "d", "e", "f").subList(3, 5)),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g" }).subList(0, 7),
                    asList("a", "b", "c", "d", "e", "f", "g").subList(0, 7)),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h" }).subList(0, 0),
                    asList("a", "b", "c", "d", "e", "f", "g", "h").subList(0, 0)),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" }).subList(4, 5),
                    asList("a", "b", "c", "d", "e", "f", "g", "h", "i").subList(4, 5)),
            a(List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" }).subList(1,10),
                    asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j").subList(1,10)),
            a(List_of(stringArray).subList(5, NUM_STRINGS),
                    asList(Arrays.copyOfRange(stringArray, 5, NUM_STRINGS))));

    //@DataProvider(name="all")
    public static List<Object[]> getAll() {
        List<Object[]> all = new ArrayList<>();
        /*empty().forEachRemaining(all::add);
        nonempty().forEachRemaining(all::add);
        sublists().forEachRemaining(all::add);
        return all.iterator();*/
        all.add(empty);
        all.addAll(nonempty);
        all.addAll(sublists);
        return all;
    }
    static final List<Object[]> all = getAll();

    //@DataProvider(name="nonsublists")
    public static List<Object[]> getNonsublists() {
        List<Object[]> all = new ArrayList<>();
        /*empty().forEachRemaining(all::add);
        nonempty().forEachRemaining(all::add);
        return all.iterator();*/
        all.add(empty);
        all.addAll(nonempty);
        return all;
    }
    static final List<Object[]> nonsublists = getNonsublists();

    //@Test(dataProvider="all", expectedExceptions=UnsupportedOperationException.class)
    @Test(executionMax = 23)
    public static int test_cannotAddLast(int execution) {
        List<String> act = (List<String>) all.get(execution)[0];
        try {
            act.add("x");
            return -1;
        } catch (UnsupportedOperationException e) {
            return execution;
        }
    }

    //@Test(dataProvider="all", expectedExceptions=UnsupportedOperationException.class)
    @Test(executionMax = 23)
    public static int test_cannotAddFirst(int execution) {
        List<String> act = (List<String>) all.get(execution)[0];
        try {
            act.add(0, "x");
            return -1;
        } catch (UnsupportedOperationException e) {
            return execution;
        }
    }

    //@Test(dataProvider="nonempty", expectedExceptions=UnsupportedOperationException.class)
    @Test(executionMax = 10)
    public static int test_cannotRemove(int execution) {
        List<String> act = (List<String>) nonempty.get(execution)[0];
        try {
            act.remove(0);
            return -1;
        } catch (UnsupportedOperationException e) {
            return execution;
        }
    }

    //@Test(dataProvider="nonempty", expectedExceptions=UnsupportedOperationException.class)
    @Test(executionMax = 10)
    public static int test_cannotSet(int execution) {
        List<String> act = (List<String>) nonempty.get(execution)[0];
        try {
            act.set(0, "x");
            return -1;
        } catch (UnsupportedOperationException e) {
            return execution;
        }
    }

    //@Test(dataProvider="all")
    @Test(executionMax = 23)
    public static int test_contentsMatch(int execution) {
        List<String> act = (List<String>) all.get(execution)[0];
        List<String> exp = (List<String>) all.get(execution)[1];
        //assertEquals(act, exp);
        if (!act.equals(exp)) {
            return -1;
        } else {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed1(int execution) {
        try {
            List_of(new Object[] { null }); // force one-arg overload
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    /*@Test
    public static int test_nullDisallowed2a(int execution) {
        try {
            List_of(new String[] { "a", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed2b(int execution) {
        try {
            List_of(new String[] { null, "b" });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed3(int execution) {
        try {
            List_of(new String[] { "a", "b", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed4(int execution) {
        try {
            List_of(new String[] { "a", "b", "c", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed5(int execution) {
        try {
            List_of(new String[] { "a", "b", "c", "d", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed6(int execution) {
        try {
            List_of(new String[] { "a", "b", "c", "d", "e", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed7(int execution) {
        try {
            List_of(new String[] { "a", "b", "c", "d", "e", "f", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed8(int execution) {
        try {
            List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed9(int execution) {
        try {
        List.of("a", "b", "c", "d", "e", "f", "g", "h", null);
    }*/

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowed10(int execution) {
        try {
            List_of(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", null });
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullDisallowedN(int execution) {
        try {
            String[] array = stringArray.clone();
            array[0] = null;
            List_of(array);
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_nullArrayDisallowed(int execution) {
        try {
            List_of(null);
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    @Test
    public static int test_ensureArrayCannotModifyList(int execution) {
        String[] array = stringArray.clone();
        List<String> list = List_of(array);
        array[0] = "xyzzy";
        //assertEquals(list, Arrays.asList(stringArray));
        if (!list.equals(Arrays.asList(stringArray))) {
            return -1;
        } else {
            return execution;
        }
    }

    //@Test(dataProvider="all", expectedExceptions=NullPointerException.class)
    @Test(executionMax = 23)
    public static int test_containsNullShouldThrowNPE(int execution) {
        try {
            List<String> act = (List<String>) all.get(execution)[0];
            act.contains(null);
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(dataProvider="all", expectedExceptions=NullPointerException.class)
    @Test(executionMax = 23)
    public static int test_indexOfNullShouldThrowNPE(int execution) {
        try {
            List<String> act = (List<String>) all.get(execution)[0];
            act.indexOf(null);
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(dataProvider="all", expectedExceptions=NullPointerException.class)
    @Test(executionMax = 23)
    public static int test_lastIndexOfNullShouldThrowNPE(int execution) {
        try {
            List<String> act = (List<String>) all.get(execution)[0];
            act.lastIndexOf(null);
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    // List.of().subList views should not be Serializable
    //@Test(dataProvider="sublists")
    @Test(executionMax = 11)
    public static int test_isNotSerializable(int execution) {
        List<String> act = (List<String>) sublists.get(execution)[0];
        //assertFalse(act instanceof Serializable);
        if (act instanceof Serializable) {
            return -1;
        } else {
            return execution;
        }
    }

    // ... but List.of() should be
    //@Test(dataProvider="nonsublists")
    @Test(executionMax = 11)
    public static int test_serialEquality(int execution) {
        // assume that act.equals(exp) tested elsewhere
        List<String> act = (List<String>) nonsublists.get(execution)[0];
        List<String> copy = new ArrayList<>();
        copy.addAll(act);
        if (!act.equals(copy) || !copy.equals(act)) {
            return -1;
        } else {
            return execution;
        }
        /*assertEquals(act, copy);
        assertEquals(copy, exp);*/
    }

    /*@SuppressWarnings("unchecked")
    static <T> T serialClone(T obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(obj);
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new AssertionError(e);
        }
    }*/

    static List<Integer> genList() {
        return new ArrayList<>(Arrays.asList(1, 2, 3));
    }

    @Test
    public static int test_copyOfResultsEqual(int execution) {
        List<Integer> orig = genList();
        List<Integer> copy = new ArrayList<>(orig);
        if (!orig.equals(copy) || !copy.equals(orig)) {
            return -1;
        } else {
            return execution;
        }
        /*assertEquals(orig, copy);
        assertEquals(copy, orig);*/
    }

    @Test
    public static int test_copyOfModifiedUnequal(int execution) {
        List<Integer> orig = genList();
        List<Integer> copy = new ArrayList<>(orig);
        orig.add(4);
        if (orig.equals(copy) || copy.equals(orig)) {
            return -1;
        } else {
            return execution;
        }
        /*assertNotEquals(orig, copy);
        assertNotEquals(copy, orig);*/
    }

    @Test
    public static int test_copyOfIdentity(int execution) {
        List<Integer> orig = genList();
        List<Integer> copy1 = new ArrayList<>(orig);
        List<Integer> copy2 = new ArrayList<>(copy1);
        if (orig == copy1 || copy1 != copy2) {
            return -1;
        } else {
            return execution;
        }
        /*assertNotSame(orig, copy1);
        assertSame(copy1, copy2);*/
    }

    @Test
    public static int test_copyOfSubList(int execution) {
        List<Integer> orig = List_of(new Integer[] { 0, 1, 2, 3 });
        List<Integer> sub = orig.subList(0, 3);
        List<Integer> copy = new ArrayList<>(sub);
        if (sub == copy) {
            return -1;
        } else {
            return execution;
        }
        //assertNotSame(sub, copy);
    }

    @Test
    public static int test_copyOfSubSubList(int execution) {
        List<Integer> orig = List_of(new Integer[] { 0, 1, 2, 3 });
        List<Integer> sub = orig.subList(0, 3).subList(0, 2);
        List<Integer> copy = new ArrayList<>(sub);
        if (sub == copy) {
            return -1;
        } else {
            return execution;
        }
        //assertNotSame(sub, copy);
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_copyOfRejectsNullCollection(int execution) {
        try {
            List<Integer> list = new ArrayList<>(null);
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_copyOfRejectsNullElements(int execution) {
        try {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, null, 3));
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    //@Test(expectedExceptions=NullPointerException.class)
    @Test
    public static int test_copyOfRejectsNullElements2(int execution) {
        try {
            List<String> list = new ArrayList<>(List_of(new String[] { "a", null, "c" }));
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    @Test
    public static int test_copyOfCopiesNullAllowingList(int execution) {
        //List<String> orig = Stream.of("a", "b", "c").toList();
        List<String> orig = List_of(new String[] { "a", "b", "c" });
        List<String> copy = new ArrayList<>(orig);
        if (orig == copy) {
            return -1;
        } else {
            return execution;
        }
        //assertNotSame(orig, copy);
    }

    @Test
    public static int test_iteratorShouldNotBeListIterator(int execution) {
        List<Integer> list = List_of(new Integer[] { 1, 2, 3, 4, 5 });
        Iterator<Integer> it = list.iterator();
        it.next();
        try {
            ((ListIterator<Integer>) it).previous();
            return -1;
        } catch (ClassCastException|UnsupportedOperationException ignore) {
            return execution;
        }
    }
}
