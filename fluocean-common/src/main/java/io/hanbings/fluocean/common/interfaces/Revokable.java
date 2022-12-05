package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Revokable<T, D, E> {
    Response<T, D, E> revoke(String token);
}
