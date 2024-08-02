package generated.java.util;

import jdk.internal.access.foreign.UnmapperProxy;
import org.jacodb.approximation.annotation.Approximate;

import java.io.FileDescriptor;
import java.nio.MappedByteBuffer;

@Approximate(java.nio.MappedByteBuffer.class)
public abstract class MappedByteBufferImpl extends ByteBufferImpl {
    private final boolean isSync;

    MappedByteBufferImpl(int mark, int pos, int lim, int cap,
                     FileDescriptor fd, boolean isSync, byte[] segment) {
        super(mark, pos, lim, cap, segment);
        throw new UnsupportedOperationException();
    }

    MappedByteBufferImpl(int mark, int pos, int lim, int cap,
                     boolean isSync, byte[] segment) {
        super(mark, pos, lim, cap, segment);
        this.isSync = isSync;
    }

    MappedByteBufferImpl(int mark, int pos, int lim, int cap, byte[] segment) {
        super(mark, pos, lim, cap, segment);
        this.isSync = false;
    }

    UnmapperProxy unmapper() {
        throw new UnsupportedOperationException();
    }

    private boolean isSync() {
        return isSync;
    }

    public final boolean isLoaded() {
        throw new UnsupportedOperationException();
    }

    public final MappedByteBuffer load() {
        throw new UnsupportedOperationException();
    }

    public final MappedByteBuffer force() {
        throw new UnsupportedOperationException();
    }

    public final MappedByteBuffer force(int index, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final MappedByteBufferImpl position(int newPosition) {
        super.position(newPosition);
        return this;
    }

    @Override
    public final MappedByteBufferImpl limit(int newLimit) {
        super.limit(newLimit);
        return this;
    }

    @Override
    public final MappedByteBufferImpl mark() {
        super.mark();
        return this;
    }

    @Override
    public final MappedByteBufferImpl reset() {
        super.reset();
        return this;
    }

    @Override
    public final MappedByteBufferImpl clear() {
        super.clear();
        return this;
    }

    @Override
    public final MappedByteBufferImpl flip() {
        super.flip();
        return this;
    }

    @Override
    public final MappedByteBufferImpl rewind() {
        super.rewind();
        return this;
    }
}
