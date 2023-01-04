package io.hanbings.fluocean.common.interfaces;

public interface Refreshable<D, W> {
    Callback<D, W> refresh(String token);

    Callback<D, W> refresh(String token, String redirect);
}
