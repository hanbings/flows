package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Profilable<T, D, E> {
    Response<T, D, E> profile();

    Response<T, D, E> profile(String token);
}
