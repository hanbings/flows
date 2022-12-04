package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Response;


public class OAuthResponse<T> implements Response<T> {
    private OAuthResponse() {
    }

    public OAuthResponse(Serialization<T> serialization, T data) {

    }

    public OAuthResponse(Throwable throwable) {

    }

    @Override
    public T data() {
        return null;
    }

    @Override
    public boolean success() {
        return false;
    }
}
