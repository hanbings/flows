package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Revokable<D, E> {
    Callback<D, E> revoke(String token);
}
