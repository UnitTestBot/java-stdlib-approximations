package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import sun.nio.ch.DirectBuffer;

import java.io.FileDescriptor;
import java.nio.*;

@Approximate(stub.java.util.DirectByteBufferR.class)
public class DirectByteBufferRImpl extends DirectByteBufferImpl implements DirectBuffer {
    DirectByteBufferRImpl(int cap) {
        super(cap);
        this.isReadOnly = true;
    }

    protected DirectByteBufferRImpl(int cap, FileDescriptor fd, boolean isSync, byte[] segment) {
        super(cap, fd, isSync, segment);
        this.isReadOnly = true;
    }

    DirectByteBufferRImpl(DirectBuffer db, int mark, int pos, int lim, int cap, int off, byte[] segment) {
        super(db, mark, pos, lim, cap, off, segment);
        this.isReadOnly = true;
    }

    public ByteBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = pos;
        assert (off >= 0);
        return new DirectByteBufferRImpl(this, -1, 0, rem, rem, off, storage);
    }

    @Override
    public ByteBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new DirectByteBufferRImpl(this, -1, 0, length, length, index, storage);
    }

    public ByteBufferImpl duplicate() {
        return new DirectByteBufferRImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public ByteBufferImpl asReadOnlyBuffer() {
        return duplicate();
    }

    public ByteBufferImpl put(byte x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl put(int i, byte x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl put(ByteBufferImpl src) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl put(int index, ByteBufferImpl src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl put(byte[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl put(int index, byte[] src, int offset, int length) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl compact() {
        throw new ReadOnlyBufferException();
    }

    public boolean isReadOnly() {
        return true;
    }

    private ByteBufferImpl putChar(long a, char x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putChar(char x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putChar(int i, char x) {
        throw new ReadOnlyBufferException();
    }

    public CharBuffer asCharBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private ByteBufferImpl putShort(long a, short x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putShort(short x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putShort(int i, short x) {
        throw new ReadOnlyBufferException();
    }

    public ShortBuffer asShortBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private ByteBufferImpl putInt(long a, int x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putInt(int x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putInt(int i, int x) {
        throw new ReadOnlyBufferException();
    }

    public IntBuffer asIntBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private ByteBufferImpl putLong(long a, long x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putLong(long x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putLong(int i, long x) {
        throw new ReadOnlyBufferException();
    }

    public LongBuffer asLongBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private ByteBufferImpl putFloat(long a, float x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putFloat(float x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putFloat(int i, float x) {
        throw new ReadOnlyBufferException();
    }

    public FloatBuffer asFloatBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private ByteBufferImpl putDouble(long a, double x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putDouble(double x) {
        throw new ReadOnlyBufferException();
    }

    public ByteBufferImpl putDouble(int i, double x) {
        throw new ReadOnlyBufferException();
    }

    public DoubleBuffer asDoubleBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
