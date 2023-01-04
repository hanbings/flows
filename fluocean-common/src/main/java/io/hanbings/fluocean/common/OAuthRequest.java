package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.interfaces.Serialization;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OAuthRequest implements Request {
    OkHttpClient client;

    public OAuthRequest() {
        client = new OkHttpClient();
    }

    public OAuthRequest(Proxy proxy) {
        client = client(proxy);
    }

    static OkHttpClient client(Proxy proxy) {
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

        return new OkHttpClient.Builder()
                .proxy(new java.net.Proxy(proxy.type(), new InetSocketAddress(proxy.host(), proxy.port())))
                .build();
    }

    @Override
    public Response get(Serialization serialization,
                        @Nullable Proxy proxy, String url) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }

    @Override
    public Response get(Serialization serialization,
                        @Nullable Proxy proxy, String url, Map<String, String> params) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }

    @Override
    public Response post(Serialization serialization,
                         @Nullable Proxy proxy, String url) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(new FormBody(List.of(), List.of()))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }

    @Override
    public Response post(Serialization serialization,
                         @Nullable Proxy proxy, String url, Map<String, String> form) {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        form.forEach((k, v) -> {
            keys.add(k);
            values.add(v);
        });

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(new FormBody(keys, values))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Call call = proxy == null ? client.newCall(request) : client(proxy).newCall(request);

        try (okhttp3.Response response = call.execute()) {
            String content = Objects.requireNonNull(response.body()).string();

            return new OAuthResponse(
                    false,
                    null,
                    response.code(),
                    content,
                    serialization.map(
                            String.class,
                            String.class,
                            content
                    )
            );
        } catch (IOException e) {
            return new OAuthResponse(true, e, 0, null, null);
        }
    }
}
