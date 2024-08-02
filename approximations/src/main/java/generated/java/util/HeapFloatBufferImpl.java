package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import runtime.LibSLRuntime;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteOrder;

@Approximate(stub.java.util.HeapFloatBuffer.class)
public class HeapFloatBufferImpl extends FloatBufferImpl {
    HeapFloatBufferImpl(int cap, int lim) {
        super(-1, 0, lim, cap, new float[cap], 0);
    }

    HeapFloatBufferImpl(float[] buf, int off, int len) {
        super(-1, off, off + len, buf.length, buf, 0);
    }

    protected HeapFloatBufferImpl(float[] buf, int mark, int pos, int lim, int cap, int off) {
        super(mark, pos, lim, cap, buf, off);
    }

    public FloatBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapFloatBufferImpl(storage, -1, 0, rem, rem, pos + offset);
    }

    @Override
    public FloatBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new HeapFloatBufferImpl(storage, -1, 0, length, length, index + offset);
    }

    public FloatBufferImpl duplicate() {
        return new HeapFloatBufferImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(), offset);
    }

    public FloatBufferImpl asReadOnlyBuffer() {
        /*return new HeapFloatBufferRImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(),
                offset);*/
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public float get() {
        return super.get();
    }

    public float get(int i) {
        return super.get(i);
    }

    public FloatBufferImpl get(float[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferUnderflowException();
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(pos), dst, offset, length);
        position(pos + length);
        return this;
    }

    public FloatBufferImpl get(int index, float[] dst, int offset, int length) {
        return super.get(index, dst, offset, length);
    }

    public boolean isDirect() {
        return false;
    }

    public boolean isReadOnly() {
        return false;
    }

    public FloatBufferImpl put(float x) {
        return super.put(x);
    }

    public FloatBufferImpl put(int i, float x) {
        return super.put(i, x);
    }

    public FloatBufferImpl put(float[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(pos), length);
        position(pos + length);
        return this;
    }

    public FloatBufferImpl put(FloatBufferImpl src) {
        super.put(src);
        return this;
    }

    public FloatBufferImpl put(int index, FloatBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public FloatBufferImpl put(int index, float[] src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(index), length);
        return this;
    }

    public FloatBufferImpl compact() {
        return super.compact();
    }

    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
}
