package generated.java.util;

import jdk.internal.ref.Cleaner;
import org.jacodb.approximation.annotation.Approximate;
import sun.nio.ch.DirectBuffer;

import java.nio.*;

@Approximate(stub.java.util.DirectCharBufferU.class)
public class DirectCharBufferUImpl extends CharBufferImpl implements DirectBuffer {
    private final Object att;

    DirectCharBufferUImpl(DirectBuffer db, int mark, int pos, int lim, int cap, int off, char[] segment) {
        super(mark, pos, lim, cap, segment);
        offset = off;
        Object attachment = db.attachment();
        att = (attachment == null ? db : attachment);
    }

    public Object attachment() {
        return att;
    }

    public Cleaner cleaner() { return null; }

    @Override
    Object base() {
        return null;
    }

    public CharBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = pos;
        assert (off >= 0);
        return new DirectCharBufferUImpl(this, -1, 0, rem, rem, off, storage);
    }

    @Override
    public CharBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new DirectCharBufferUImpl(this, -1, 0, length, length, index, storage);
    }

    public CharBufferImpl duplicate() {
        return new DirectCharBufferUImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public CharBufferImpl asReadOnlyBuffer() {
        return new DirectCharBufferRUImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public long address() {
        throw new UnsupportedOperationException();
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
        super.get(dst, offset, length);
        return this;
    }

    public CharBufferImpl get(int index, char[] dst, int offset, int length) {
        super.get(index, dst, offset, length);
        return this;
    }

    public CharBufferImpl put(char x) {
        return super.put(x);
    }

    public CharBufferImpl put(int i, char x) {
        return super.put(i, x);
    }

    public CharBufferImpl put(CharBufferImpl src) {
        super.put(src);
        return this;
    }

    public CharBufferImpl put(int index, CharBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public CharBufferImpl put(char[] src, int offset, int length) {
        super.put(src, offset, length);
        return this;
    }

    public CharBufferImpl put(int index, char[] src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public CharBufferImpl compact() {
        return super.compact();
    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return false;
    }

    public String toString(int start, int end) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CharBufferImpl subSequence(int start, int end) {
        int pos = position();
        int lim = limit();
        assert (pos <= lim);
        int len = lim - pos;
        checkFromToIndex(start, end, len);
        return new DirectCharBufferUImpl(this, -1, pos + start, pos + end, capacity(),
                offset, storage);
    }

    public ByteOrder order() {
        return ((ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
                ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
    }

    ByteOrder charRegionOrder() {
        return order();
    }
}
