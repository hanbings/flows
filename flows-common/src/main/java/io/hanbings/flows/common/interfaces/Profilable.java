package io.hanbings.flows.common.interfaces;

@SuppressWarnings("SpellCheckingInspection unused")
public interface Profilable<D extends Profile, W extends Profile.Wrong> {
    Callback<D, W> profile(String token);
}