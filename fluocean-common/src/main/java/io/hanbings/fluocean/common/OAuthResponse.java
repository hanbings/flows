package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Response;


public class OAuthResponse<T, E> implements Response<T, E> {
    private OAuthResponse() {
    }

    public OAuthResponse(Serialization serialization, T data, E error) {

    }

    public OAuthResponse(Throwable throwable) {

    }

    @Override
    public T data() {
        return null;
    }

    @Override
    public E error() {
        return null;
    }

    @Override
    public boolean success() {
        return false;
    }
}
