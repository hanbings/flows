package io.hanbings.flows.common.interfaces;

@SuppressWarnings("unused")
public interface Refresh {
    String accessToken();

    interface Wrong {
        String error();
    }
}
