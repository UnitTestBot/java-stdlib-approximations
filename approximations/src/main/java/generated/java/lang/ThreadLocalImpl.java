package generated.java.lang;

import org.jacodb.approximation.annotation.Approximate;

import java.util.function.Supplier;

@Approximate(java.lang.ThreadLocal.class)
public class ThreadLocalImpl<T> {
    private T storage;

    public ThreadLocalImpl(T value) {
        storage = value;
    }

    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        ThreadLocal<S> local = new ThreadLocal<>();
        local.set(supplier.get());
        return local;
    }

    public T get() {
        return storage;
    }

    public void set(T value) {
        storage = value;
    }

    public void remove() {
        storage = null;
    }
}
