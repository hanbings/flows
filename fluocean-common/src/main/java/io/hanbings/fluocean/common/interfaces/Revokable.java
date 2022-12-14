package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Revokable<D, W> {
    Callback<D, W> revoke(String token);
}
