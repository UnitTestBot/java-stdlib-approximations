package stub.java.util;

import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class HeapCharBufferR {
    HeapCharBufferR(int cap, int lim) {
        throw new LinkageError();
    }

    HeapCharBufferR(char[] buf, int off, int len) {
        throw new LinkageError();
    }

    protected HeapCharBufferR(char[] buf, int mark, int pos, int lim, int cap, int off) {
        throw new LinkageError();
    }

    public CharBuffer slice() {
        throw new LinkageError();
    }

    public CharBuffer slice(int index, int length) {
        throw new LinkageError();
    }

    public CharBuffer duplicate() {
        throw new LinkageError();
    }

    public CharBuffer asReadOnlyBuffer() {
        throw new LinkageError();
    }

    public boolean isReadOnly() {
        throw new LinkageError();
    }

    public CharBuffer put(char x) {
        throw new LinkageError();
    }

    public CharBuffer put(int i, char x) {
        throw new LinkageError();
    }

    public CharBuffer put(char[] src, int offset, int length) {
        throw new LinkageError();
    }

    public CharBuffer put(CharBuffer src) {
        throw new LinkageError();
    }

    public CharBuffer put(int index, CharBuffer src, int offset, int length) {
        throw new LinkageError();
    }

    public CharBuffer put(int index, char[] src, int offset, int length) {
        throw new LinkageError();
    }

    public CharBuffer put(String src, int start, int end) {
        throw new LinkageError();
    }

    public CharBuffer compact() {
        throw new LinkageError();
    }

    String toString(int start, int end) {
        throw new LinkageError();
    }

    public CharBuffer subSequence(int start, int end) {
        throw new LinkageError();
    }

    public ByteOrder order() {
        throw new LinkageError();
    }

    ByteOrder charRegionOrder() {
        throw new LinkageError();
    }
}
