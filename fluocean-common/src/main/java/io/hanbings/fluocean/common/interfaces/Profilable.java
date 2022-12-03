package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Profilable<T, P extends Response<T>> {
    P profile();

    P profile(String token);
}
