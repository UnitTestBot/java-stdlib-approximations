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
import stub.java.util.map.Map_EntrySet;

@Approximate(Map_EntrySet.class)
public final class Map_EntrySetImpl<K, V> extends Map_ContentsSetImpl<K, V, Map.Entry<K, V>> {

    public Map_EntrySetImpl(AbstractMapImpl<K, V> parent) {
        super(parent);
    }

    Map.Entry<K, V> _contentByKey(LibSLRuntime.Map<K, Map.Entry<K, V>> storage, K key) {
        return storage.get(key);
    }

    public boolean add(Map.Entry<K, V> e) {
        return super._add(e);
    }

    public boolean addAll(@NotNull Collection<? extends Map.Entry<K, V>> c) {
        return super._addAll(c);
    }

    public void clear() {
        super._clear();
    }

    @SuppressWarnings("unchecked")
    boolean _containsInStorage(LibSLRuntime.Map<K, Map.Entry<K, V>> storage, Object o) {
        if (o instanceof Map.Entry) {
            Map.Entry<K, V> oEntry = (Map.Entry<K, V>) o;
            K key = oEntry.getKey();
            if (storage.hasKey(key)) {
                Map.Entry<K, V> entry = storage.get(key);
                return LibSLRuntime.equals(entry, oEntry);
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        return super._contains(o);
    }

    public boolean containsAll(@NotNull Collection<?> c) {
        return super._containsAll(c);
    }

    public boolean equals(Object other) {
        return super._equals(other);
    }

    public void forEach(Consumer<? super Map.Entry<K, V>> userAction) {
        super._forEach(userAction);
    }

    public int hashCode() {
        return super._hashCode();
    }

    public boolean isEmpty() {
        return super._isEmpty();
    }

    @NotNull
    public Iterator<Map.Entry<K, V>> iterator() {
        return super._iterator();
    }

    public Stream<Map.Entry<K, V>> parallelStream() {
        return super._parallelStream();
    }

    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        if (!(o instanceof Map.Entry<?, ?>))
            return false;

        Map.Entry<K, V> typedOtherEntry = (Map.Entry<K, V>) o;
        K key = typedOtherEntry.getKey();
        LibSLRuntime.Map<K, Map.Entry<K, V>> storage = _getStorage();
        if (storage.hasKey(key)) {
            Map.Entry<K, V> entry = storage.get(key);
            if (LibSLRuntime.equals(entry, typedOtherEntry)) {
                storage.remove(key);
                this.map.modCount++;
                return true;
            }
        }

        return false;
    }

    public boolean removeAll(@NotNull Collection<?> c) {
        return super._removeAll(c);
    }

    public boolean removeIf(Predicate<? super Map.Entry<K, V>> filter) {
        return super._removeIf(filter);
    }

    public boolean retainAll(@NotNull Collection<?> c) {
        return super._retainAll(c);
    }

    public int size() {
        return super._size();
    }

    public Spliterator<Map.Entry<K, V>> spliterator() {
        return super._spliterator();
    }

    public Stream<Map.Entry<K, V>> stream() {
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
