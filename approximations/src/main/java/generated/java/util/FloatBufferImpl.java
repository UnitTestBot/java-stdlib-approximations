package generated.java.util;

import runtime.LibSLRuntime;

import java.nio.*;

public abstract class FloatBufferImpl extends BufferImpl implements Comparable<FloatBufferImpl> {
    final float[] storage;
    boolean isReadOnly;

    FloatBufferImpl(int mark, int pos, int lim, int cap, float[] hb, int offset) {
        super(mark, pos, lim, cap);
        this.storage = hb;
        this.offset = offset;
    }

    FloatBufferImpl(int mark, int pos, int lim, int cap, float[] segment) {
        this(mark, pos, lim, cap, segment, 0);
    }

    FloatBufferImpl(float[] hb, int cap) {
        super(cap);
        this.storage = hb;
        this.offset = 0;
    }

    @Override
    Object base() {
        return storage;
    }

    public static FloatBufferImpl allocate(int capacity) {
        /*if (capacity < 0)
            throw createCapacityException(capacity);
        return new HeapFloatBuffer(capacity, capacity, null);*/
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static FloatBufferImpl wrap(float[] array, int offset, int length) {
        /*try {
            return new HeapFloatBuffer(array, offset, length, null);
        } catch (IllegalArgumentException x) {
            throw new IndexOutOfBoundsException();
        }*/
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static FloatBufferImpl wrap(float[] array) {
        return wrap(array, 0, array.length);
    }

    @Override
    public abstract FloatBufferImpl slice();

    @Override
    public abstract FloatBufferImpl slice(int index, int length);

    @Override
    public abstract FloatBufferImpl duplicate();

    public abstract FloatBufferImpl asReadOnlyBuffer();

    public float get() {
        return storage[applyOffset(nextGetIndex())];
    }

    public FloatBufferImpl put(float f) {
        storage[applyOffset(nextPutIndex())] = f;
        return this;
    }

    public float get(int index) {
        return storage[applyOffset(checkIndex(index))];
    }

    public FloatBufferImpl put(int index, float f) {
        storage[applyOffset(checkIndex(index))] = f;
        return this;
    }

    public FloatBufferImpl get(float[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        if (length > remaining())
            throw new BufferUnderflowException();
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(nextGetIndex(length)), dst, offset, length);
        return this;
    }

    public FloatBufferImpl get(float[] dst) {
        return get(dst, 0, dst.length);
    }

    public FloatBufferImpl get(int index, float[] dst, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, dst.length);
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(index), dst, offset, length);
        return this;
    }

    public FloatBufferImpl get(int index, float[] dst) {
        return get(index, dst, 0, dst.length);
    }

    public FloatBufferImpl put(FloatBufferImpl src) {
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

    public FloatBufferImpl put(int index, FloatBufferImpl src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.limit());
        if (isReadOnly())
            throw new ReadOnlyBufferException();

        putBuffer(index, src, offset, length);

        return this;
    }

    void putBuffer(int pos, FloatBufferImpl src, int srcPos, int n) {
        Object srcBase = src.base();
        assert srcBase != null || src.isDirect();
        Object base = base();
        assert base != null || isDirect();
        LibSLRuntime.ArrayActions.copy(src.storage, src.applyOffset(srcPos), storage, applyOffset(pos), n);
    }

    public FloatBufferImpl put(float[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        if (length > remaining())
            throw new BufferOverflowException();
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(nextPutIndex(length)), length);
        return this;
    }

    public final FloatBufferImpl put(float[] src) {
        return put(src, 0, src.length);
    }

    public FloatBufferImpl put(int index, float[] src, int offset, int length) {
        if (isReadOnly())
            throw new ReadOnlyBufferException();
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(index), length);
        return this;
    }

    public FloatBufferImpl put(int index, float[] src) {
        return put(index, src, 0, src.length);
    }

    public final boolean hasArray() {
        return (storage != null) && !isReadOnly;
    }

    public final float[] array() {
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
    public final FloatBufferImpl position(int newPosition) {
        super.position(newPosition);
        return this;
    }

    @Override
    public final FloatBufferImpl limit(int newLimit) {
        super.limit(newLimit);
        return this;
    }

    @Override
    public final FloatBufferImpl mark() {
        super.mark();
        return this;
    }

    @Override
    public final FloatBufferImpl reset() {
        super.reset();
        return this;
    }

    @Override
    public final FloatBufferImpl clear() {
        super.clear();
        return this;
    }

    @Override
    public final FloatBufferImpl flip() {
        super.flip();
        return this;
    }

    @Override
    public final FloatBufferImpl rewind() {
        super.rewind();
        return this;
    }

    public FloatBufferImpl compact() {
        int pos = position();
        int lim = limit();
        assert (pos <= lim);
        int rem = lim - pos;
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(pos), storage, applyOffset(0), rem);
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

    private int mismatch(FloatBufferImpl a, int aOff, FloatBufferImpl b, int bOff, int length) {
        for (int i = 0; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (!(ob instanceof FloatBuffer))
            return false;
        FloatBufferImpl that = (FloatBufferImpl)ob;
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        if (thisRem < 0 || thisRem != thatRem)
            return false;
        return mismatch(this, thisPos, that, thatPos, thisRem) < 0;
    }

    public int compareTo(FloatBufferImpl that) {
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        int length = thisRem < thatRem ? thisRem : thatRem;
        if (length < 0)
            return -1;
        int i = mismatch(this, thisPos, that, thatPos, length);
        if (i >= 0) {
            return compare(this.get(thisPos + i), that.get(thatPos + i));
        }
        return thisRem - thatRem;
    }

    private static int compare(float x, float y) {
        return ((x < y)  ? -1 :
                (x > y)  ? +1 :
                        (x == y) ?  0 :
                                Float.isNaN(x) ? (Float.isNaN(y) ? 0 : +1) : -1);
    }

    public int mismatch(FloatBufferImpl that) {
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        int length = thisRem < thatRem ? thisRem : thatRem;
        if (length < 0)
            return -1;
        int r = mismatch(this, thisPos, that, thatPos, length);
        return (r == -1 && thisRem != thatRem) ? length : r;
    }

    public abstract ByteOrder order();
}
