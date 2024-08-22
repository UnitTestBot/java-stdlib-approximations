package approximations.java.util.optional;

import approximations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Test
public class BasicTest {
    public <T> Stream<T> Optional_stream(Optional<T> optional) {
        if (!optional.isPresent()) {
            return Stream.empty();
        } else {
            return Stream.of(optional.get());
        }
    }

    public <E> List<E> List_of() {
        return new ArrayList<E>();
    }

    public <T> List<T> List_of(T element) {
        ArrayList<T> list = new ArrayList<T>();
        list.add(element);
        return list;
    }

    int checkEmpty(Optional<String> empty, int execution) {
        if (!empty.equals(Optional.empty())) {
            return -1;
        }
        if (!Optional.empty().equals(empty)) {
            return -1;
        }
        if (empty.equals(Optional.of("unexpected"))) {
            return -1;
        }
        if (Optional.of("unexpected").equals(empty)) {
            return -1;
        }
        if (empty.equals("unexpected")) {
            return -1;
        }

        if (empty.isPresent()) {
            return -1;
        }
        //if (!empty.isEmpty());
        if (empty.hashCode() != 0) {
            return -1;
        }
        if (empty.orElse("x") != "x") {
            return -1;
        }
        if (empty.orElseGet(() -> "y") != "y") {
            return -1;
        }

        try {
            empty.get();
            return -1;
        } catch (NoSuchElementException e) {
        }
        //assertThrows(NoSuchElementException.class, () -> empty.orElseThrow());
        //assertThrows(ObscureException.class,       () -> empty.orElseThrow(ObscureException::new));

        AtomicBoolean b = new AtomicBoolean();
        empty.ifPresent(s -> b.set(true));
        if (b.get()) {
            return -1;
        }

        AtomicBoolean b1 = new AtomicBoolean(false);
        AtomicBoolean b2 = new AtomicBoolean(false);
        empty.ifPresent(s -> b1.set(true));
        if (!b1.get()) {
            b2.set(true);
        }
        if (b1.get()) {
            return -1;
        }
        if (!b2.get()) {
            return -1;
        }

        if (empty.toString() != "Optional.empty") {
            return -1;
        }
        return execution;
    }

    /**
     * Checks a block of assertions over an Optional that is expected to
     * have a particular value present.
     */
    int checkPresent(Optional<String> opt, String expected, int execution) {
        if (opt.equals(Optional.empty())) {
            return -1;
        }
        if (Optional.empty().equals(opt)) {
            return -1;
        }
        if (!opt.equals(Optional.of(expected))) {
            return -1;
        }
        if (!Optional.of(expected).equals(opt)) {
            return -1;
        }
        if (opt.equals(Optional.of("unexpected"))) {
            return -1;
        }
        if (Optional.of("unexpected").equals(opt)) {
            return -1;
        }
        if (opt.equals("unexpected")) {
            return -1;
        }

        if (!opt.isPresent()) {
            return -1;
        }
        //assertFalse(opt.isEmpty());
        if (opt.hashCode() != expected.hashCode()) {
            return -1;
        }
        if (opt.orElse("unexpected") != expected) {
            return -1;
        }
        if (opt.orElseGet(() -> "unexpected") != expected) {
            return -1;
        }

        if (opt.get() != expected) {
            return -1;
        }
        //if (opt.orElseThrow() != expected);
        //if (opt.orElseThrow(ObscureException::new), expected);

        AtomicBoolean b = new AtomicBoolean(false);
        opt.ifPresent(s -> b.set(true));
        if (!b.get()) {
            return -1;
        }

        AtomicBoolean b1 = new AtomicBoolean(false);
        AtomicBoolean b2 = new AtomicBoolean(false);
        opt.ifPresent(s -> b1.set(true));
        if (!b1.get()) {
            b2.set(true);
        }
        if (!b1.get()) {
            return -1;
        }
        if (b2.get()) {
            return -1;
        }

        if (opt.toString() != "Optional[" + expected + "]") {
            return -1;
        }
        return execution;
    }

    @Test
    public int test_Empty (int execution) {
        return checkEmpty(Optional.empty(), execution);
    }

