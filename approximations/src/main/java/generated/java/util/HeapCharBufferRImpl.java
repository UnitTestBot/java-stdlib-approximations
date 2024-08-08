package generated.java.util;

import jdk.internal.access.foreign.MemorySegmentProxy;
import org.jacodb.approximation.annotation.Approximate;

import java.nio.*;
import java.util.Objects;

@Approximate(stub.java.util.HeapCharBufferR.class)
public class HeapCharBufferRImpl extends HeapCharBufferImpl {
    HeapCharBufferRImpl(int cap, int lim) {
        super(cap, lim);
        this.isReadOnly = true;
    }

    HeapCharBufferRImpl(char[] buf, int off, int len) {
        super(buf, off, len);
        this.isReadOnly = true;
    }

    protected HeapCharBufferRImpl(char[] buf, int mark, int pos, int lim, int cap, int off) {
        super(buf, mark, pos, lim, cap, off);
        this.isReadOnly = true;
    }

    public CharBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapCharBufferRImpl(storage, -1, 0, rem, rem, pos + offset);
    }

    @Override
    public CharBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new HeapCharBufferRImpl(storage, -1, 0, length, length, index + offset);
    }

    public CharBufferImpl duplicate() {
        return new HeapCharBufferRImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(),
                offset);
    }

    public CharBufferImpl asReadOnlyBuffer() {
        return duplicate();
    }

    public boolean isReadOnly() {
        return true;
    }

    public CharBufferImpl put(char x) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(int i, char x) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(char[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(CharBufferImpl src) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(int index, CharBufferImpl src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(int index, char[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public CharBufferImpl put(String src, int start, int end) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CharBufferImpl compact() {
        throw new ReadOnlyBufferException();
    }

    String toString(int start, int end) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public CharBufferImpl subSequence(int start, int end) {
        int pos = position();
        checkFromToIndex(start, end, limit() - pos);
        return new HeapCharBufferRImpl(storage, -1, pos + start, pos + end, capacity(), offset);
    }

    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }

    ByteOrder charRegionOrder() {
        return order();
    }
}
