package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Profilable<D, W> {
    Callback<D, W> profile();

    Callback<D, W> profile(String token);
}
