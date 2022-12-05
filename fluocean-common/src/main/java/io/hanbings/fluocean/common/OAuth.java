package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.function.Lazy;
import io.hanbings.fluocean.common.interfaces.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    Supplier<Request.Proxy> proxy = null;
    Lazy<Request> request = Lazy.of(
            () -> proxy == null ? new OAuthRequest() : new OAuthRequest(3000, proxy.get()));
    Lazy<Serialization> serialization = Lazy.of(OAuthSerialization::new);
    Lazy<State> state = Lazy.of(() -> new OAuthState(300, () -> UUID.randomUUID().toString()));

    @Override
    public String authorize() {
        return authorize(List.of(), Map.of());
    }

    @Override
    public String authorize(List<Enum<?>> scopes) {
        return authorize(scopes, Map.of());
    }

    @Override
    public String authorize(Map<String, String> params) {
        return authorize(List.of(), params);
    }

    @Override
    public String authorize(List<Enum<?>> scopes, Map<String, String> params) {
        Map<String, String> temp = new HashMap<>() {{
            put("client_id", client);
            put("redirect_uri", redirect);
            put("state", state.get().add());

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
        throw new UnsupportedOperationException();
    }

    @Override
    public Response<D, E> token(String code, boolean raw) {
        throw new UnsupportedOperationException();
    }
}
