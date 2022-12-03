package io.hanbings.fluocean.common.interfaces;

public interface Refreshable<T, P extends Response<T>> {
    P refresh(String token);
}
