package approximations.java.util.optional;

import approximations.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.DoubleStream;

@Test
public class BasicDoubleTest {
    static final double DOUBLEVAL = Math.PI;
    static final double UNEXPECTED = 6.62607004E-34;

    /**
     * Checks a block of assertions over an empty OptionalDouble.
     */

    public static DoubleStream OptionalDouble_stream(OptionalDouble optional) {
        if (optional.isPresent()) {
            return DoubleStream.of(optional.getAsDouble());
        } else {
            return DoubleStream.empty();
        }
    }

    static int checkEmpty(OptionalDouble empty, int execution) {
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
    static int checkPresent(OptionalDouble opt, double expected, int execution) {
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
        if (opt.orElse(UNEXPECTED) != expected) {
            return -1;
        }
        if (opt.orElseGet(() -> UNEXPECTED) != expected) {
            return -1;
        }

        if (opt.getAsDouble() != expected) {
            return -1;
        }

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

        String optToString = opt.toString();
        //String test = String.valueOf(expected);
        if (!optToString.equals("OptionalDouble[" + expected + "]")) {
            return -1;
        }
        return execution;
    }

    @Test(disabled = true)
    public static int test_Empty(int execution) {
        return checkEmpty(OptionalDouble.empty(), execution);
    }

    @Test
    public static int test_Present(int execution) {
        return checkPresent(OptionalDouble.of(DOUBLEVAL), DOUBLEVAL, execution);
    }

    @Test(disabled = true)
    public static int test_StreamEmpty(int execution) {
        if (!Arrays.equals(OptionalDouble_stream(OptionalDouble.empty()).toArray(), new double[]{})) {
            return -1;
        }
        return execution;
    }

    @Test(disabled = true)
    public static int test_StreamPresent (int execution) {
        if (!Arrays.equals(OptionalDouble_stream(OptionalDouble.of(DOUBLEVAL)).toArray(), new double[]{DOUBLEVAL})) {
            return -1;
        }
        return execution;
    }
}
