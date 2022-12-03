package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Response;


public class OAuthResponse<T> implements Response<T> {
    Serialization<T> serialization = new Response.Serialization<>();

    @Override
    public T data() {
        return null;
    }

    @Override
    public boolean success() {
        return false;
    }
}
