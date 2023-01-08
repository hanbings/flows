package io.hanbings.fluocean.common.interfaces;

public interface Refreshable<D extends Refresh, W extends Refresh.Wrong> {
    Callback<D, W> refresh(String token);
}
