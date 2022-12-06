package io.hanbings.fluocean.common.interfaces;

public interface Refreshable<D, E> {
    Callback<D, E> refresh(String token);
}
