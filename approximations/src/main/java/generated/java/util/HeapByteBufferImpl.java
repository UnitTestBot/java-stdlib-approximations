package generated.java.util;

import org.jacodb.approximation.annotation.Approximate;
import runtime.LibSLRuntime;

import java.nio.*;

@Approximate(stub.java.util.HeapByteBuffer.class)
public class HeapByteBufferImpl extends ByteBufferImpl {
    HeapByteBufferImpl(int cap, int lim) {
        super(-1, 0, lim, cap, new byte[cap], 0);
    }

    HeapByteBufferImpl(byte[] buf, int off, int len) {
        super(-1, off, off + len, buf.length, buf, 0);
    }

    protected HeapByteBufferImpl(byte[] buf, int mark, int pos, int lim, int cap, int off) {
        super(mark, pos, lim, cap, buf, off);
    }

    public ByteBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapByteBufferImpl(storage, -1, 0, rem, rem, pos + offset);
    }

    @Override
    public ByteBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new HeapByteBufferImpl(storage, -1, 0, length, length, index + offset);
    }

    public ByteBufferImpl duplicate() {
        return new HeapByteBufferImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(),
                offset);
    }

    public ByteBufferImpl asReadOnlyBuffer() {
        return new HeapByteBufferRImpl(storage, this.markValue(), this.position(), this.limit(), this.capacity(),
                offset);
    }

    public byte get() {
        return super.get();
    }

    public byte get(int i) {
        return super.get(i);
    }

    public ByteBufferImpl get(byte[] dst, int offset, int length) {
        checkFromIndexSize(offset, length, dst.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferUnderflowException();
        LibSLRuntime.ArrayActions.copy(storage, applyOffset(pos), dst, offset, length);
        position(pos + length);
        return this;
    }

    public ByteBufferImpl get(int index, byte[] dst, int offset, int length) {
        return super.get(index, dst, offset, length);
    }

    public boolean isDirect() {
        return false;
    }

    public boolean isReadOnly() {
        return false;
    }

    public ByteBufferImpl put(byte x) {
        return super.put(x);
    }

    public ByteBufferImpl put(int i, byte x) {
        return super.put(i, x);
    }

    public ByteBufferImpl put(byte[] src, int offset, int length) {
        checkFromIndexSize(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(pos), length);
        position(pos + length);
        return this;
    }

    public ByteBufferImpl put(ByteBufferImpl src) {
        super.put(src);
        return this;
    }

    public ByteBufferImpl put(int index, ByteBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public ByteBufferImpl put(int index, byte[] src, int offset, int length) {
        checkFromIndexSize(index, length, limit());
        checkFromIndexSize(offset, length, src.length);
        LibSLRuntime.ArrayActions.copy(src, offset, storage, applyOffset(index), length);
        return this;
    }

    public ByteBufferImpl compact() {
        return super.compact();
    }

    byte _get(int i) {
        return storage[i];
    }

    void _put(int i, byte b) {
        storage[i] = b;
    }

    public char getChar() {
        return super.getChar();
    }

    public char getChar(int i) {
        return super.getChar(i);
    }

    public ByteBufferImpl putChar(char x) {
        return super.putChar(x);
    }

    public ByteBufferImpl putChar(int i, char x) {
        return super.putChar(i, x);
    }

    public CharBuffer asCharBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public short getShort() {
        return super.getShort();
    }

    public short getShort(int i) {
        return super.getShort(i);
    }

    public ByteBufferImpl putShort(short x) {
        return super.putShort(x);
    }

    public ByteBufferImpl putShort(int i, short x) {
        return super.putShort(i, x);
    }

    public ShortBuffer asShortBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getInt() {
        return super.getInt();
    }

    public int getInt(int i) {
        return super.getInt(i);
    }

    public ByteBufferImpl putInt(int x) {
        return super.putInt(x);
    }

    public ByteBufferImpl putInt(int i, int x) {
        return super.putInt(i, x);
    }

    public IntBuffer asIntBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long getLong() {
        return super.getLong();
    }

    public long getLong(int i) {
        return super.getLong(i);
    }

    public ByteBufferImpl putLong(long x) {
        return super.putLong(x);
    }

    public ByteBufferImpl putLong(int i, long x) {
        return super.putLong(i, x);
    }

    public LongBuffer asLongBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public float getFloat() {
        return super.getFloat();
    }

    public float getFloat(int i) {
        return super.getFloat(i);
    }

    public ByteBufferImpl putFloat(float x) {
        return super.putFloat(x);
    }

    public ByteBufferImpl putFloat(int i, float x) {
        return super.putFloat(i, x);
    }

    public FloatBuffer asFloatBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public double getDouble() {
        return super.getDouble();
    }

    public double getDouble(int i) {
        return super.getDouble(i);
    }

    public ByteBufferImpl putDouble(double x) {
        return super.putDouble(x);
    }

    public ByteBufferImpl putDouble(int i, double x) {
        return super.putDouble(i, x);
    }

    public DoubleBuffer asDoubleBuffer() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
