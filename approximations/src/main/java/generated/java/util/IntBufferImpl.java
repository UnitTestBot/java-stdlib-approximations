package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import runtime.LibSLRuntime;

import java.nio.*;

@Approximate(java.nio.IntBuffer.class)
public abstract class IntBufferImpl extends BufferImpl implements Comparable<IntBufferImpl> {
    final int[] storage;
    int offset;
    boolean isReadOnly;

    IntBufferImpl(int mark, int pos, int lim, int cap, int[] hb, int offset) {
        super(mark, pos, lim, cap);
        this.storage = hb;
        this.offset = offset;
    }

    IntBufferImpl(int mark, int pos, int lim, int cap, int[] segment) {
        this(mark, pos, lim, cap, segment, 0);
    }

    IntBufferImpl(int[] hb, int cap) {
        super(cap);
        this.storage = hb;
        this.offset = 0;
    }

    @Override
    Object base() {
        return storage;
    }

    public static IntBufferImpl allocate(int capacity) {
        /*if (capacity < 0)
            throw createCapacityException(capacity);
        return new HeapIntBuffer(capacity, capacity, null);*/
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static IntBufferImpl wrap(int[] array, int offset, int length) {
        /*try {
            return new HeapIntBuffer(array, offset, length, null);
        } catch (IllegalArgumentException x) {
            throw new IndexOutOfBoundsException();
        }*/
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static IntBufferImpl wrap(int[] array) {
        return wrap(array, 0, array.length);
    }

    @Override
    public abstract IntBufferImpl slice();

    @Override
    public abstract IntBufferImpl slice(int index, int length);

    @Override
    public abstract IntBufferImpl duplicate();

    public abstract IntBufferImpl asReadOnlyBuffer();

    public int get() {
        return storage[applyOffset(nextGetIndex())];
    }

    public IntBufferImpl put(int i) {
        storage[applyOffset(nextPutIndex())] = i;
        return this;
    }

    public int get(int index) {
        return storage[applyOffset(checkIndex(index))];
    }

    public IntBufferImpl put(int index, int i) {
        storage[applyOffset(checkIndex(index))] = i;
        return this;
    }

    public IntBufferImpl get(int[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        if (length > remaining())
            throw new BufferUnderflowException();
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(nextGetIndex(length)), dst, offset, length);
        return this;
    }

    public IntBufferImpl get(int[] dst) {
        return get(dst, 0, dst.length);
    }

    public IntBufferImpl get(int index, int[] dst, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, dst.length);
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(index), dst, offset, length);
        return this;
    }

    public IntBufferImpl get(int index, int[] dst) {
        return get(index, dst, 0, dst.length);
    }

    public IntBufferImpl put(IntBufferImpl src) {
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

    public IntBufferImpl put(int index, IntBufferImpl src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.limit());
        if (isReadOnly())
            throw new ReadOnlyBufferException();

        putBuffer(index, src, offset, length);

        return this;
    }

    void putBuffer(int pos, IntBufferImpl src, int srcPos, int n) {
        Object srcBase = src.base();
        assert srcBase != null || src.isDirect();
        Object base = base();
        assert base != null || isDirect();
        LibSLRuntime.ArrayActions.copy(src.storage, src.applyOffset(srcPos), storage, applyOffset(pos), n);
    }

    public IntBufferImpl put(int[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        if (length > remaining())
            throw new BufferOverflowException();
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(nextPutIndex(length)), length);
        return this;
    }

    public final IntBufferImpl put(int[] src) {
        return put(src, 0, src.length);
    }

    public IntBufferImpl put(int index, int[] src, int offset, int length) {
        if (isReadOnly())
            throw new ReadOnlyBufferException();
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(index), length);
        return this;
    }

    public IntBufferImpl put(int index, int[] src) {
        return put(index, src, 0, src.length);
    }

    public final boolean hasArray() {
        return (storage != null) && !isReadOnly;
    }

    public final int[] array() {
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
    public final IntBufferImpl position(int newPosition) {
        super.position(newPosition);
        return this;
    }

    @Override
    public final IntBufferImpl limit(int newLimit) {
        super.limit(newLimit);
        return this;
    }

    @Override
    public final IntBufferImpl mark() {
        super.mark();
        return this;
    }

    @Override
    public final IntBufferImpl reset() {
        super.reset();
        return this;
    }

    @Override
    public final IntBufferImpl clear() {
        super.clear();
        return this;
    }

    @Override
    public final IntBufferImpl flip() {
        super.flip();
        return this;
    }

    @Override
    public final IntBufferImpl rewind() {
        super.rewind();
        return this;
    }

    public IntBufferImpl compact() {
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
            h = 31 * h + get(i);
        return h;
    }

    private int mismatch(IntBufferImpl a, int aOff, IntBufferImpl b, int bOff, int length) {
        for (int i = 0; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (!(ob instanceof IntBuffer))
            return false;
        IntBufferImpl that = (IntBufferImpl)ob;
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        if (thisRem < 0 || thisRem != thatRem)
            return false;
        return mismatch(this, thisPos, that, thatPos, thisRem) < 0;
    }

    public int compareTo(IntBufferImpl that) {
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

    private static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public int mismatch(IntBufferImpl that) {
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
