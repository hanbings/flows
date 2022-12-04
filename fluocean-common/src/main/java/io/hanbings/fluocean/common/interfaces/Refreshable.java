package io.hanbings.fluocean.common.interfaces;

public interface Refreshable<D, E> {
    Response<D, E> refresh(String token);
}
