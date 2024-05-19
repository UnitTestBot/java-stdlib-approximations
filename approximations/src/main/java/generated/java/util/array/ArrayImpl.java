package generated.java.util.array;

import org.jacodb.approximation.annotation.Approximate;

@Approximate(java.lang.reflect.Array.class)
public class ArrayImpl {

    public static int getLength(Object array) throws IllegalArgumentException {
        if (array instanceof int[])
            return ((int[]) array).length;

        if (array instanceof byte[])
            return ((byte[]) array).length;

        if (array instanceof char[])
            return ((char[]) array).length;

        if (array instanceof long[])
            return ((long[]) array).length;

        if (array instanceof boolean[])
            return ((boolean[]) array).length;

        if (array instanceof short[])
            return ((short[]) array).length;

        if (array instanceof float[])
            return ((float[]) array).length;

        if (array instanceof double[])
            return ((double[]) array).length;

        if (array instanceof Object[])
            return ((Object[]) array).length;

        throw new IllegalArgumentException();
    }

    public static Object get(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[])
            return ((int[]) array)[index];

        if (array instanceof byte[])
            return ((byte[]) array)[index];

        if (array instanceof char[])
            return ((char[]) array)[index];

        if (array instanceof long[])
            return ((long[]) array)[index];

        if (array instanceof boolean[])
            return ((boolean[]) array)[index];

        if (array instanceof short[])
            return ((short[]) array)[index];

        if (array instanceof float[])
            return ((float[]) array)[index];

        if (array instanceof double[])
            return ((double[]) array)[index];

        if (array instanceof Object[])
            return ((Object[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static boolean getBoolean(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof boolean[])
            return ((boolean[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static byte getByte(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof byte[])
            return ((byte[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static char getChar(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof char[])
            return ((char[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static short getShort(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof short[])
            return ((short[]) array)[index];

        if (array instanceof byte[])
            return ((byte[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static int getInt(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[])
            return ((int[]) array)[index];

        if (array instanceof byte[])
            return ((byte[]) array)[index];

        if (array instanceof char[])
            return ((char[]) array)[index];

        if (array instanceof short[])
            return ((short[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static long getLong(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[])
            return ((int[]) array)[index];

        if (array instanceof byte[])
            return ((byte[]) array)[index];

        if (array instanceof char[])
            return ((char[]) array)[index];

        if (array instanceof long[])
            return ((long[]) array)[index];

        if (array instanceof short[])
            return ((short[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static float getFloat(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[])
            return ((int[]) array)[index];

        if (array instanceof byte[])
            return ((byte[]) array)[index];

        if (array instanceof char[])
            return ((char[]) array)[index];

        if (array instanceof long[])
            return ((long[]) array)[index];

        if (array instanceof short[])
            return ((short[]) array)[index];

        if (array instanceof float[])
            return ((float[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static double getDouble(Object array, int index) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[])
            return ((int[]) array)[index];

        if (array instanceof byte[])
            return ((byte[]) array)[index];

        if (array instanceof char[])
            return ((char[]) array)[index];

        if (array instanceof long[])
            return ((long[]) array)[index];

        if (array instanceof short[])
            return ((short[]) array)[index];

        if (array instanceof float[])
            return ((float[]) array)[index];

        if (array instanceof double[])
            return ((double[]) array)[index];

        throw new IllegalArgumentException();
    }

    public static void set(Object array, int index, Object value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[]) {
            ((int[]) array)[index] = (int) value;
            return;
        }

        if (array instanceof byte[]) {
            ((byte[]) array)[index] = (byte) value;
            return;
        }

        if (array instanceof char[]) {
            ((char[]) array)[index] = (char) value;
            return;
        }

        if (array instanceof long[]) {
            ((long[]) array)[index] = (long) value;
            return;
        }

        if (array instanceof boolean[]) {
            ((boolean[]) array)[index] = (boolean) value;
            return;
        }

        if (array instanceof short[]) {
            ((short[]) array)[index] = (short) value;
            return;
        }

        if (array instanceof float[]) {
            ((float[]) array)[index] = (float) value;
            return;
        }

        if (array instanceof double[]) {
            ((double[]) array)[index] = (double) value;
            return;
        }

        if (array instanceof Object[]) {
            ((Object[]) array)[index] = value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setBoolean(Object array, int index, boolean value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof boolean[]) {
            ((boolean[]) array)[index] = value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setByte(Object array, int index, byte value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[]) {
            ((int[]) array)[index] = value;
            return;
        }

        if (array instanceof byte[]) {
            ((byte[]) array)[index] = value;
            return;
        }

        if (array instanceof long[]) {
            ((long[]) array)[index] = value;
            return;
        }

        if (array instanceof short[]) {
            ((short[]) array)[index] = value;
            return;
        }

        if (array instanceof float[]) {
            ((float[]) array)[index] = value;
            return;
        }

        if (array instanceof double[]) {
            ((double[]) array)[index] = value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setChar(Object array, int index, char value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[]) {
            ((int[]) array)[index] = value;
            return;
        }

        if (array instanceof char[]) {
            ((char[]) array)[index] = value;
            return;
        }

        if (array instanceof long[]) {
            ((long[]) array)[index] = value;
            return;
        }

        if (array instanceof float[]) {
            ((float[]) array)[index] = value;
            return;
        }

        if (array instanceof double[]) {
            ((double[]) array)[index] = value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setShort(Object array, int index, short value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[]) {
            ((int[]) array)[index] = value;
            return;
        }

        if (array instanceof long[]) {
            ((long[]) array)[index] = value;
            return;
        }

        if (array instanceof short[]) {
            ((short[]) array)[index] = value;
            return;
        }

        if (array instanceof float[]) {
            ((float[]) array)[index] = value;
            return;
        }

        if (array instanceof double[]) {
            ((double[]) array)[index] = value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setInt(Object array, int index, int value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof int[]) {
            ((int[]) array)[index] = value;
            return;
        }

        if (array instanceof long[]) {
            ((long[]) array)[index] = value;
            return;
        }

        if (array instanceof float[]) {
            ((float[]) array)[index] = (float) value;
            return;
        }

        if (array instanceof double[]) {
            ((double[]) array)[index] = value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setLong(Object array, int index, long value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof long[]) {
            ((long[]) array)[index] = value;
            return;
        }

        if (array instanceof double[]) {
            ((double[]) array)[index] = (double) value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setFloat(Object array, int index, float value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof float[]) {
            ((float[]) array)[index] = value;
            return;
        }

        if (array instanceof double[]) {
            ((double[]) array)[index] = value;
            return;
        }

        throw new IllegalArgumentException();
    }

    public static void setDouble(Object array, int index, Object value) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array == null)
            throw new NullPointerException();

        if (array instanceof double[]) {
            ((double[]) array)[index] = (double) value;
            return;
        }

        throw new IllegalArgumentException();
    }
}
