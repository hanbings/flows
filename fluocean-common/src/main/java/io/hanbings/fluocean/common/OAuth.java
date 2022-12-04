package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Authable;
import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Setter
@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
@Accessors(fluent = true, chain = true)
public class OAuth<T> implements Authable<T> {
    final String authorization;
    final String access;
    String client;
    String secret;
    String redirect;

    Consumer<Request.Proxy> proxy;
    Supplier<Response.Serialization<T>> serialization;
    Request request;

    @Override
    public String authorize() {
        return null;
    }

    @Override
    public String authorize(List<Enum<?>> scopes) {
        return null;
    }

    @Override
    public String authorize(Map<String, String> params) {
        return null;
    }

    @Override
    public String authorize(List<Enum<?>> scopes, Map<String, String> params) {
        return null;
    }

    @Override
    public Response<T> token(String url) {
        return null;
    }

    @Override
    public Response<T> token(String code, boolean raw) {
        return null;
    }
}
