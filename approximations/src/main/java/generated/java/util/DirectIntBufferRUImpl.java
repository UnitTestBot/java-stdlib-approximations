package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import sun.nio.ch.DirectBuffer;

import java.nio.*;

@Approximate(stub.java.util.DirectIntBufferRU.class)
public class DirectIntBufferRUImpl extends DirectIntBufferUImpl implements DirectBuffer {
    DirectIntBufferRUImpl(DirectBuffer db, int mark, int pos, int lim, int cap, int off, int[] segment) {
        super(db, mark, pos, lim, cap, off, segment);
        this.isReadOnly = true;
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
        return new DirectIntBufferRUImpl(this, -1, 0, rem, rem, off, storage);
    }

    @Override
    public IntBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new DirectIntBufferRUImpl(this, -1, 0, length, length, index,
                storage);
    }

    public IntBufferImpl duplicate() {
        return new DirectIntBufferRUImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public IntBufferImpl asReadOnlyBuffer() {
        return duplicate();
    }

    public IntBufferImpl put(int x) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int i, int x) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(IntBufferImpl src) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int index, IntBufferImpl src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int index, int[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl compact() {
        throw new ReadOnlyBufferException();
    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }

    public ByteOrder order() {
        return ((ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
                ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
    }
}
