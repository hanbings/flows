package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;

import java.util.Map;

public class OAuthRequest implements Request {

    @Override
    public <T, E> Response<T, E> get(T type, E error, Response.Serialization<T> serialization, Proxy proxy, String url) {
        return null;
    }

    @Override
    public <T, E> Response<T, E> get(T type, E error, Response.Serialization<T> serialization, Proxy proxy, String url, Map<String, String> params) {
        return null;
    }

    @Override
    public <T, E> Response<T, E> post(T type, E error, Response.Serialization<T> serialization, Proxy proxy, String url) {
        return null;
    }

    @Override
    public <T, E> Response<T, E> post(T type, E error, Response.Serialization<T> serialization, Proxy proxy, String url, Map<String, String> form) {
        return null;
    }
}
