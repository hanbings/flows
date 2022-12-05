package io.hanbings.fluocean.common.function;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@SuppressWarnings("unused")
@RequiredArgsConstructor(staticName = "of")
public class Lazy<T> {
    final Supplier<T> supplier;
    volatile T value;
    volatile boolean initialized;

    public T get() {
        if (value == null) {
            synchronized (this) {
                if (value == null) {
                    value = supplier.get();
                    initialized = true;
                }
            }
        }
        return value;
    }

    public boolean initialized() {
        return initialized;
    }
}
