package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import org.usvm.api.Engine;
import runtime.LibSLRuntime;

import java.nio.*;

@Approximate(stub.java.util.HeapIntBuffer.class)
public class HeapIntBufferImpl extends IntBufferImpl {
    HeapIntBufferImpl(int cap, int lim) {
        super(-1, 0, lim, cap, new int[cap], 0);
    }

    HeapIntBufferImpl(int[] buf, int off, int len) {
        super(-1, off, off + len, buf.length, buf, 0);
    }

    protected HeapIntBufferImpl(int[] buf, int mark, int pos, int lim, int cap, int off) {
        super(mark, pos, lim, cap, buf, off);
    }

    public IntBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapIntBufferImpl(storage, -1, 0, rem, rem, pos + offset);
    }

    @Override
    public IntBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new HeapIntBufferImpl(storage, -1, 0, length, length, index + offset);
    }

    public IntBufferImpl duplicate() {
        return new HeapIntBufferImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(), offset);
    }

    public IntBufferImpl asReadOnlyBuffer() {
        return new HeapIntBufferRImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(),
                offset);
    }

    public int get() {
        return super.get();
    }

    public int get(int i) {
        return super.get(i);
    }

    public IntBufferImpl get(int[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferUnderflowException();
        int indexWithOffset = applyOffset(pos);
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(pos), dst, offset, length);
        position(pos + length);
        return this;
    }

    public IntBufferImpl get(int index, int[] dst, int offset, int length) {
        return super.get(index, dst, offset, length);
    }

    public boolean isDirect() {
        return false;
    }

    public boolean isReadOnly() {
        return false;
    }

    public IntBufferImpl put(int x) {
        return super.put(x);
    }

    public IntBufferImpl put(int i, int x) {
        return super.put(i, x);
    }

    public IntBufferImpl put(int[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();
        int indexWithOffset = applyOffset(pos);
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, indexWithOffset, length);
        position(pos + length);
        return this;
    }

    public IntBufferImpl put(IntBufferImpl src) {
        super.put(src);
        return this;
    }

    public IntBufferImpl put(int index, IntBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public IntBufferImpl put(int index, int[] src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        int indexWithOffset = applyOffset(index);
        Engine.assume(indexWithOffset + length < storage.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(index), length);
        return this;
    }

    public IntBufferImpl compact() {
        return super.compact();
    }

    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
}
