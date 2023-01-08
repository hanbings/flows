package io.hanbings.fluocean.common.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public interface Revokable<D extends Revoke, W extends Revoke.Wrong> {
    Callback<D, W> revoke(String token);
}