    @Test
    public int test_OfNull (int execution) {
        try {
            Optional.of(null);
            return -1;
        } catch (NullPointerException e) {
            return execution;
        }
    }

    @Test
    public int test_OfPresent (int execution) {
        return checkPresent(Optional.of("xyzzy"), "xyzzy", execution);
    }

    @Test
    public int test_OfNullableNull (int execution) {
        return checkEmpty(Optional.ofNullable(null), execution);
    }

    @Test
    public int test_OfNullablePresent (int execution) {
        return checkPresent(Optional.ofNullable("xyzzy"), "xyzzy", execution);
    }

    @Test
    public int test_FilterEmpty (int execution) {
        AtomicBoolean failed = new AtomicBoolean(false);
        int result = checkEmpty(Optional.<String>empty().filter(s -> { failed.set(true); return true; }), execution);
        if (failed.get()) {
            return -1;
        } else {
            return result;
        }
    }

    @Test
    public int test_FilterFalse (int execution) {
        return checkEmpty(Optional.of("xyzzy").filter(s -> s.equals("plugh")), execution);
    }

    @Test
    public int test_FilterTrue (int execution) {
        return checkPresent(Optional.of("xyzzy").filter(s -> s.equals("xyzzy")), "xyzzy", execution);
    }

    @Test
    public int test_MapEmpty (int execution) {
        AtomicBoolean failed = new AtomicBoolean(false);
        int result = checkEmpty(Optional.empty().map(s -> { failed.set(true); return ""; }), execution);
        if (failed.get()) {
            return -1;
        } else {
            return result;
        }
    }

    @Test
    public int test_MapPresent (int execution) {
        return checkPresent(Optional.of("xyzzy").map(s -> s.replace("xyzzy", "plugh")),
                "plugh", execution);
    }

    @Test
    public int test_FlatMapEmpty (int execution) {
        AtomicBoolean failed = new AtomicBoolean(false);
        int result = checkEmpty(Optional.empty().flatMap(s -> { failed.set(true); return Optional.of(""); }),
                execution);
        if (failed.get()) {
            return -1;
        } else {
            return result;
        }
    }

    @Test
    public int test_FlatMapPresentReturnEmpty (int execution) {
        AtomicBoolean failed = new AtomicBoolean(false);
        int result = checkEmpty(Optional.of("xyzzy")
                .flatMap(s -> {
                    if (s != "xyzzy") {
                        failed.set(true);
                    }
                    return Optional.empty();
                }), execution);
        if (failed.get()) {
            return -1;
        } else {
            return result;
        }
    }

    @Test
    public int test_FlatMapPresentReturnPresent (int execution) {
        AtomicBoolean failed = new AtomicBoolean(false);
        int result = checkPresent(Optional.of("xyzzy")
                        .flatMap(s -> {
                            if (s != "xyzzy") {
                                failed.set(true);
                            }
                            return Optional.of("plugh");
                        }),
                "plugh", execution);
        if (failed.get()) {
            return -1;
        } else {
            return result;
        }
    }

    @Test
    public int test_OrEmptyEmpty (int execution) {
        try {
            Optional.<String>empty().get();
            return -1;
        } catch (NoSuchElementException e) {
            return checkEmpty(Optional.empty(), execution);
        }
    }

    @Test
    public int test_OrEmptyPresent (int execution) {
        try {
            Optional.<String>empty().get();
            return -1;
        } catch (NoSuchElementException e) {
            return checkPresent(Optional.of("plugh"), "plugh", execution);
        }
    }

    @Test(disabled = true)
    public int test_OrPresentDontCare (int execution) {
        Optional<String> optional = Optional.of("xyzzy");
        if (optional.isPresent()) {
            return checkPresent(optional, "xyzzy", execution);
        } else {
            return -1;
        }
    }

    @Test
    public int testStreamEmpty(int execution) {
        if (Optional_stream(Optional.empty()).collect(toList()) != List_of()) {
            return -1;
        }
        return execution;
    }

    @Test
    public int testStreamPresent(int execution) {
        if (Optional_stream(Optional.of("xyzzy")).collect(toList()) != List_of("xyzzy")) {
            return -1;
        }
        return execution;
    }
}
