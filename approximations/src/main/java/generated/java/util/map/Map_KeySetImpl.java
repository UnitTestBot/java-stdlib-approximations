package generated.java.util.map;

import java.lang.Object;
import java.lang.String;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jacodb.approximation.annotation.Approximate;
import org.jetbrains.annotations.NotNull;
import runtime.LibSLRuntime;
import stub.java.util.map.Map_KeySet;

@Approximate(Map_KeySet.class)
public final class Map_KeySetImpl<K, V> extends Map_ContentsSetImpl<K, V, K> {

    public Map_KeySetImpl(AbstractMapImpl<K, V> parent) {
        super(parent);
    }

    K _contentByKey(LibSLRuntime.Map<K, Map.Entry<K, V>> storage, K key) {
        return key;
    }

    public boolean add(K e) {
        return super._add(e);
    }

    public boolean addAll(@NotNull Collection<? extends K> c) {
        return super._addAll(c);
    }

    public void clear() {
        super._clear();
    }

    @SuppressWarnings("unchecked")
    boolean _containsInStorage(LibSLRuntime.Map<K, Map.Entry<K, V>> storage, Object o) {
        return storage.hasKey((K) o);
    }

    public boolean contains(Object key) {
        return super._contains(key);
    }

    public boolean containsAll(@NotNull Collection<?> c) {
        return super._containsAll(c);
    }

    public boolean equals(Object other) {
        return super._equals(other);
    }

    public void forEach(Consumer<? super K> userAction) {
        super._forEach(userAction);
    }

    public int hashCode() {
        return super._hashCode();
    }

    public boolean isEmpty() {
        return super._isEmpty();
    }

    @NotNull
    public Iterator<K> iterator() {
        return super._iterator();
    }

    public Stream<K> parallelStream() {
        return super._parallelStream();
    }

    @SuppressWarnings("unchecked")
    public boolean remove(Object key) {
        K typedKey = (K) key;
        LibSLRuntime.Map<K, Map.Entry<K, V>> storage = getStorage();
        if (storage.hasKey(typedKey)) {
            storage.remove(typedKey);
            this.map.modCount++;
            return true;
        }

        return false;
    }

    public boolean removeAll(@NotNull Collection<?> c) {
        return super._removeAll(c);
    }

    public boolean removeIf(Predicate<? super K> filter) {
        return super._removeIf(filter);
    }

    public boolean retainAll(@NotNull Collection<?> c) {
        return super._retainAll(c);
    }

    public int size() {
        return super._size();
    }

    public Spliterator<K> spliterator() {
        return super._spliterator();
    }

    public Stream<K> stream() {
        return super._stream();
    }

    @NotNull
    public Object[] toArray() {
        return super._toArray();
    }

    public <T> T[] toArray(IntFunction<T[]> generator) {
        return super._toArray(generator);
    }

    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        return super._toArray(array);
    }

    public String toString() {
        return super._toString();
    }
}
