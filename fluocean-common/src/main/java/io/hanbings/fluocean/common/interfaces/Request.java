package io.hanbings.fluocean.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

public interface Request {
    <T> Response<T> get(T type, Proxy proxy, String url);

    <T> Response<T> get(T type, Proxy proxy, String url, Map<String, String> params);

    <T> Response<T> post(T type, Proxy proxy, String url);

    <T> Response<T> post(T type, Proxy proxy, String url, Map<String, String> form);

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
