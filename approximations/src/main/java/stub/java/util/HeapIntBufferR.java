package stub.java.util;

import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class HeapIntBufferR {
    HeapIntBufferR(int cap, int lim) {
        throw new LinkageError();
    }

    HeapIntBufferR(int[] buf, int off, int len) {
        throw new LinkageError();
    }

    protected HeapIntBufferR(int[] buf, int mark, int pos, int lim, int cap, int off) {
        throw new LinkageError();
    }

    public IntBuffer slice() {
        throw new LinkageError();
    }

    public IntBuffer slice(int index, int length) {
        throw new LinkageError();
    }

    public IntBuffer duplicate() {
        throw new LinkageError();
    }

    public IntBuffer asReadOnlyBuffer() {
        throw new LinkageError();
    }

    public boolean isReadOnly() {
        throw new LinkageError();
    }

    public IntBuffer put(int x) {
        throw new LinkageError();
    }

    public IntBuffer put(int i, int x) {
        throw new LinkageError();
    }

    public IntBuffer put(int[] src, int offset, int length) {
        throw new LinkageError();
    }

    public IntBuffer put(IntBuffer src) {
        throw new LinkageError();
    }

    public IntBuffer put(int index, IntBuffer src, int offset, int length) {
        throw new LinkageError();
    }

    public IntBuffer put(int index, int[] src, int offset, int length) {
        throw new LinkageError();
    }

    public IntBuffer compact() {
        throw new LinkageError();
    }

    public ByteOrder order() {
        throw new LinkageError();
    }
}
