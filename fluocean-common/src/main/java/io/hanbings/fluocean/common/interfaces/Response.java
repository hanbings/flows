package io.hanbings.fluocean.common.interfaces;

import java.util.function.Consumer;

public interface Response<D, E> {
    D data();

    E error();

    String token();

    Throwable throwable();

    Response<D, E> succeed(Consumer<D> data);

    Response<D, E> fail(Consumer<E> error);

    Response<D, E> except(Consumer<Throwable> throwable);

    boolean success();
}
