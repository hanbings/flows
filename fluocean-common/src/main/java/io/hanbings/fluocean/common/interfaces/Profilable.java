package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Profilable<D, E> {
    Callback<D, E> profile();

    Callback<D, E> profile(String token);
}
