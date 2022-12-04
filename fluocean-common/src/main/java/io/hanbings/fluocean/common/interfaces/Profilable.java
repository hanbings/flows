package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Profilable<T> {
    Response<T> profile();

    Response<T> profile(String token);
}
