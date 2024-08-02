package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import runtime.LibSLRuntime;

import java.nio.*;

@Approximate(stub.java.util.HeapCharBuffer.class)
public class HeapCharBufferImpl extends CharBufferImpl {
    HeapCharBufferImpl(int cap, int lim) {
        super(-1, 0, lim, cap, new char[cap], 0);
    }

    HeapCharBufferImpl(char[] buf, int off, int len) {
        super(-1, off, off + len, buf.length, buf, 0);
    }

    protected HeapCharBufferImpl(char[] buf, int mark, int pos, int lim, int cap, int off) {
        super(mark, pos, lim, cap, buf, off);
    }

    public CharBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapCharBufferImpl(storage, -1, 0, rem, rem, pos + offset);
    }

    @Override
    public CharBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new HeapCharBufferImpl(storage, -1, 0, length, length, index + offset);
    }

    public CharBufferImpl duplicate() {
        return new HeapCharBufferImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(), offset);
    }

    public CharBufferImpl asReadOnlyBuffer() {
        //return new HeapCharBufferR(storage, this.markValue(), this.position(), this.limit(), this.capacity(), offset);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public char get() {
        return super.get();
    }

    public char get(int i) {
        return super.get(i);
    }

    char getUnchecked(int i) {
        return super.getUnchecked(i);
    }

    public CharBufferImpl get(char[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferUnderflowException();
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(pos), dst, offset, length);
        position(pos + length);
        return this;
    }

    public CharBufferImpl get(int index, char[] dst, int offset, int length) {
        return super.get(index, dst, offset, length);
    }

    public boolean isDirect() {
        return false;
    }

    public boolean isReadOnly() {
        return false;
    }

    public CharBufferImpl put(char x) {
        return super.put(x);
    }

    public CharBufferImpl put(int i, char x) {
        return super.put(i, x);
    }

    public CharBufferImpl put(char[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(pos), length);
        position(pos + length);
        return this;
    }

    public CharBufferImpl put(CharBufferImpl src) {
        super.put(src);
        return this;
    }

    public CharBufferImpl put(int index, CharBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public CharBufferImpl put(int index, char[] src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(index), length);
        return this;
    }

    public CharBufferImpl put(String src, int start, int end) {
        /*int length = end - start;
        checkFromIndexSize(start, length, src.length());
        if (isReadOnly())
            throw new ReadOnlyBufferException();
        int pos = position();
        int lim = limit();
        int rem = (pos <= lim) ? lim - pos : 0;
        if (length > rem)
            throw new BufferOverflowException();
        src.getChars(start, end, storage, ix(pos));
        position(pos + length);
        return this;*/
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CharBufferImpl compact() {
        return super.compact();
    }

    String toString(int start, int end) {
        /*try {
            return new String(storage, start + offset, end - start);
        } catch (StringIndexOutOfBoundsException x) {
            throw new IndexOutOfBoundsException();
        }*/
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CharBufferImpl subSequence(int start, int end) {
        int pos = position();
        checkFromToIndex(start, end, limit() - pos);
        return new HeapCharBufferImpl(storage, -1, pos + start, pos + end, capacity(), offset);
    }

    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }

    ByteOrder charRegionOrder() {
        return order();
    }
}
