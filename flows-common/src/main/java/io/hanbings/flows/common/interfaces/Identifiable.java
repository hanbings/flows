package io.hanbings.flows.common.interfaces;

@SuppressWarnings("unused")
public interface Identifiable<D extends Identify, W extends Identify.Wrong> {
    Callback<D, W> identify(String token);
}
