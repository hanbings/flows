package io.hanbings.fluocean.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

public interface Request {
    <D, E> Response<D, E> get(D type, E error, Serialization serialization, Proxy proxy, String url);

    <D, E> Response<D, E> get(D type, E error, Serialization serialization, Proxy proxy, String url, Map<String, String> params);

    <D, E> Response<D, E> post(D type, E error, Serialization serialization, Proxy proxy, String url);

    <D, E> Response<D, E> post(D type, E error, Serialization serialization, Proxy proxy, String url, Map<String, String> form);

    @Setter
    @Getter
    @AllArgsConstructor
    @SuppressWarnings("unused")
    @Accessors(fluent = true, chain = true)
    class Proxy {
        String host;
        String port;
        String username;
        String password;
    }
}
