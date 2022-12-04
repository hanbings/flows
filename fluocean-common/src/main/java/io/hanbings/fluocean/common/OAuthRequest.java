package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;

import java.util.Map;

public class OAuthRequest implements Request {

    @Override
    public <T> Response<T> get(T type, Response.Serialization<T> serialization, Proxy proxy, String url) {
        return null;
    }

    @Override
    public <T> Response<T> get(T type, Response.Serialization<T> serialization, Proxy proxy, String url, Map<String, String> params) {
        return null;
    }

    @Override
    public <T> Response<T> post(T type, Response.Serialization<T> serialization, Proxy proxy, String url) {
        return null;
    }

    @Override
    public <T> Response<T> post(T type, Response.Serialization<T> serialization, Proxy proxy, String url, Map<String, String> form) {
        return null;
    }
}
