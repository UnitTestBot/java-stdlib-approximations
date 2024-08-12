package approximations.java.util.optional;

import approximations.Test;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicBoolean;

@Test
public class BasicDoubleTest {
    static final double DOUBLEVAL = Math.PI;
    static final double UNEXPECTED = 6.62607004E-34;

    /**
     * Checks a block of assertions over an empty OptionalDouble.
     */
    int checkEmpty (OptionalDouble empty, int execution) {
        if (!empty.equals(OptionalDouble.empty())) {
            return -1;
        }
        if (!OptionalDouble.empty().equals(empty)) {
            return -1;
        }
        if (empty.equals(OptionalDouble.of(UNEXPECTED))) {
            return -1;
        }
        if (OptionalDouble.of(UNEXPECTED).equals(empty)) {
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
            empty.getAsDouble();
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

        if (empty.toString() != "OptionalDouble.empty") {
            return -1;
        }
        return execution;
    }

    /**
     * Checks a block of assertions over an OptionalDouble that is expected to
     * have a particular value present.
     */
    int checkPresent (OptionalDouble opt, double expected, int execution) {
        if (opt.equals(OptionalDouble.empty())) {
            return -1;
        }
        if (OptionalDouble.empty().equals(opt)) {
            return -1;
        }
        if (!opt.equals(OptionalDouble.of(expected))) {
            return -1;
        }
        if (!OptionalDouble.of(expected).equals(opt)) {
            return -1;
        }
        if (opt.equals(OptionalDouble.of(UNEXPECTED))) {
            return -1;
        }
        if (OptionalDouble.of(UNEXPECTED).equals(opt)) {
            return -1;
        }
        if (opt.equals("unexpected")) {
            return -1;
        }

        if (!opt.isPresent()) {
            return -1;
        }
        //assertFalse(opt.isEmpty());
        if (opt.hashCode() != Double.hashCode(expected)) {
            return -1;
        }
        if (opt.orElse(UNEXPECTED) != expected) {
            return -1;
        }
        if (opt.orElseGet(() -> UNEXPECTED) != expected) {
            return -1;
        }

        if (opt.getAsDouble() != expected) {
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

        if (!opt.toString().equals("OptionalDouble[" + expected + "]")) {
            return -1;
        }
        return execution;
    }

    @Test
    public int test_Empty (int execution) {
        return checkEmpty(OptionalDouble.empty(), execution);
    }

    @Test
    public int test_Present (int execution) {
        return checkPresent(OptionalDouble.of(DOUBLEVAL), DOUBLEVAL, execution);
    }

    @Test(disabled = true)
    public int test_StreamEmpty (int execution) {
        //assertEquals(OptionalDouble.empty().stream().toArray(), new double[] { });
        return execution;
    }

    @Test(disabled = true)
    public int test_StreamPresent (int execution) {
        //assertEquals(OptionalDouble.of(DOUBLEVAL).stream().toArray(), new double[] { DOUBLEVAL });
        return execution;
    }
}
