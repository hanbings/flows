package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Revokable<T, P extends Response<T>> {
    P revoke(String token);
}
