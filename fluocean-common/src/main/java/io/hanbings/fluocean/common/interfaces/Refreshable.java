package io.hanbings.fluocean.common.interfaces;

public interface Refreshable<T, D, E> {
    Response<T, D, E> refresh(String token);
}
