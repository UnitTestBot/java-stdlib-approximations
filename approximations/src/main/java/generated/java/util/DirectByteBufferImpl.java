package generated.java.util;

import jdk.internal.ref.Cleaner;
import org.jacodb.approximation.annotation.Approximate;
import runtime.LibSLRuntime;
import sun.nio.ch.DirectBuffer;

import java.io.FileDescriptor;
import java.nio.*;

@Approximate(stub.java.util.DirectByteBuffer.class)
public class DirectByteBufferImpl extends MappedByteBufferImpl implements DirectBuffer {
    private final Object att;

    DirectByteBufferImpl(int cap) {
        super(-1, 0, cap, cap, null);
        storage = new byte[cap];
        LibSLRuntime.ArrayActions.fill(storage, (byte) 0);
        att = null;
    }

    DirectByteBufferImpl(int cap, Object ob, byte[] segment) {
        super(-1, 0, cap, cap, segment);
        att = ob;
    }

    DirectByteBufferImpl(int cap, Object ob, FileDescriptor fd, boolean isSync, byte[] segment) {
        super(-1, 0, cap, cap, fd, isSync, segment);
        att = ob;
    }

    protected DirectByteBufferImpl(int cap, FileDescriptor fd, boolean isSync, byte[] segment) {
        super(-1, 0, cap, cap, fd, isSync, segment);
        att = null;
    }

    DirectByteBufferImpl(DirectBuffer db, int mark, int pos, int lim, int cap, int off, byte[] segment) {
        super(mark, pos, lim, cap, segment);
        offset = off;
        Object attachment = db.attachment();
        att = (attachment == null ? db : attachment);
    }

    @Override
    Object base() {
        return null;
    }

    public ByteBufferImpl slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = pos;
        assert (off >= 0);
        return new DirectByteBufferImpl(this, -1, 0, rem, rem, off, storage);
    }

    @Override
    public ByteBufferImpl slice(int index, int length) {
        checkFromIndexSize(index, length, limit());
        return new DirectByteBufferImpl(this, -1, 0, length, length, index, storage);
    }

    public ByteBufferImpl duplicate() {
        return new DirectByteBufferImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public ByteBufferImpl asReadOnlyBuffer() {
        return new DirectByteBufferRImpl(this, this.markValue(), this.position(), this.limit(), this.capacity(),
                0, storage);
    }

    public long address() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object attachment() {
        return att;
    }

    @Override
    public Cleaner cleaner() {
        return null;
    }

    public byte get() {
        return super.get();
    }

    public byte get(int i) {
        return super.get(i);
    }

    public ByteBufferImpl get(int index, byte[] dst, int offset, int length) {
        super.get(index, dst, offset, length);
        return this;
    }

    public ByteBufferImpl put(byte x) {
        return super.put(x);
    }

    public ByteBufferImpl put(int i, byte x) {
        return super.put(i, x);
    }

    public ByteBufferImpl put(ByteBufferImpl src) {
        super.put(src);
        return this;
    }

    public ByteBufferImpl put(int index, ByteBufferImpl src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public ByteBufferImpl put(byte[] src, int offset, int length) {
        super.put(src, offset, length);
        return this;
    }

    public ByteBufferImpl put(int index, byte[] src, int offset, int length) {
        super.put(index, src, offset, length);
        return this;
    }

    public ByteBufferImpl compact() {
        return super.compact();
    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return false;
    }

    public char getChar() {
        return super.getChar();
    }

    public char getChar(int i) {
        return super.getChar(i);
    }

    public ByteBufferImpl putChar(char x) { return super.putChar(x); }

    public ByteBufferImpl putChar(int i, char x) {
        return super.putChar(i, x);
    }

    public CharBuffer asCharBuffer() {
        throw new UnsupportedOperationException("Not yet implemented");
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
        throw new UnsupportedOperationException("Not yet implemented");
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
        throw new UnsupportedOperationException("Not yet implemented");
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
        throw new UnsupportedOperationException("Not yet implemented");
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
        throw new UnsupportedOperationException("Not yet implemented");
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
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
