package generated.java.util.map;

import org.jacodb.approximation.annotation.Approximate;
import org.jetbrains.annotations.NotNull;
import stub.java.util.map.Map_ContentsSet;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Approximate(Map_ContentsSet.class)
public abstract class Map_ContentsSetImpl<K, V, Content> extends Map_ContentsImpl<K, V, Content> implements Set<Content> {

    public Map_ContentsSetImpl(AbstractMapImpl<K, V> map) {
        super(map);
    }

    public boolean _add(Content e) {
        return super.add(e);
    }

    public boolean _addAll(@NotNull Collection<? extends Content> c) {
        return super.addAll(c);
    }

    public void _clear() {
        super.clear();
    }

    public boolean _contains(Object obj) {
        return super.contains(obj);
    }

    public boolean _containsAll(@NotNull Collection<?> c) {
        return super.containsAll(c);
    }

    Set<?> _equalsTypeCheck(Object other) {
        if (other instanceof Set<?>)
            return (Set<?>) other;

        return null;
    }

    public boolean _equals(Object other) {
        return super.equals(other);
    }

    public void _forEach(Consumer<? super Content> userAction) {
        super.forEach(userAction);
    }

    public int _hashCode() {
        return super.hashCode();
    }

    public boolean _isEmpty() {
        return super.isEmpty();
    }

    @NotNull
    public Iterator<Content> _iterator() {
        return super.iterator();
    }

    public Stream<Content> _parallelStream() {
        return super.parallelStream();
    }

    public boolean _removeAll(@NotNull Collection<?> c) {
        return super.removeAll(c);
    }

    public boolean _removeIf(Predicate<? super Content> filter) {
        return super.removeIf(filter);
    }

    public boolean _retainAll(@NotNull Collection<?> c) {
        return super.retainAll(c);
    }

    public int _size() {
        return super._size();
    }

    public Spliterator<Content> _spliterator() {
        return new Map_ContentsSet_SpliteratorImpl<>(this);
    }

    public Stream<Content> _stream() {
        return super.stream();
    }

    @NotNull
    public Object[] _toArray() {
        return super._toArray();
    }

    public <T> T[] _toArray(IntFunction<T[]> generator) {
        return super._toArray(generator);
    }

    @NotNull
    public <T> T[] _toArray(@NotNull T[] array) {
        return super._toArray(array);
    }

    public String _toString() {
        return super._toString();
    }
}
