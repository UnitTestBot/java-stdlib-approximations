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
import org.usvm.api.Engine;
import runtime.LibSLRuntime;
import stub.java.util.map.Map_Values;

@Approximate(Map_Values.class)
public class Map_ValuesImpl<K, V> extends Map_ContentsImpl<K, V, V> {

    public Map_ValuesImpl(AbstractMapImpl<K, V> parent) {
        super(parent);
    }

    V _contentByKey(LibSLRuntime.Map<K, Map.Entry<K, V>> storage, K key) {
        Map.Entry<K, V> entry = storage.get(key);
        return entry.getValue();
    }

    public boolean add(V e) {
        return super._add(e);
    }

    public boolean addAll(@NotNull Collection<? extends V> c) {
        return super._addAll(c);
    }

    public void clear() {
        super._clear();
    }

    boolean _containsInStorage(LibSLRuntime.Map<K, Map.Entry<K, V>> storage, Object value) {
        LibSLRuntime.Map<K, Map.Entry<K, V>> unseen = _getStorage().duplicate();
        int size = unseen.size();
        Engine.assume(size > 0);
        for (int i = 0; i < size; i++) {
            K key = unseen.anyKey();
            Map.Entry<K, V> entry = unseen.get(key);
            V curValue = entry.getValue();
            if (LibSLRuntime.equals(curValue, value))
                return true;

            unseen.remove(key);
        }

        return false;
    }

    public boolean contains(Object value) {
        return super._contains(value);
    }

    public boolean containsAll(@NotNull Collection<?> c) {
        return super._containsAll(c);
    }

    public void forEach(Consumer<? super V> userAction) {
        super._forEach(userAction);
    }

    public boolean isEmpty() {
        return super._isEmpty();
    }

    @NotNull
    public Iterator<V> iterator() {
        return super._iterator();
    }

    public Stream<V> parallelStream() {
        return super._parallelStream();
    }

    public boolean remove(Object value) {
        LibSLRuntime.Map<K, Map.Entry<K, V>> storage = _getStorage();
        LibSLRuntime.Map<K, Map.Entry<K, V>> unseen = storage.duplicate();
        int size = unseen.size();
        Engine.assume(size >= 0);
        for (int i = 0; i < size; i++) {
            K key = unseen.anyKey();
            Map.Entry<K, V> entry = unseen.get(key);
            V curValue = entry.getValue();
            if (LibSLRuntime.equals(curValue, value)) {
                storage.remove(key);
                this.map.modCount++;
                return true;
            }

            unseen.remove(key);
        }

        return false;
    }

    public boolean removeAll(@NotNull Collection<?> c) {
        return super._removeAll(c);
    }

    public boolean removeIf(Predicate<? super V> filter) {
        return super._removeIf(filter);
    }

    public boolean retainAll(@NotNull Collection<?> c) {
        return super._retainAll(c);
    }

    public int size() {
        return super._size();
    }

    public Spliterator<V> spliterator() {
        return super._spliterator();
    }

    public Stream<V> stream() {
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
