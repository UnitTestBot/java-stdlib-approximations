package generated.java.util;

import jdk.internal.ref.Cleaner;
import org.jacodb.approximation.annotation.Approximate;
import stub.java.util.DirectIntBufferU;
import sun.nio.ch.DirectBuffer;

import java.nio.*;

@Approximate(DirectIntBufferU.class)
public class DirectIntBufferUImpl extends IntBufferImpl implements DirectBuffer {
    private final Object att;

    public Object attachment() {
        return att;
    }

    public Cleaner cleaner() { return null; }

    DirectIntBufferUImpl(DirectBuffer db, int mark, int pos, int lim, int cap, int off, int[] segment) {
        super(mark, pos, lim, cap, segment);
        offset = off;
        Object attachment = db.attachment();
        att = (attachment == null ? db : attachment);
    }

    @Override
    Object base() {
        return null;
    }

    public IntBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = pos;
        assert (off >= 0);
        return new DirectIntBufferUImpl(this, -1, 0, rem, rem, off, storage);
    }

    @Override
    public IntBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new DirectIntBufferUImpl(this, -1, 0, length, length, index, storage);
    }

    public IntBufferImpl duplicate() {
        return new DirectIntBufferUImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public IntBufferImpl asReadOnlyBuffer() {
        return new DirectIntBufferRUImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public long address() {
        throw new UnsupportedOperationException();
    }

    public int get() {
        return super.get();
    }

    public int get(int i) {
        return super.get(i);
    }

    public IntBufferImpl get(int[] dst, int offset, int length) {
        super.get(dst, offset, length);
        return this;
    }

    public IntBufferImpl get(int index, int[] dst, int offset, int length) {
        super.get(index, dst, offset, length);
        return this;
    }

    public IntBufferImpl put(int x) {
        return super.put(x);
    }

    public IntBufferImpl put(int i, int x) {
        return super.put(i, x);
    }

    public IntBufferImpl put(IntBufferImpl src) {
        super.put(src);
        return this;
    }

    public IntBufferImpl put(int index, IntBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public IntBufferImpl put(int[] src, int offset, int length) {
        super.put(src, offset, length);
        return this;
    }

    public IntBufferImpl put(int index, int[] src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public IntBufferImpl compact() {
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
