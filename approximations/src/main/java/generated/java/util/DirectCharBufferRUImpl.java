package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import stub.java.util.DirectCharBufferRU;
import sun.nio.ch.DirectBuffer;

import java.nio.ReadOnlyBufferException;

@Approximate(DirectCharBufferRU.class)
public class DirectCharBufferRUImpl extends DirectCharBufferUImpl implements DirectBuffer {
    DirectCharBufferRUImpl(DirectBuffer db, int mark, int pos, int lim, int cap, int off, char[] segment) {
        super(db, mark, pos, lim, cap, off, segment);
        this.isReadOnly = true;
    }

    public CharBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 1);
        assert (off >= 0);
        return new DirectCharBufferRUImpl(this, -1, 0, rem, rem, off, storage);
    }

    @Override
    public CharBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new DirectCharBufferRUImpl(this, -1, 0, length, length, index << 1, storage);
    }

    public CharBufferImpl duplicate() {
        return new DirectCharBufferRUImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public CharBufferImpl asReadOnlyBuffer() {
        return duplicate();
    }

    public CharBufferImpl put(char x) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(int i, char x) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(CharBufferImpl src) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(int index, CharBufferImpl src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(char[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(int index, char[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl compact() {
        throw new ReadOnlyBufferException();
    }

    public boolean isReadOnly() {
        return true;
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
        return new DirectCharBufferRUImpl(this, -1, pos + start, pos + end, capacity(),
                offset, storage);
    }
}
