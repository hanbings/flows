package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Profilable<D extends Profile, W extends Profile.Wrong> {
    Callback<D, W> profile();

    Callback<D, W> profile(String token);
}
