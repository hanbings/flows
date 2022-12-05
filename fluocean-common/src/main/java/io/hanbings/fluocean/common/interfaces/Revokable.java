package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Revokable<D, E> {
    Response<D, E> revoke(String token);
}
