package approximations.java.util.Spliterator;

import approximations.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Test
public class SpliteratorCharacteristicsTest {
    /*public void testSpliteratorFromCharSequence() {
        class CharSequenceImpl implements CharSequence {
            final String s;

            public CharSequenceImpl(String s) {
                this.s = s;
            }

            @Override
            public int length() {
                return s.length();
            }

            @Override
            public char charAt(int index) {
                return s.charAt(index);
            }

            @Override
            public CharSequence subSequence(int start, int end) {
                return s.subSequence(start, end);
            }

            @Override
            public String toString() {
                return s;
            }
        }

        CharSequence cs = "A";
        Spliterator.OfInt s = cs.chars().spliterator();
        assertCharacteristics(s, Spliterator.IMMUTABLE | Spliterator.ORDERED |
                Spliterator.SIZED | Spliterator.SUBSIZED);
        assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
        s = cs.codePoints().spliterator();
        assertCharacteristics(s, Spliterator.IMMUTABLE | Spliterator.ORDERED);
        assertHasNotCharacteristics(s, Spliterator.CONCURRENT);

        for (CharSequence c : Arrays.asList(new CharSequenceImpl("A"),
                new StringBuilder("A"),
                new StringBuffer("A"))) {
            s = cs.chars().spliterator();
            assertCharacteristics(s, Spliterator.ORDERED |
                    Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
            s = cs.codePoints().spliterator();
            assertCharacteristics(s, Spliterator.ORDERED);
            assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
        }
    }

    public void testSpliteratorFromCollection() {
        List<Integer> l = Arrays.asList(1, 2, 3, 4);

        {
            Spliterator<?> s = Spliterators.spliterator(l, 0);
            assertCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliterator(l, Spliterator.CONCURRENT);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliterator(l.iterator(), 1, 0);
            assertCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliterator(l.iterator(), 1, Spliterator.CONCURRENT);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(l.iterator(), 0);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(
                    l.iterator(), Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }
    }

    public void testSpliteratorOfIntFromIterator() {
        Supplier<PrimitiveIterator.OfInt> si = () -> IntStream.of(1, 2, 3, 4).iterator();

        {
            Spliterator<?> s = Spliterators.spliterator(si.get(), 1, 0);
            assertCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliterator(si.get(), 1, Spliterator.CONCURRENT);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(si.get(), 0);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(
                    si.get(), Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }
    }

    public void testSpliteratorOfLongFromIterator() {
        Supplier<PrimitiveIterator.OfLong> si = () -> LongStream.of(1, 2, 3, 4).iterator();

        {
            Spliterator<?> s = Spliterators.spliterator(si.get(), 1, 0);
            assertCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliterator(si.get(), 1, Spliterator.CONCURRENT);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(si.get(), 0);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(
                    si.get(), Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }
    }

    public void testSpliteratorOfDoubleFromIterator() {
        Supplier<PrimitiveIterator.OfDouble> si = () -> DoubleStream.of(1, 2, 3, 4).iterator();

        {
            Spliterator<?> s = Spliterators.spliterator(si.get(), 1, 0);
            assertCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliterator(si.get(), 1, Spliterator.CONCURRENT);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
            assertCharacteristics(s, Spliterator.CONCURRENT);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(si.get(), 0);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        {
            Spliterator<?> s = Spliterators.spliteratorUnknownSize(
                    si.get(), Spliterator.SIZED | Spliterator.SUBSIZED);
            assertHasNotCharacteristics(s, Spliterator.SIZED | Spliterator.SUBSIZED);
        }
    }

    //

    public void testHashMap() {
        assertMapCharacteristics(new HashMap<>(),
                Spliterator.SIZED | Spliterator.DISTINCT);
    }

    public void testHashSet() {
        assertSetCharacteristics(new HashSet<>(),
                Spliterator.SIZED | Spliterator.DISTINCT);
    }

    public void testLinkedHashMap() {
        assertMapCharacteristics(new LinkedHashMap<>(),
                Spliterator.SIZED | Spliterator.DISTINCT |
                        Spliterator.ORDERED);
    }

    public void testLinkedHashSet() {
        assertSetCharacteristics(new LinkedHashSet<>(),
                Spliterator.SIZED | Spliterator.DISTINCT |
                        Spliterator.ORDERED);
    }


    //


    void assertMapCharacteristics(Map<Integer, String> m, int keyCharacteristics) {
        assertMapCharacteristics(m, keyCharacteristics, 0);
    }

    void assertMapCharacteristics(Map<Integer, String> m, int keyCharacteristics, int notValueCharacteristics) {
        initMap(m);

        assertCharacteristics(m.keySet(), keyCharacteristics);

        assertCharacteristics(m.values(),
                keyCharacteristics & ~(Spliterator.DISTINCT | notValueCharacteristics));

        assertCharacteristics(m.entrySet(), keyCharacteristics);

        if ((keyCharacteristics & Spliterator.SORTED) == 0) {
            assertISEComparator(m.keySet());
            assertISEComparator(m.values());
            assertISEComparator(m.entrySet());
        }
    }

    void assertSetCharacteristics(Set<Integer> s, int keyCharacteristics) {
        initSet(s);

        assertCharacteristics(s, keyCharacteristics);

        if ((keyCharacteristics & Spliterator.SORTED) == 0) {
            assertISEComparator(s);
        }
    }

    void initMap(Map<Integer, String> m) {
        m.put(1, "4");
        m.put(2, "3");
        m.put(3, "2");
        m.put(4, "1");
    }

    void initSet(Set<Integer> s) {
        s.addAll(Arrays.asList(1, 2, 3, 4));
    }

    void assertCharacteristics(Collection<?> c, int expectedCharacteristics) {
        assertCharacteristics(c.spliterator(), expectedCharacteristics);
    }

    void assertCharacteristics(Spliterator<?> s, int expectedCharacteristics) {
        assertTrue(s.hasCharacteristics(expectedCharacteristics),
                "Spliterator characteristics");
    }

    void assertHasNotCharacteristics(Spliterator<?> s, int expectedCharacteristics) {
        assertFalse(s.hasCharacteristics(expectedCharacteristics),
                "Spliterator characteristics");
    }

    void assertNullComparator(Collection<?> c) {
        assertNull(c.spliterator().getComparator(),
                "Comparator of Spliterator of Collection");
    }

    void assertNotNullComparator(Collection<?> c) {
        assertNotNull(c.spliterator().getComparator(),
                "Comparator of Spliterator of Collection");
    }

    void assertISEComparator(Collection<?> c) {
        assertISEComparator(c.spliterator());
    }

    void assertISEComparator(Spliterator<?> s) {
        boolean caught = false;
        try {
            s.getComparator();
        }
        catch (IllegalStateException e) {
            caught = true;
        }
        assertTrue(caught, "Throwing IllegalStateException");
    }*/
}
