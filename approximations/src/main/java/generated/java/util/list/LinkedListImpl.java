package generated.java.util.list;

import java.io.Serial;
import java.io.Serializable;
import java.lang.Object;
import java.lang.String;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.jacodb.approximation.annotation.Approximate;
import org.jetbrains.annotations.NotNull;
import org.usvm.api.Engine;
import org.usvm.api.SymbolicList;
import runtime.LibSLRuntime;

@Approximate(java.util.LinkedList.class)
public class LinkedListImpl<E> extends AbstractListImpl<E> implements Deque<E>, Cloneable, Serializable {

    @Serial
    private static final long serialVersionUID = 876323262645176354L;

    private SymbolicList<E> storage;

    private int modCount;

    static {
        Engine.assume(true);
    }

    @SuppressWarnings("unused")
    public LinkedListImpl(SymbolicList<E> storage, int modCount) {
        super(storage, modCount);
    }

    @SuppressWarnings("unused")
    public LinkedListImpl() {
        super(Engine.makeSymbolicList(), 0);
    }

    @SuppressWarnings("unused")
    public LinkedListImpl(Collection<? extends E> c) {
        super(c);
    }

    public SymbolicList<E> _getStorage() {
        SymbolicList<E> storage = this.storage;
        Engine.assume(storage != null);
        return storage;
    }

    public void _setStorage(SymbolicList<E> storage) {
        this.storage = storage;
    }

    public int _getModCount() {
        return modCount;
    }

    protected void _setModCount(int newModCount) {
        this.modCount = newModCount;
    }


    private E _unlinkFirst() {
        if (!_isValidIndex(0))
            throw new NoSuchElementException();

        return _deleteElement(0);
    }

    private E _getFirstElement() {
        if (!_isValidIndex(0))
            throw new NoSuchElementException();

        return _uncheckedGet(0);
    }

    public boolean add(E e) {
        return super._add(e);
    }

    public void add(int index, E element) {
        super._add(index, element);
    }

    public boolean addAll(@NotNull Collection<? extends E> c) {
        return super._addAll(c);
    }

    public boolean addAll(int index, @NotNull Collection<? extends E> c) {
        return super._addAll(index, c);
    }

    public void addFirst(E e) {
        _addElement(0, e);
    }

    public void addLast(E e) {
        _addElement(_getStorage().size(), e);
    }

    public void clear() {
        super._clear();
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Object clone() throws CloneNotSupportedException {
        return super._clone();
    }

    public boolean contains(Object o) {
        return super._contains(o);
    }

    public boolean containsAll(@NotNull Collection<?> c) {
        return super._containsAll(c);
    }

    @NotNull
    public Iterator<E> descendingIterator() {
        return new LinkedList_DescendingIteratorImpl<>(this);
    }

    public E element() {
        return _getFirstElement();
    }

    @SuppressWarnings("EqualsDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return Engine.typeIs(o, LinkedListImpl.class) && super._equals(o);
    }

    public void forEach(Consumer<? super E> _action) {
        super._forEach(_action);
    }

    public E get(int index) {
        return super._get(index);
    }

    public E getFirst() {
        return _getFirstElement();
    }

    public E getLast() {
        return super._get(_getStorage().size() - 1);
    }

    public int hashCode() {
        return super._hashCode();
    }

    public int indexOf(Object o) {
        return super._indexOf(o);
    }

    public boolean isEmpty() {
        return super._isEmpty();
    }

    @NotNull
    public Iterator<E> iterator() {
        return super._iterator();
    }

    public int lastIndexOf(Object o) {
        return super._lastIndexOf(o);
    }

    @NotNull
    public ListIterator<E> listIterator() {
        return super._listIterator();
    }

    @NotNull
    public ListIterator<E> listIterator(int index) {
        return super._listIterator(index);
    }

    public boolean offer(E e) {
        return add(e);
    }

    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @NotNull
    public Stream<E> parallelStream() {
        return super._parallelStream();
    }

    public E peek() {
        if (isEmpty())
            return null;

        return _uncheckedGet(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        int size = _getStorage().size();
        if (size == 0)
            return null;

        return _uncheckedGet(size - 1);
    }

    public E poll() {
        if (isEmpty())
            return null;

        return _deleteElement(0);
    }

    public E pollFirst() {
        return poll();
    }

    public E pollLast() {
        int size = _getStorage().size();
        if (size == 0)
            return null;

        return _deleteElement(size - 1);
    }

    public E pop() {
        return _unlinkFirst();
    }

    public void push(E e) {
        _addElement(0, e);
    }

    public E remove() {
        return _unlinkFirst();
    }

    public boolean remove(Object o) {
        return super._remove(o);
    }

    public E remove(int index) {
        return super._remove(index);
    }

    public boolean removeAll(@NotNull Collection<?> c) {
        return super._removeAll(c);
    }

    public E removeFirst() {
        return remove();
    }

    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    public boolean removeIf(@NotNull Predicate<? super E> filter) {
        return super._removeIf(filter);
    }

    public E removeLast() {
        int size = _getStorage().size();
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return _deleteElement(size - 1);
    }

    public boolean removeLastOccurrence(Object o) {
        SymbolicList<E> storage = _getStorage();
        int size = storage.size();
        if (size == 0)
            return false;

        Engine.assume(size > 0);
        int index;
        for (index = size - 1; index >= 0; index--) {
            Object item = storage.get(index);
            if (LibSLRuntime.equals(item, o)) {
                break;
            }
        }

        if (index != -1) {
            storage.remove(index);
            this.modCount++;
            return true;
        }

        return false;
    }

    public void replaceAll(@NotNull UnaryOperator<E> op) {
        super._replaceAll(op);
    }

    public boolean retainAll(@NotNull Collection<?> c) {
        return super._retainAll(c);
    }

    public E set(int index, E element) {
        return super._set(index, element);
    }

    public int size() {
        return super._size();
    }

    public void sort(Comparator<? super E> c) {
        super._sort(c);
    }

    @NotNull
    public Spliterator<E> spliterator() {
        return super._spliterator();
    }

    @NotNull
    public Stream<E> stream() {
        return super._stream();
    }

    @NotNull
    public List<E> subList(int fromIndex, int toIndex) {
        return super._subList(fromIndex, toIndex);
    }

    @NotNull
    public Object[] toArray() {
        return super._toArray();
    }

    public <T> T[] toArray(@NotNull IntFunction<T[]> generator) {
        return super._toArray(generator);
    }

    @NotNull
    public <T> T[] toArray(@NotNull T[] a) {
        return super._toArray(a);
    }

    public String toString() {
        return super._toString();
    }
}
