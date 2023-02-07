package io.hanbings.flows.common.interfaces;

@SuppressWarnings("unused")
public interface Refreshable<D extends Refresh, W extends Refresh.Wrong> {
    Callback<D, W> refresh(String token);
}
