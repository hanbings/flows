package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.State;

public class OAuthState implements State {
    @Override
    public String get() {
        return null;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public void add(String state) {

    }

    @Override
    public boolean has(String state) {
        return false;
    }

    @Override
    public String fire(String state) {
        return null;
    }

    @Override
    public void clean() {

    }
}
