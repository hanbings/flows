package io.hanbings.flows.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

public interface Request {
    Response get(Serialization serialization, Proxy proxy, String url);

    Response get(Serialization serialization, Proxy proxy, String url, Map<String, String> params);

    Response get(Serialization serialization, Proxy proxy, String url,
                 Map<String, String> params, Map<String, String> headers);

    Response post(Serialization serialization, Proxy proxy, String url);

    Response post(Serialization serialization, Proxy proxy, String url, Map<String, String> form);

    Response post(Serialization serialization, Proxy proxy, String url,
                  Map<String, String> form, Map<String, String> headers);

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
