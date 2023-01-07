package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.interfaces.Serialization;
import lombok.AllArgsConstructor;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
public class OAuthModernHttpRequest implements Request {
    HttpClient client;

    public OAuthModernHttpRequest() {
        client = HttpClient.newBuilder().build();
    }

    public OAuthModernHttpRequest(Proxy proxy) {
        // 设置 Socks 代理的认证
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestingHost().equalsIgnoreCase(proxy.host())) {
                    if (proxy.port() == getRequestingPort()) {
                        return new PasswordAuthentication(proxy.username(), proxy.password().toCharArray());
                    }
                }
                return null;
            }
        });

        // 为客户端设置 Socks 代理
        client = HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress(proxy.host(), proxy.port())))
                .build();
    }

    @Override
    public Response get(Serialization serialization, Proxy proxy, String url) {
        return get(serialization, proxy, url, Map.of(), Map.of());
    }

    @Override
    public Response get(Serialization serialization, Proxy proxy, String url,
                        Map<String, String> params) {
        return get(serialization, proxy, url, params, Map.of());
    }

    @Override
    public Response get(
            Serialization serialization,
            Proxy proxy,
            String url,
            Map<String, String> params,
            Map<String, String> headers
    ) {
        String[] strings = params.entrySet()
                .stream()
                .collect(
                        ArrayList<String>::new,
                        (l, s) -> {
                            l.add(s.getKey());
                            l.add(s.getValue());
                        },
                        ArrayList::addAll
                )
                .toArray(String[]::new);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .headers(strings)
                .build();

        return null;
    }

    @Override
    public Response post(Serialization serialization, Proxy proxy, String url) {
        return this.post(serialization, proxy, url, Map.of(), Map.of());
    }

    @Override
    public Response post(Serialization serialization, Proxy proxy, String url,
                         Map<String, String> form) {
        return this.post(serialization, proxy, url, form, Map.of());
    }

    @Override
    public Response post(
            Serialization serialization,
            Proxy proxy,
            String url,
            Map<String, String> form,
            Map<String, String> headers
    ) {
        return null;
    }
}
