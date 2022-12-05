package io.hanbings.fluocean.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

public interface Request {
    <T, D, E> Response<T, D, E> get(D type, E error, Serialization serialization, Proxy proxy, String url);

    <T, D, E> Response<T, D, E> get(D type, E error, Serialization serialization, Proxy proxy, String url, Map<String, String> params);

    <T, D, E> Response<T, D, E> post(D type, E error, Serialization serialization, Proxy proxy, String url);

    <T, D, E> Response<T, D, E> post(D type, E error, Serialization serialization, Proxy proxy, String url, Map<String, String> form);

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
