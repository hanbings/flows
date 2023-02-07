package io.hanbings.flows.common.interfaces;

@SuppressWarnings("unused")
public interface Access {
    String accessToken();

    interface Wrong {
        String error();
    }
}
