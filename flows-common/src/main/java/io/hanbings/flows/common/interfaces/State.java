package io.hanbings.flows.common.interfaces;

@SuppressWarnings("unused")
public interface State {
    String add();

    void add(String state);

    boolean has(String state);

    boolean fire(String state);

    void empty();

    void clean();
}
