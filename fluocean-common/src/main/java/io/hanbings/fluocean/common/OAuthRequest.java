package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.interfaces.Serialization;

import java.util.Map;

public class OAuthRequest implements Request {

    @Override
    public <D, E> Response<D, E> get(D type, E error, Serialization serialization, Proxy proxy, String url) {
        return null;
    }

    @Override
    public <D, E> Response<D, E> get(D type, E error, Serialization serialization, Proxy proxy, String url, Map<String, String> params) {
        return null;
    }

    @Override
    public <D, E> Response<D, E> post(D type, E error, Serialization serialization, Proxy proxy, String url) {
        return null;
    }

    @Override
    public <D, E> Response<D, E> post(D type, E error, Serialization serialization, Proxy proxy, String url, Map<String, String> form) {
        return null;
    }
}
