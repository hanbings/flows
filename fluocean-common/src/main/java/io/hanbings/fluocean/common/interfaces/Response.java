package io.hanbings.fluocean.common.interfaces;

import java.util.function.Consumer;

public interface Response<T, D, E> {
    D data();

    E error();

    T token();

    Throwable throwable();

    Response<T, D, E> succeed(Consumer<D> data);

    Response<T, D, E> fail(Consumer<E> error);

    Response<T, D, E> except(Consumer<Throwable> throwable);

    boolean success();
}
