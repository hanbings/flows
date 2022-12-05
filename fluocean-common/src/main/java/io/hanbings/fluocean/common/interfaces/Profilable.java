package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Profilable<D, E> {
    Response<D, E> profile();

    Response<D, E> profile(String token);
}
