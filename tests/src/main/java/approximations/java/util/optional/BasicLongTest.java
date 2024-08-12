package approximations.java.util.optional;

import approximations.Test;

import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.concurrent.atomic.AtomicBoolean;

public class BasicLongTest {
    static final long LONGVAL = 2_305_843_008_139_952_128L;
    static final long UNEXPECTED = 0xFEEDBEEFCAFEBABEL;

    /**
     * Checks a block of assertions over an empty OptionalLong.
     */
    int checkEmpty(OptionalLong empty, int execution) {
        if (!empty.equals(OptionalLong.empty())) {
            return -1;
        }
        if (!OptionalLong.empty().equals(empty)) {
            return -1;
        }
        if (empty.equals(OptionalLong.of(UNEXPECTED))) {
            return -1;
        }
        if (OptionalLong.of(UNEXPECTED).equals(empty)) {
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
        if (empty.orElse(UNEXPECTED) != UNEXPECTED) {
            return -1;
        }
        if (empty.orElseGet(() -> UNEXPECTED) != UNEXPECTED) {
            return -1;
        }

        try {
            empty.getAsLong();
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

        if (empty.toString() != "OptionalLong.empty") {
            return -1;
        }
        return execution;
    }

    /**
     * Checks a block of assertions over an OptionalLong that is expected to
     * have a particular value present.
     */
    int checkPresent(OptionalLong opt, long expected, int execution) {
        if (opt.equals(OptionalLong.empty())) {
            return -1;
        }
        if (OptionalLong.empty().equals(opt)) {
            return -1;
        }
        if (!opt.equals(OptionalLong.of(expected))) {
            return -1;
        }
        if (!OptionalLong.of(expected).equals(opt)) {
            return -1;
        }
        if (opt.equals(OptionalLong.of(UNEXPECTED))) {
            return -1;
        }
        if (OptionalLong.of(UNEXPECTED).equals(opt)) {
            return -1;
        }
        if (opt.equals("unexpected")) {
            return -1;
        }

        if (!opt.isPresent()) {
            return -1;
        }
        //assertFalse(opt.isEmpty());
        if (opt.hashCode() != Long.hashCode(expected)) {
            return -1;
        }
        if (opt.orElse(UNEXPECTED) != expected) {
            return -1;
        }
        if (opt.orElseGet(() -> UNEXPECTED) != expected) {
            return -1;
        }

        if (opt.getAsLong() != expected) {
            return -1;
        }
        //assertEquals(opt.orElseThrow(), expected);
        //assertEquals(opt.orElseThrow(ObscureException::new), expected);

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

        if (opt.toString() != "OptionalLong[" + expected + "]") {
            return -1;
        }
        return execution;
    }

    @Test
    public int test_Empty (int execution) {
        return checkEmpty(OptionalLong.empty(), execution);
    }

    @Test
    public int test_Present (int execution) {
        return checkPresent(OptionalLong.of(LONGVAL), LONGVAL, execution);
    }

    @Test(disabled = true)
    public int test_StreamEmpty (int execution) {
        //assertEquals(OptionalLong.empty().stream().toArray(), new long[] { });
        return execution;
    }

    @Test(disabled = true)
    public int test_StreamPresent (int execution) {
        //assertEquals(OptionalLong.of(LONGVAL).stream().toArray(), new long[] { LONGVAL });
        return execution;
    }
}
