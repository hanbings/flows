package io.hanbings.fluocean.common.interfaces;

public interface Identifiable<D extends Identify, W extends Identify.Wrong> {
    Callback<D, W> identify(String token);

    Callback<D, W> identify(String code, String state, String redirect);
}
