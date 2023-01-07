package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.interfaces.Serialization;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
        // 处理 url 的 params
        URI check;
        URI target;

        try {
            // 从旧的 url 创建 URI 对象
            check = new URI(url);

            StringBuilder strings = new StringBuilder();

            // 判断是否已经有 query 参数 如果没有还需要 & 带在 url 尾部
            if (check.getQuery() != null) {
                strings.append(check.getQuery()).append("&");
            }
            // 转换参数拼接到 url 尾部
            params.forEach((k, v) -> strings.append(k).append("=").append(v).append("&"));

            // 构造新 url
            target = new URI(
                    check.getScheme(),
                    check.getAuthority(),
                    check.getPath(),
                    strings.toString(),
                    check.getFragment()
            );
        } catch (URISyntaxException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }

        // 为了掺入 headers 参数拆分 builder
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .uri(target);
        // 添加 headers
        headers.forEach(builder::header);

        // 发出请求
        try {
            HttpResponse<String> response = client.send(
                    builder.build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            return new OAuthResponse(
                    false,
                    null,
                    response.statusCode(),
                    response.body(),
                    serialization.map(
                            String.class,
                            String.class,
                            response.body()
                    )
            );
        } catch (IOException | InterruptedException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
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
        // 构造表单
        StringBuilder strings = new StringBuilder();
        // 拼接数据
        form.forEach((k, v) -> strings.append(k).append("=").append(v).append("&"));

        // 为了掺入 headers 参数拆分 builder
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(strings.toString()))
                .uri(URI.create(url));
        // 添加 headers
        headers.forEach(builder::header);

        // 发出请求
        try {
            HttpResponse<String> response = client.send(
                    builder.build(),
                    HttpResponse.BodyHandlers.ofString()
            );

            return new OAuthResponse(
                    false,
                    null,
                    response.statusCode(),
                    response.body(),
                    serialization.map(
                            String.class,
                            String.class,
                            response.body()
                    )
            );
        } catch (IOException | InterruptedException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }
}
