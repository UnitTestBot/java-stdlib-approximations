package generated.java.util;

import jdk.internal.ref.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteOrder;

public class DirectFloatBufferUImpl extends FloatBufferImpl implements DirectBuffer {
    private final Object att;

    public Object attachment() {
        return att;
    }

    public Cleaner cleaner() { return null; }

    DirectFloatBufferUImpl(DirectBuffer db, int mark, int pos, int lim, int cap, int off, float[] segment) {
        super(mark, pos, lim, cap, segment);
        offset = off;
        Object attachment = db.attachment();
        att = (attachment == null ? db : attachment);
    }

    @Override
    Object base() {
        return null;
    }

    public FloatBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = pos;
        assert (off >= 0);
        return new DirectFloatBufferUImpl(this, -1, 0, rem, rem, off, storage);
    }

    @Override
    public FloatBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new DirectFloatBufferUImpl(this, -1, 0, length, length, index, storage);
    }

    public FloatBufferImpl duplicate() {
        return new DirectFloatBufferUImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public FloatBufferImpl asReadOnlyBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long address() {
        throw new UnsupportedOperationException();
    }

    public float get() {
        return super.get();
    }

    public float get(int i) {
        return super.get(i);
    }

    public FloatBufferImpl get(float[] dst, int offset, int length) {
        super.get(dst, offset, length);
        return this;
    }

    public FloatBufferImpl get(int index, float[] dst, int offset, int length) {
        super.get(index, dst, offset, length);
        return this;
    }

    public FloatBufferImpl put(float x) {
        return super.put(x);
    }

    public FloatBufferImpl put(int i, float x) {
        return super.put(i, x);
    }

    public FloatBufferImpl put(FloatBufferImpl src) {
        super.put(src);
        return this;
    }

    public FloatBufferImpl put(int index, FloatBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public FloatBufferImpl put(float[] src, int offset, int length) {
        super.put(src, offset, length);
        return this;
    }

    public FloatBufferImpl put(int index, float[] src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public FloatBufferImpl compact() {
        return super.compact();
    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return false;
    }

    public ByteOrder order() {
        return ((ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
                ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
    }
}
