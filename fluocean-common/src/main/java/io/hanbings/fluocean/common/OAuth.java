package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Authable;
import io.hanbings.fluocean.common.interfaces.Request;
import io.hanbings.fluocean.common.interfaces.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
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
public class OAuth<D, E> implements Authable<D, E> {
    final String authorization;
    final String access;
    String client;
    String secret;
    String redirect;

    Consumer<Request.Proxy> proxy;
    Supplier<Request.Serialization> serialization;
    Request request;

    @Override
    public String authorize() {
        return authorize(List.of(), Map.of());
    }

    @Override
    public String authorize(List<Enum<?>> scopes) {
        return authorize(List.of(), Map.of());
    }

    @Override
    public String authorize(Map<String, String> params) {
        return null;
    }

    @Override
    public String authorize(List<Enum<?>> scopes, Map<String, String> params) {
        Map<String, String> temp = new HashMap<>() {{
            put("client_id", client);
            put("redirect_uri", redirect);

            // put scopes
            StringBuilder scope = new StringBuilder();

            IntStream.range(0, scopes.size()).forEach(count -> {
                if (count != 0) {
                    scope.append(",");
                }

                scope.append(scopes.get(count).toString());
            });

            if (scope.length() != 0) {
                put("scope", scope.toString());
            }

            // merge map
            putAll(params);
        }};

        // url builder
        StringBuilder url = new StringBuilder().append(authorization);

        if (temp.size() != 0) {
            url.append("?");

            // map to string
            temp.forEach((k, v) -> {
                url.append(k).append("=").append(v).append("&");
            });

            // eat last '&' and return string
            return url.substring(0, url.length() - 1);
        }

        return url.toString();
    }

    @Override
    public Response<D, E> token(String url) {
        return null;
    }

    @Override
    public Response<D, E> token(String code, boolean raw) {
        return null;
    }
}
