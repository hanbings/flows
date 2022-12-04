package io.hanbings.fluocean.common.interfaces;

public interface State {
    String get();

    String add();

    void add(String state);

    boolean has(String state);

    String fire(String state);

    void clean();
}
