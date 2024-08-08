package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;

import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

@Approximate(stub.java.util.HeapIntBufferR.class)
public class HeapIntBufferRImpl extends HeapIntBufferImpl {
    HeapIntBufferRImpl(int cap, int lim) {
        super(cap, lim);
        this.isReadOnly = true;
    }

    HeapIntBufferRImpl(int[] buf, int off, int len) {
        super(buf, off, len);
        this.isReadOnly = true;
    }

    protected HeapIntBufferRImpl(int[] buf, int mark, int pos, int lim, int cap, int off) {
        super(buf, mark, pos, lim, cap, off);
        this.isReadOnly = true;
    }

    public IntBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapIntBufferRImpl(storage, -1, 0, rem, rem, pos + offset);
    }

    @Override
    public IntBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new HeapIntBufferRImpl(storage, -1, 0, length, length, index + offset);
    }

    public IntBufferImpl duplicate() {
        return new HeapIntBufferRImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(),
                offset);
    }

    public IntBufferImpl asReadOnlyBuffer() {
        return duplicate();
    }

    public boolean isReadOnly() {
        return true;
    }

    public IntBufferImpl put(int x) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int i, int x) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(IntBufferImpl src) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int index, IntBufferImpl src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl put(int index, int[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public IntBufferImpl compact() {
        throw new ReadOnlyBufferException();
    }

    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
}
