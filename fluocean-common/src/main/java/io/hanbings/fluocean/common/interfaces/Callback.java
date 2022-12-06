package io.hanbings.fluocean.common.interfaces;

import java.util.function.Consumer;

public interface Callback<D, E> {
    D data();

    E error();

    String token();

    Throwable throwable();

    Callback<D, E> succeed(Consumer<D> data);

    Callback<D, E> fail(Consumer<E> error);

    Callback<D, E> except(Consumer<Throwable> throwable);

    boolean success();
}
