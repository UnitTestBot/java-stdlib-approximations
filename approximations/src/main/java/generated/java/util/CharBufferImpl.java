package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import org.usvm.api.Engine;
import runtime.LibSLRuntime;

import java.io.IOException;
import java.nio.*;
import java.util.stream.IntStream;

@Approximate(java.nio.CharBuffer.class)
public abstract class CharBufferImpl
        extends BufferImpl
        implements Comparable<CharBufferImpl>, Appendable, CharSequence, Readable {
    final char[] storage;
    boolean isReadOnly;

    CharBufferImpl(int mark, int pos, int lim, int cap, char[] hb, int offset)
    {
        super(mark, pos, lim, cap);
        this.storage = hb;
        this.offset = offset;
    }

    CharBufferImpl(int mark, int pos, int lim, int cap, char[] segment) {
        this(mark, pos, lim, cap, segment, 0);
    }

    CharBufferImpl(char[] hb, int cap) {
        super(cap);
        this.storage = hb;
        this.offset = 0;
    }

    @Override
    Object base() {
        return storage;
    }

    public static CharBufferImpl allocate(int capacity) {
        if (capacity < 0)
            throw createCapacityException(capacity);
        return new HeapCharBufferImpl(capacity, capacity);
    }

    public static CharBufferImpl wrap(char[] array,
                                  int offset, int length)
    {
        try {
            return new HeapCharBufferImpl(array, offset, length);
        } catch (IllegalArgumentException x) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static CharBufferImpl wrap(char[] array) {
        return wrap(array, 0, array.length);
    }

    public int read(CharBufferImpl target) throws IOException {
        int limit = limit();
        int pos = position();
        int remaining = limit - pos;
        assert remaining >= 0;
        if (remaining <= 0)
            return -1;

        int targetRemaining = target.remaining();
        assert targetRemaining >= 0;
        if (targetRemaining <= 0)
            return 0;

        int n = remaining < targetRemaining ? remaining : targetRemaining;

        if (targetRemaining < remaining)
            limit(pos + n);
        try {
            if (n > 0)
                target.put(this);
        } finally {
            limit(limit);
        }
        return n;
    }

    public static CharBufferImpl wrap(CharSequence csq, int start, int end) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static CharBufferImpl wrap(CharSequence csq) {
        return wrap(csq, 0, csq.length());
    }

    @Override
    public abstract CharBufferImpl slice();

    @Override
    public abstract CharBufferImpl slice(int index, int length);

    @Override
    public abstract CharBufferImpl duplicate();

    public abstract CharBufferImpl asReadOnlyBuffer();

    public char get() {
        int indexWithOffset = applyOffset(nextGetIndex());
        Engine.assume(indexWithOffset < storage.length);
        return storage[indexWithOffset];
    }

    public CharBufferImpl put(char c) {
        int indexWithOffset = applyOffset(nextPutIndex());
        Engine.assume(indexWithOffset < storage.length);
        storage[indexWithOffset] = c;
        return this;
    }

    public char get(int index) {
        int indexWithOffset = applyOffset(checkIndex(index));
        Engine.assume(indexWithOffset < storage.length);
        return storage[indexWithOffset];
    }

    char getUnchecked(int index) {
        return storage[applyOffset(index)];
    }

    public CharBufferImpl put(int index, char c) {
        int indexWithOffset = applyOffset(checkIndex(index));
        Engine.assume(indexWithOffset < storage.length);
        storage[indexWithOffset] = c;
        return this;
    }

    public CharBufferImpl get(char[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        if (length > remaining())
            throw new BufferUnderflowException();
        int indexWithOffset = applyOffset(nextGetIndex(length));
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(storage, indexWithOffset, dst, offset, length);
        return this;
    }

    public CharBufferImpl get(char[] dst) {
        return get(dst, 0, dst.length);
    }

    public CharBufferImpl get(int index, char[] dst, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, dst.length);
        int indexWithOffset = applyOffset(nextGetIndex(length));
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(storage, indexWithOffset, dst, offset, length);
        return this;
    }

    public CharBufferImpl get(int index, char[] dst) {
        return get(index, dst, 0, dst.length);
    }

    public CharBufferImpl put(CharBufferImpl src) {
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

    public CharBufferImpl put(int index, CharBufferImpl src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.limit());
        if (isReadOnly())
            throw new ReadOnlyBufferException();

        putBuffer(index, src, offset, length);

        return this;
    }

    void putBuffer(int pos, CharBufferImpl src, int srcPos, int n) {
        if (src.isAddressable()) {
            Object base = base();
            assert base != null || isDirect();
            int indexWithOffset = applyOffset(pos);
            int srcIndexWithOffset = src.applyOffset(srcPos);
            Engine.assume(indexWithOffset + n < storage.length);
            Engine.assume(srcIndexWithOffset + n < src.storage.length);
            LibSLRuntime.ArrayActions.copy(src.storage, srcIndexWithOffset, storage, indexWithOffset, n);
        } else {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public CharBufferImpl put(char[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        if (length > remaining())
            throw new BufferOverflowException();
        int indexWithOffset = applyOffset(nextPutIndex(length));
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, indexWithOffset, length);
        return this;
    }

    public final CharBufferImpl put(char[] src) {
        return put(src, 0, src.length);
    }

    public CharBufferImpl put(int index, char[] src, int offset, int length) {
        if (isReadOnly())
            throw new ReadOnlyBufferException();
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        int indexWithOffset = applyOffset(index);
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, indexWithOffset, length);
        return this;
    }

    public CharBufferImpl put(int index, char[] src) {
        return put(index, src, 0, src.length);
    }

    public CharBufferImpl put(String src, int start, int end) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public final CharBufferImpl put(String src) {
        return put(src, 0, src.length());
    }

    public final boolean hasArray() {
        return (storage != null) && !isReadOnly;
    }

    public final char[] array() {
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
    public final CharBufferImpl position(int newPosition) {
        super.position(newPosition);
        return this;
    }

    @Override
    public final CharBufferImpl limit(int newLimit) {
        super.limit(newLimit);
        return this;
    }

    @Override
    public final CharBufferImpl mark() {
        super.mark();
        return this;
    }

    @Override
    public final CharBufferImpl reset() {
        super.reset();
        return this;
    }

    @Override
    public final CharBufferImpl clear() {
        super.clear();
        return this;
    }

    @Override
    public final CharBufferImpl flip() {
        super.flip();
        return this;
    }

    @Override
    public final CharBufferImpl rewind() {
        super.rewind();
        return this;
    }

    public CharBufferImpl compact() {
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

    boolean isAddressable() {
        return true;
    }

    public int hashCode() {
        int h = 1;
        int p = position();
        for (int i = limit() - 1; i >= p; i--)
            h = 31 * h + (int)get(i);
        return h;
    }

    private int mismatch(CharBufferImpl a, int aOff, CharBufferImpl b, int bOff, int length) {
        for (int i = 0; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (!(ob instanceof CharBufferImpl))
            return false;
        CharBufferImpl that = (CharBufferImpl)ob;
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        if (thisRem < 0 || thisRem != thatRem)
            return false;
        return mismatch(this, thisPos, that, thatPos, thisRem) < 0;
    }

    public int compareTo(CharBufferImpl that) {
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

    private static int compare(char x, char y) { return x - y; }

    public int mismatch(CharBufferImpl that) {
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

    public String toString() {
        return toString(position(), limit());
    }

    abstract String toString(int start, int end);

    public final int length() {
        return remaining();
    }

    public final boolean isEmpty() {
        return remaining() == 0;
    }

    public final char charAt(int index) {
        return get(position() + checkIndex(index, 1));
    }

    public abstract CharBufferImpl subSequence(int start, int end);

    public CharBufferImpl append(CharSequence csq) {
        if (csq == null)
            return put("null");
        else
            return put(csq.toString());
    }

    public CharBufferImpl append(CharSequence csq, int start, int end) {
        CharSequence cs = (csq == null ? "null" : csq);
        return put(cs.subSequence(start, end).toString());
    }

    public CharBufferImpl append(char c) {
        return put(c);
    }

    public abstract ByteOrder order();

    abstract ByteOrder charRegionOrder();

    @Override
    public IntStream chars() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
