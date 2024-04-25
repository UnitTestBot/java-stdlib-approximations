// Generated by the LibSL translator.  DO NOT EDIT!
// sources:
//  - java/lang/Integer.lsl:30
//  - java/lang/Integer.main.lsl:20
//
package generated.java.lang;

import java.lang.Class;
import java.lang.Comparable;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import org.jacodb.approximation.annotation.Approximate;
import org.usvm.api.Engine;
import runtime.LibSLRuntime;

/**
 * IntegerAutomaton for LSLInteger ~> java.lang.Integer
 */
@SuppressWarnings({"all", "unchecked"})
@Approximate(java.lang.Integer.class)
public final class Integer implements LibSLRuntime.Automaton, Comparable<Integer> {
    private static final long serialVersionUID = 1360826667806852920L;

    public static final int MIN_VALUE = -2147483648;

    public static final int MAX_VALUE = 2147483647;

    public static final Class TYPE = java.lang.Integer.class;

    public static final int SIZE = 32;

    public static final int BYTES = 4;

    public static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static final char[] DigitTens = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9' };

    public static final char[] DigitOnes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public static final int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, 2147483647 };

    static {
        Engine.assume(true);
    }

    private int value;

    @LibSLRuntime.AutomatonConstructor
    public Integer(Void __$lsl_token, final byte p0, final int p1) {
        this.value = p1;
    }

    @LibSLRuntime.AutomatonConstructor
    public Integer(final Void __$lsl_token) {
        this(__$lsl_token, __$lsl_States.Initialized, 0);
    }

    /**
     * [CONSTRUCTOR] IntegerAutomaton::<init>(LSLInteger, int) -> void
     * Source: java/lang/Integer.main.lsl:114
     */
    public Integer(int v) {
        this((Void) null);
        /* body */ {
            this.value = v;
        }
    }

    /**
     * [SUBROUTINE] IntegerAutomaton::_parse(String) -> int
     * Source: java/lang/Integer.main.lsl:93
     */
    private static int _parse(String str) throws java.lang.NumberFormatException {
        int result = 0;
        /* body */ {
            if (str == null) {
                throw new NullPointerException();
            }
            LibSLRuntime.todo();
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::bitCount(int) -> int
     * Source: java/lang/Integer.main.lsl:122
     */
    public static int bitCount(int i) {
        int result = 0;
        /* body */ {
            i = i - ((i >>> 1) & 1431655765);
            i = (i & 858993459) + ((i >>> 2) & 858993459);
            i = (i + (i >>> 4)) & 252645135;
            i = i + (i >>> 8);
            i = i + (i >>> 16);
            result = i & 63;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::compare(int, int) -> int
     * Source: java/lang/Integer.main.lsl:134
     */
    public static int compare(int x, int y) {
        int result = 0;
        /* body */ {
            if (x == y) {
                result = 0;
            } else {
                if (x < y) {
                    result = -1;
                } else {
                    result = 1;
                }
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::compareUnsigned(int, int) -> int
     * Source: java/lang/Integer.main.lsl:150
     */
    public static int compareUnsigned(int x, int y) {
        int result = 0;
        /* body */ {
            x += MIN_VALUE;
            y += MIN_VALUE;
            if (x == y) {
                result = 0;
            } else {
                if (x < y) {
                    result = -1;
                } else {
                    result = 1;
                }
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::divideUnsigned(int, int) -> int
     * Source: java/lang/Integer.main.lsl:176
     */
    public static int divideUnsigned(int dividend, int divisor) {
        int result = 0;
        /* body */ {
            final long unsignedDividend = ((long) dividend) & 4294967295L;
            final long unsignedDivisor = ((long) divisor) & 4294967295L;
            result = ((int) (unsignedDividend / unsignedDivisor));
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::hashCode(int) -> int
     * Source: java/lang/Integer.main.lsl:202
     */
    public static int hashCode(int value) {
        int result = 0;
        /* body */ {
            result = value;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::highestOneBit(int) -> int
     * Source: java/lang/Integer.main.lsl:208
     */
    public static int highestOneBit(int i) {
        int result = 0;
        /* body */ {
            i |= i >> 1;
            i |= i >> 2;
            i |= i >> 4;
            i |= i >> 8;
            i |= i >> 16;
            result = i - (i >>> 1);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::lowestOneBit(int) -> int
     * Source: java/lang/Integer.main.lsl:220
     */
    public static int lowestOneBit(int i) {
        int result = 0;
        /* body */ {
            result = i & -i;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::max(int, int) -> int
     * Source: java/lang/Integer.main.lsl:227
     */
    public static int max(int a, int b) {
        int result = 0;
        /* body */ {
            if (a > b) {
                result = a;
            } else {
                result = b;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::min(int, int) -> int
     * Source: java/lang/Integer.main.lsl:236
     */
    public static int min(int a, int b) {
        int result = 0;
        /* body */ {
            if (a < b) {
                result = a;
            } else {
                result = b;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::numberOfLeadingZeros(int) -> int
     * Source: java/lang/Integer.main.lsl:245
     */
    public static int numberOfLeadingZeros(int i) {
        int result = 0;
        /* body */ {
            if (i == 0) {
                result = 32;
            } else {
                result = 1;
                if ((i >>> 16) == 0) {
                    result += 16;
                    i <<= 16;
                }
                if ((i >>> 24) == 0) {
                    result += 8;
                    i <<= 8;
                }
                if ((i >>> 28) == 0) {
                    result += 4;
                    i <<= 4;
                }
                if ((i >>> 30) == 0) {
                    result += 2;
                    i <<= 2;
                }
                result -= i >>> 31;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::numberOfTrailingZeros(int) -> int
     * Source: java/lang/Integer.main.lsl:266
     */
    public static int numberOfTrailingZeros(int i) {
        int result = 0;
        /* body */ {
            if (i == 0) {
                result = 32;
            } else {
                int y = 0;
                result = 31;
                y = i << 16;
                if (y != 0) {
                    result -= 16;
                    i = y;
                }
                y = i << 8;
                if (y != 0) {
                    result -= 8;
                    i = y;
                }
                y = i << 4;
                if (y != 0) {
                    result -= 4;
                    i = y;
                }
                y = i << 2;
                if (y != 0) {
                    result -= 2;
                    i = y;
                }
                result -= (i << 1) >>> 31;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::remainderUnsigned(int, int) -> int
     * Source: java/lang/Integer.main.lsl:330
     */
    public static int remainderUnsigned(int dividend, int divisor) {
        int result = 0;
        /* body */ {
            final long unsignedDividend = ((long) dividend) & 4294967295L;
            final long unsignedDivisor = ((long) divisor) & 4294967295L;
            result = ((int) (unsignedDividend % unsignedDivisor));
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::reverse(int) -> int
     * Source: java/lang/Integer.main.lsl:338
     */
    public static int reverse(int i) {
        int result = 0;
        /* body */ {
            i = (((i & 1431655765) << 1) | (i >>> 1)) & 1431655765;
            i = (((i & 858993459) << 2) | (i >>> 2)) & 858993459;
            i = (((i & 252645135) << 4) | (i >>> 4)) & 252645135;
            i = (i << 24) | ((i & 65280) << 8) | ((i >>> 8) & 65280) | (i >>> 24);
            result = i;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::reverseBytes(int) -> int
     * Source: java/lang/Integer.main.lsl:351
     */
    public static int reverseBytes(int i) {
        int result = 0;
        /* body */ {
            result = (i >>> 24) | ((i >> 8) & 65280) | ((i << 8) & 16711680) | (i << 24);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::rotateLeft(int, int) -> int
     * Source: java/lang/Integer.main.lsl:361
     */
    public static int rotateLeft(int i, int distance) {
        int result = 0;
        /* body */ {
            result = (i << distance) | (i >>> -distance);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::rotateRight(int, int) -> int
     * Source: java/lang/Integer.main.lsl:368
     */
    public static int rotateRight(int i, int distance) {
        int result = 0;
        /* body */ {
            result = (i >>> distance) | (i << -distance);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::signum(int) -> int
     * Source: java/lang/Integer.main.lsl:375
     */
    public static int signum(int i) {
        int result = 0;
        /* body */ {
            result = (i >> 31) | (-i >>> 31);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::sum(int, int) -> int
     * Source: java/lang/Integer.main.lsl:382
     */
    public static int sum(int a, int b) {
        int result = 0;
        /* body */ {
            result = a + b;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::toString(int) -> String
     * Source: java/lang/Integer.main.lsl:406
     */
    public static String toString(int i) {
        String result = null;
        /* body */ {
            result = LibSLRuntime.toString(i);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::toUnsignedLong(int) -> long
     * Source: java/lang/Integer.main.lsl:418
     */
    public static long toUnsignedLong(int x) {
        long result = 0L;
        /* body */ {
            result = ((long) x) & 4294967295L;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::valueOf(int) -> Integer
     * Source: java/lang/Integer.main.lsl:451
     */
    public static java.lang.Integer valueOf(int i) {
        java.lang.Integer result = null;
        /* body */ {
            result = (java.lang.Integer) ((Object) new Integer((Void) null, 
                /* state = */ Integer.__$lsl_States.Initialized, 
                /* value = */ i
            ));
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::byteValue(LSLInteger) -> byte
     * Source: java/lang/Integer.main.lsl:461
     */
    public byte byteValue() {
        byte result = ((byte) 0);
        /* body */ {
            result = ((byte) this.value);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::compareTo(LSLInteger, LSLInteger) -> int
     * Source: java/lang/Integer.main.lsl:467
     */
    public int compareTo(Integer anotherInteger) {
        int result = 0;
        /* body */ {
            final int x = this.value;
            final int y = ((Integer) ((Object) anotherInteger)).value;
            if (x == y) {
                result = 0;
            } else {
                if (x < y) {
                    result = -1;
                } else {
                    result = 1;
                }
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::doubleValue(LSLInteger) -> double
     * Source: java/lang/Integer.main.lsl:486
     */
    public double doubleValue() {
        double result = 0.0d;
        /* body */ {
            result = ((double) this.value);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::equals(LSLInteger, Object) -> boolean
     * Source: java/lang/Integer.main.lsl:492
     */
    public boolean equals(Object obj) {
        boolean result = false;
        /* body */ {
            if ((obj instanceof java.lang.Integer)) {
                result = this.value == ((Integer) ((Object) obj)).value;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::floatValue(LSLInteger) -> float
     * Source: java/lang/Integer.main.lsl:501
     */
    public float floatValue() {
        float result = 0.0f;
        /* body */ {
            result = ((float) this.value);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::hashCode(LSLInteger) -> int
     * Source: java/lang/Integer.main.lsl:507
     */
    public int hashCode() {
        int result = 0;
        /* body */ {
            result = this.value;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::intValue(LSLInteger) -> int
     * Source: java/lang/Integer.main.lsl:513
     */
    public int intValue() {
        int result = 0;
        /* body */ {
            result = this.value;
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::longValue(LSLInteger) -> long
     * Source: java/lang/Integer.main.lsl:519
     */
    public long longValue() {
        long result = 0L;
        /* body */ {
            result = ((long) this.value);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::shortValue(LSLInteger) -> short
     * Source: java/lang/Integer.main.lsl:525
     */
    public short shortValue() {
        short result = ((short) 0);
        /* body */ {
            result = ((short) this.value);
        }
        return result;
    }

    /**
     * [FUNCTION] IntegerAutomaton::toString(LSLInteger) -> String
     * Source: java/lang/Integer.main.lsl:531
     */
    public String toString() {
        String result = null;
        /* body */ {
            result = LibSLRuntime.toString(this.value);
        }
        return result;
    }

    public static final class __$lsl_States {
        public static final byte Initialized = (byte) 0;
    }

    @Approximate(Integer.class)
    public static final class __hook {
        private __hook(Void o1, Void o2) {
            Engine.assume(false);
        }
    }
}
