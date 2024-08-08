package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import org.usvm.api.Engine;
import runtime.LibSLRuntime;

import java.nio.*;

@Approximate(java.nio.ByteBuffer.class)
public abstract class ByteBufferImpl extends BufferImpl implements Comparable<ByteBufferImpl> {
    byte[] storage;
    boolean isReadOnly;

    ByteBufferImpl(int mark, int pos, int lim, int cap,
                   byte[] hb, int offset)
    {
        super(mark, pos, lim, cap);
        this.storage = hb;
        this.offset = offset;
    }

    ByteBufferImpl(int mark, int pos, int lim, int cap, byte[] segment) {
        this(mark, pos, lim, cap, segment, 0);
    }

    ByteBufferImpl(byte[] hb, int cap) {
        super(cap);
        this.storage = hb;
        this.offset = 0;
    }

    @Override
    Object base() {
        return storage;
    }

    public static ByteBufferImpl allocateDirect(int capacity) {
        return new DirectByteBufferImpl(capacity);
    }

    public static ByteBufferImpl allocate(int capacity) {
        if (capacity < 0)
            throw createCapacityException(capacity);
        return new HeapByteBufferImpl(capacity, capacity);
    }

    public static ByteBufferImpl wrap(byte[] array,
                                      int offset, int length)
    {
        try {
            return new HeapByteBufferImpl(array, offset, length);
        } catch (IllegalArgumentException x) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static ByteBufferImpl wrap(byte[] array) {
        return wrap(array, 0, array.length);
    }

    @Override
    public abstract ByteBufferImpl slice();

    @Override
    public abstract ByteBufferImpl slice(int index, int length);

    @Override
    public abstract ByteBufferImpl duplicate();

    public abstract ByteBufferImpl asReadOnlyBuffer();

    public byte get() {
        int indexWithOffset = applyOffset(nextGetIndex());
        Engine.assume(indexWithOffset < storage.length);
        return storage[indexWithOffset];
    }

    public ByteBufferImpl put(byte b) {
        int indexWithOffset = applyOffset(nextPutIndex());
        Engine.assume(indexWithOffset < storage.length);
        storage[indexWithOffset] = b;
        return this;
    }

    public byte get(int index) {
        int indexWithOffset = applyOffset(checkIndex(index));
        Engine.assume(indexWithOffset < storage.length);
        return storage[indexWithOffset];
    }

    public ByteBufferImpl put(int index, byte b) {
        int indexWithOffset = applyOffset(checkIndex(index));
        Engine.assume(indexWithOffset < storage.length);
        storage[indexWithOffset] = b;
        return this;
    }

    public ByteBufferImpl get(byte[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        if (length > remaining())
            throw new BufferUnderflowException();
        int indexWithOffset = applyOffset(nextGetIndex(length));
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(storage, indexWithOffset, dst, offset, length);
        return this;
    }

    public ByteBufferImpl get(byte[] dst) {
        return get(dst, 0, dst.length);
    }

    public ByteBufferImpl get(int index, byte[] dst, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, dst.length);
        int indexWithOffset = applyOffset(nextGetIndex(length));
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(storage, indexWithOffset, dst, offset, length);
        return this;
    }

    public ByteBufferImpl get(int index, byte[] dst) {
        return get(index, dst, 0, dst.length);
    }

    public ByteBufferImpl put(ByteBufferImpl src) {
        if (src == this)
            throw createSameBufferException();
        if (isReadOnly())
            throw new ReadOnlyBufferException();

        int srcPos = src.position();
        int srcLim = src.limit();
        int srcRem = (srcPos <= srcLim ? srcLim - srcPos : 0);
        int pos = position();
        int lim = limit();
        int rem = (pos <= lim ? lim - pos : 0);

        if (srcRem > rem)
            throw new BufferOverflowException();

        putBuffer(pos, src, srcPos, srcRem);

        position(pos + srcRem);
        src.position(srcPos + srcRem);

        return this;
    }

    public ByteBufferImpl put(int index, ByteBufferImpl src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.limit());
        if (isReadOnly())
            throw new ReadOnlyBufferException();

        putBuffer(index, src, offset, length);

        return this;
    }

    void putBuffer(int pos, ByteBufferImpl src, int srcPos, int n) {
        Object srcBase = src.base();
        assert srcBase != null || src.isDirect();
        Object base = base();
        assert base != null || isDirect();
        int indexWithOffset = applyOffset(pos);
        int srcIndexWithOffset = src.applyOffset(srcPos);
        Engine.assume(indexWithOffset + n < storage.length);
        Engine.assume(srcIndexWithOffset + n < src.storage.length);
        LibSLRuntime.ArrayActions.copy(src.storage, srcIndexWithOffset, storage, indexWithOffset, n);
    }

    public ByteBufferImpl put(byte[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        if (length > remaining())
            throw new BufferOverflowException();
        int indexWithOffset = applyOffset(nextPutIndex(length));
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, indexWithOffset, length);
        return this;
    }

    public final ByteBufferImpl put(byte[] src) {
        return put(src, 0, src.length);
    }

