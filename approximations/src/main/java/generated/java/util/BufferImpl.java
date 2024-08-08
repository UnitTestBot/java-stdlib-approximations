package generated.java.util;

import jdk.internal.access.SharedSecrets;
import jdk.internal.misc.ScopedMemoryAccess;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import org.jacodb.approximation.annotation.Approximate;
import org.usvm.api.Engine;

import java.nio.*;

@Approximate(java.nio.Buffer.class)
public abstract class BufferImpl {
    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;
    protected int offset = 0;


    BufferImpl(int cap) {
        this.capacity = cap;
        bufferAssume();
    }

    BufferImpl(int mark, int pos, int lim, int cap) {
        if (cap < 0)
            throw createCapacityException(cap);
        this.capacity = cap;
        limit(lim);
        position(pos);
        if (mark >= 0) {
            if (mark > pos)
                throw new IllegalArgumentException("mark > position: ("
                        + mark + " > " + pos + ")");
            this.mark = mark;
        }
        bufferAssume();
    }

    protected void bufferAssume() {
        Engine.assume(0 <= position);
        Engine.assume(position <= limit);
        Engine.assume(limit <= capacity);
        Engine.assume(mark <= position);
        Engine.assume(mark >= -1);
    }

    static IllegalArgumentException createSameBufferException() {
        return new IllegalArgumentException("The source buffer is this buffer");
    }

    static IllegalArgumentException createCapacityException(int capacity) {
        assert capacity < 0 : "capacity expected to be negative";
        return new IllegalArgumentException("capacity < 0: ("
                + capacity + " < 0)");
    }

    protected int applyOffset(int i) {
        Engine.assume(offset >= 0);
        return i + offset;
    }

    public final int capacity() {
        bufferAssume();
        return capacity;
    }

    public final int position() {
        bufferAssume();
        return position;
    }

    public BufferImpl position(int newPosition) {
        if (newPosition > limit | newPosition < 0)
            throw createPositionException(newPosition);
        if (mark > newPosition) mark = -1;
        position = newPosition;
        bufferAssume();
        return this;
    }

    private IllegalArgumentException createPositionException(int newPosition) {
        String msg;

        if (newPosition > limit) {
            msg = "newPosition > limit: (" + newPosition + " > " + limit + ")";
        } else {
            assert newPosition < 0 : "newPosition expected to be negative";
            msg = "newPosition < 0: (" + newPosition + " < 0)";
        }

        return new IllegalArgumentException(msg);
    }

    public final int limit() {
        return limit;
    }

    public BufferImpl limit(int newLimit) {
        if (newLimit > capacity | newLimit < 0)
            throw createLimitException(newLimit);
        limit = newLimit;
        if (position > newLimit) position = newLimit;
        if (mark > newLimit) mark = -1;
        bufferAssume();
        return this;
    }

    private IllegalArgumentException createLimitException(int newLimit) {
        String msg;

        if (newLimit > capacity) {
            msg = "newLimit > capacity: (" + newLimit + " > " + capacity + ")";
        } else {
            assert newLimit < 0 : "newLimit expected to be negative";
            msg = "newLimit < 0: (" + newLimit + " < 0)";
        }

        return new IllegalArgumentException(msg);
    }

    public BufferImpl mark() {
        bufferAssume();
        mark = position;
        bufferAssume();
        return this;
    }

    public BufferImpl reset() {
        bufferAssume();
        int m = mark;
        if (m < 0)
            throw new InvalidMarkException();
        position = m;
        bufferAssume();
        return this;
    }

    public BufferImpl clear() {
        bufferAssume();
        position = 0;
        limit = capacity;
        mark = -1;
        bufferAssume();
        return this;
    }

    public BufferImpl flip() {
        bufferAssume();
        limit = position;
        position = 0;
        mark = -1;
        bufferAssume();
        return this;
    }

    public BufferImpl rewind() {
        position = 0;
        mark = -1;
        bufferAssume();
        return this;
    }

    public final int remaining() {
        bufferAssume();
        int rem = limit - position;
        return rem > 0 ? rem : 0;
    }

    public final boolean hasRemaining() {
        bufferAssume();
        return position < limit;
    }

    public abstract boolean isReadOnly();

    public abstract boolean hasArray();

    public abstract Object array();

    public abstract int arrayOffset();

    public abstract boolean isDirect();

    public abstract BufferImpl slice();

    public abstract BufferImpl slice(int index, int length);

    public abstract BufferImpl duplicate();

    abstract Object base();

    final int nextGetIndex() {
        bufferAssume();
        int p = position;
        if (p >= limit)
            throw new BufferUnderflowException();
        position = p + 1;
        bufferAssume();
        return p;
    }

    final int nextGetIndex(int nb) {
        bufferAssume();
        int p = position;
        if (limit - p < nb)
            throw new BufferUnderflowException();
        position = p + nb;
        bufferAssume();
        return p;
    }

    final int nextPutIndex() {
        bufferAssume();
        int p = position;
        if (p >= limit)
            throw new BufferOverflowException();
        position = p + 1;
        bufferAssume();
        return p;
    }

    final int nextPutIndex(int nb) {
        bufferAssume();
        int p = position;
        if (limit - p < nb)
            throw new BufferOverflowException();
        position = p + nb;
        bufferAssume();
        return p;
    }

    @IntrinsicCandidate
    final int checkIndex(int i) {
        bufferAssume();
        if ((i < 0) || (i >= limit))
            throw new IndexOutOfBoundsException();
        return i;
    }

    final int checkIndex(int i, int nb) {
        bufferAssume();
        if ((i < 0) || (nb > limit - i))
            throw new IndexOutOfBoundsException();
        return i;
    }

    final int markValue() {
        bufferAssume();
        return mark;
    }

    final void discardMark() {
        bufferAssume();
        mark = -1;
    }

    protected int checkFromIndexSize(int fromIndex, int size, int length) {
        if ((length | fromIndex | size) < 0 || size > length - fromIndex)
            throw new IndexOutOfBoundsException();
        return fromIndex;
    }

    protected int checkFromToIndex(int fromIndex, int toIndex, int length) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > length)
            throw new IndexOutOfBoundsException();
        return fromIndex;
    }

    @ForceInline
    final ScopedMemoryAccess.Scope scope() {
        throw new UnsupportedOperationException();
    }

    final void checkScope() {
        throw new UnsupportedOperationException();
    }

    static {
        SharedSecrets.setJavaNioAccess(null);
    }
}
