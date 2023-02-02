package io.hanbings.flows.common.interfaces;

public interface Access {
    String accessToken();

    interface Wrong {
        String error();
    }
}