    public ByteBufferImpl put(int index, byte[] src, int offset, int length) {
        if (isReadOnly())
            throw new ReadOnlyBufferException();
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        int indexWithOffset = applyOffset(index);
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, indexWithOffset, length);
        return this;
    }

    public ByteBufferImpl put(int index, byte[] src) {
        return put(index, src, 0, src.length);
    }

    public final boolean hasArray() {
        return (storage != null) && !isReadOnly;
    }

    public final byte[] array() {
        if (storage == null)
            throw new UnsupportedOperationException();
        if (isReadOnly)
            throw new ReadOnlyBufferException();
        return storage;
    }

    public final int arrayOffset() {
        if (storage == null)
            throw new UnsupportedOperationException();
        if (isReadOnly)
            throw new ReadOnlyBufferException();
        return offset;
    }

    @Override
    public ByteBufferImpl position(int newPosition) {
        super.position(newPosition);
        return this;
    }

    @Override
    public ByteBufferImpl limit(int newLimit) {
        super.limit(newLimit);
        return this;
    }

    @Override
    public ByteBufferImpl mark() {
        super.mark();
        return this;
    }

    @Override
    public ByteBufferImpl reset() {
        super.reset();
        return this;
    }

    @Override
    public ByteBufferImpl clear() {
        super.clear();
        return this;
    }

    @Override
    public ByteBufferImpl flip() {
        super.flip();
        return this;
    }

    @Override
    public ByteBufferImpl rewind() {
        super.rewind();
        return this;
    }

    public ByteBufferImpl compact() {
        int pos = position();
        int lim = limit();
        assert (pos <= lim);
        int rem = lim - pos;
        int srcIndexWithOffset = applyOffset(pos);
        int dstIndexWithOffset = applyOffset(0);
        Engine.assume(srcIndexWithOffset + rem < storage.length);
        Engine.assume(dstIndexWithOffset + rem < storage.length);
        LibSLRuntime.ArrayActions.copy(storage, srcIndexWithOffset, storage, dstIndexWithOffset, rem);
        position(rem);
        limit(capacity());
        discardMark();
        return this;
    }

    public abstract boolean isDirect();

    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int hashCode() {
        int h = 1;
        int p = position();
        for (int i = limit() - 1; i >= p; i--)
            h = 31 * h + (int)get(i);
        return h;
    }

    private int mismatch(ByteBufferImpl a, int aOff, ByteBufferImpl b, int bOff, int length) {
        for (int i = 0; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (!(ob instanceof ByteBufferImpl))
            return false;
        ByteBufferImpl that = (ByteBufferImpl)ob;
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        if (thisRem < 0 || thisRem != thatRem)
            return false;
        return mismatch(this, thisPos, that, thatPos, thisRem) < 0;
    }

    public int compareTo(ByteBufferImpl that) {
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        int length = thisRem < thatRem ? thisRem : thatRem;
        if (length < 0)
            return -1;
        int i = mismatch(this, thisPos,
                that, thatPos,
                length);
        if (i >= 0) {
            return compare(this.get(thisPos + i), that.get(thatPos + i));
        }
        return thisRem - thatRem;
    }

    private static int compare(byte x, byte y) {
        return x - y;
    }

    public int mismatch(ByteBufferImpl that) {
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        int length = thisRem < thatRem ? thisRem : thatRem;
        if (length < 0)
            return -1;
        int r = mismatch(this, thisPos,that, thatPos, length);
        return (r == -1 && thisRem != thatRem) ? length : r;
    }

    boolean bigEndian = true;
    boolean nativeByteOrder = true;

    public final ByteOrder order() {
        return bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    public final ByteBufferImpl order(ByteOrder bo) {
        if (bo != ByteOrder.BIG_ENDIAN) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    public final int alignmentOffset(int index, int unitSize) {
        throw new UnsupportedOperationException();
    }

    public final ByteBufferImpl alignedSlice(int unitSize) {
        int pos = position();
        int lim = limit();

        int pos_mod = alignmentOffset(pos, unitSize);
        int lim_mod = alignmentOffset(lim, unitSize);

        int aligned_pos = (pos_mod > 0)
                ? pos + (unitSize - pos_mod)
                : pos;

        int aligned_lim = lim - lim_mod;

        if (aligned_pos > lim || aligned_lim < pos) {
            aligned_pos = aligned_lim = pos;
        }

        return slice(aligned_pos, aligned_lim - aligned_pos);
    }

    protected char scopedGetChar(int i, boolean be) {
        return (char)scopedGetShort(i, be);
    }

    protected ByteBufferImpl scopedPutChar(int i, char x, boolean be) {
        return scopedPutShort(i, (short)x, be);
    }

    protected short scopedGetShort(int i, boolean be) {
        checkIndex(i, 2);
        int part1 = (get(i) & 0xff);
        int part2 = (get(i + 1) & 0xff);
        return (short)((part1 << 8) | part2);
    }

    protected ByteBufferImpl scopedPutShort(int i, short x, boolean be) {
        checkIndex(i, 2);
        put(i, (byte)x);
        put(i + 1, (byte)(x >>> 8));
        return this;
    }

    protected int scopedGetInt(int i, boolean be) {
        checkIndex(i, 4);
        int part1 = (get(i) & 0xff);
        int part2 = (get(i + 1) & 0xff);
        int part3 = (get(i + 2) & 0xff);
        int part4 = (get(i + 3) & 0xff);
        return ((part1 << 24) | (part2 << 16) | (part3 << 8) | part4);
    }

    protected ByteBufferImpl scopedPutInt(int i, int x, boolean be) {
        checkIndex(i, 4);
        put(i, (byte)x);
        put(i + 1, (byte)(x >>> 8));
        put(i + 2, (byte)(x >>> 16));
        put(i + 3, (byte)(x >>> 24));
        return this;
    }

    protected long scopedGetLong(int i, boolean be) {
        checkIndex(i, 8);
        long part1 = (get(i) & 0xffL);
        long part2 = (get(i + 1) & 0xffL);
        long part3 = (get(i + 2) & 0xffL);
        long part4 = (get(i + 3) & 0xffL);
        long part5 = (get(i + 4) & 0xffL);
        long part6 = (get(i + 5) & 0xffL);
        long part7 = (get(i + 6) & 0xffL);
        long part8 = (get(i + 7) & 0xffL);
        return ((part1 << 56) | (part2 << 48) | (part3 << 40) | (part4 << 32) | (part5 << 24) | (part6 << 16) | (part7 << 8) | part8);
    }

    protected ByteBufferImpl scopedPutLong(int i, long x, boolean be) {
        checkIndex(i, 8);
        put(i, (byte)x);
        put(i + 1, (byte)(x >>> 8));
        put(i + 2, (byte)(x >>> 16));
        put(i + 3, (byte)(x >>> 24));
        put(i + 4, (byte)(x >>> 32));
        put(i + 5, (byte)(x >>> 40));
        put(i + 6, (byte)(x >>> 48));
        put(i + 7, (byte)(x >>> 56));
        return this;
    }

    protected float scopedGetFloat(int i, boolean be) {
        return Float.intBitsToFloat(scopedGetInt(i, be));
    }

    protected ByteBufferImpl scopedPutFloat(int i, float x, boolean be) {
        return scopedPutInt(i, Float.floatToRawIntBits(x), be);
    }

    protected double scopedGetDouble(int i, boolean be) {
        return Double.longBitsToDouble(scopedGetLong(i, be));
    }

    protected ByteBufferImpl scopedPutDouble(int i, double x, boolean be) {
        return scopedPutLong(i, Double.doubleToRawLongBits(x), be);
    }

    public char getChar() {
        return (char)getShort();
    }

    public ByteBufferImpl putChar(char value) {
        return putShort((short)value);
    }

    public char getChar(int index) {
        return (char)getShort(index);
    }

    public ByteBufferImpl putChar(int index, char value) {
        return putShort(index, (short)value);
    }

    public abstract CharBuffer asCharBuffer();

    public short getShort() {
        return getShort(nextGetIndex(2));
    }

    public ByteBufferImpl putShort(short value) {
        return putShort(nextPutIndex(2), value);
    }

    public short getShort(int index) {
        return scopedGetShort(applyOffset(index), bigEndian);
    }

    public ByteBufferImpl putShort(int index, short value) {
        return scopedPutShort(applyOffset(index), value, bigEndian);
    }

    public abstract ShortBuffer asShortBuffer();

    public int getInt() {
        return getInt(nextGetIndex(4));
    }

    public ByteBufferImpl putInt(int value) {
        return putInt(nextPutIndex(4), value);
    }

    public int getInt(int index) {
        return scopedGetInt(applyOffset(index), bigEndian);
    }

    public ByteBufferImpl putInt(int index, int value) {
        return scopedPutInt(applyOffset(index), value, bigEndian);
    }

    public abstract IntBuffer asIntBuffer();

    public long getLong() {
        return getLong(nextPutIndex(8));
    }

    public ByteBufferImpl putLong(long value) {
        return putLong(nextPutIndex(8), value);
    }

    public long getLong(int index) {
        return scopedGetLong(applyOffset(index), bigEndian);
    }

    public ByteBufferImpl putLong(int index, long value) {
        return scopedPutLong(applyOffset(index), value, bigEndian);
    }

    public abstract LongBuffer asLongBuffer();

    public float getFloat() {
        return Float.intBitsToFloat(getInt());
    }

    public ByteBufferImpl putFloat(float value) {
        return putInt(Float.floatToRawIntBits(value));
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(getInt(index));
    }

    public ByteBufferImpl putFloat(int index, float value) {
        return putInt(index, Float.floatToRawIntBits(value));
    }

    public abstract FloatBuffer asFloatBuffer();

    public double getDouble() {
        return Double.longBitsToDouble(getLong());
    }

    public ByteBufferImpl putDouble(double value) {
        return putLong(Double.doubleToRawLongBits(value));
    }

    public double getDouble(int index) {
        return Double.longBitsToDouble(getLong(index));
    }

    public ByteBufferImpl putDouble(int index, double value) {
        return putLong(index, Double.doubleToRawLongBits(value));
    }

    public abstract DoubleBuffer asDoubleBuffer();
}
