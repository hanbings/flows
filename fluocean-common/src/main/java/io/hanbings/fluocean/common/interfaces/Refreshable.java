package io.hanbings.fluocean.common.interfaces;

public interface Refreshable<T> {
    Response<T> refresh(String token);
}
