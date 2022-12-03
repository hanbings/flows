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
import java.util.stream.IntStream;

@Setter
@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
@Accessors(fluent = true, chain = true)
public class OAuth<R extends Request, T, P extends Response<T>, S extends Enum<?>>
        implements Authable<R, T, P, S> {
    final String authorization;
    final String access;
    String client;
    String secret;
    String redirect;

    Consumer<Request.Proxy> proxy;
    Consumer<Response.Serialization<?>> serialization;
    Supplier<R> request;
    Supplier<P> response;

    @Override
    public String authorize() {
        return authorize(List.of(), Map.of());
    }

    @Override
    public String authorize(List<S> scopes) {
        return authorize(scopes, Map.of());
    }

    @Override
    public String authorize(Map<String, String> params) {
        return authorize(List.of(), params);
    }

    @Override
    public String authorize(List<S> scopes, Map<String, String> params) {
        StringBuilder builder = new StringBuilder()
                .append(authorization)
                .append("?")
                .append("client_id=")
                .append(client);

        IntStream.range(0, scopes.size()).forEach(count -> {
            if (count == 0) builder.append("&scope=");

            builder.append(scopes.get(count));

            if (count != scopes.size() - 1) builder.append(",");
        });

        return builder.toString();
    }

    @Override
    public P token(String url) {
        return null;
    }

    @Override
    public P token(String code, boolean raw) {
        return null;
    }
}
