package io.hanbings.fluocean.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.function.Consumer;

public interface Request {
    Response get(Serialization serialization, Proxy proxy, String url);

    Response get(Serialization serialization, Proxy proxy, String url, Map<String, String> params);

    Response post(Serialization serialization, Proxy proxy, String url);

    Response post(Serialization serialization, Proxy proxy, String url, Map<String, String> form);

    void get(Serialization serialization, Proxy proxy, String url, Consumer<Response> callback);

    void get(Serialization serialization, Proxy proxy, String url, Map<String, String> params, Consumer<Response> callback);

    void post(Serialization serialization, Proxy proxy, String url, Consumer<Response> callback);

    void post(Serialization serialization, Proxy proxy, String url, Map<String, String> form, Consumer<Response> callback);

    @Setter
    @Getter
    @AllArgsConstructor
    @SuppressWarnings("unused")
    @Accessors(fluent = true, chain = true)
    class Proxy {
        java.net.Proxy.Type type;
        String host;
        int port;
        String username;
        String password;
    }
}
