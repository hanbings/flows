package io.hanbings.flows.common.interfaces;

@SuppressWarnings("SpellCheckingInspection unused")
public interface Revokable<D extends Revoke, W extends Revoke.Wrong> {
    Callback<D, W> revoke(String token);
}
