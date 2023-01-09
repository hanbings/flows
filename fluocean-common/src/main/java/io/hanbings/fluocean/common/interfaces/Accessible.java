package io.hanbings.fluocean.common.interfaces;

import io.hanbings.fluocean.common.OAuthCallback;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Accessible<D extends Access, W extends Access.Wrong> {
    default String authorize() {
        return authorize(List.of(), Map.of());
    }

    default String authorize(List<String> scopes) {
        return authorize(scopes, Map.of());
    }

    default String authorize(Map<String, String> params) {
        return authorize(List.of(), params);
    }

    String authorize(List<String> scopes, Map<String, String> params);

    default Callback<D, W> token(String url) {
        return token(url, null);
    }

    default Callback<D, W> token(String url, String redirect) {
        String code;
        String state;

        try {
            code = Accessible.Utils.params(url).get("code");
            state = Accessible.Utils.params(url).get("state");
        } catch (URISyntaxException e) {
            return OAuthCallback.exception(null, e);
        }

        return token(code, state, redirect);
    }

    Callback<D, W> token(String code, String state, String redirect);

    class Utils {
        public static String params(String url, Map<String, String> params) throws URISyntaxException {
            URI check = new URI(url);

            StringBuilder builder = new StringBuilder();

            if (check.getQuery() != null) {
                builder.append(check.getQuery()).append("&");
            }

            params.forEach((k, v) -> {
                builder.append(k).append("=").append(v).append("&");
            });

            return new URI(
                    check.getScheme(),
                    check.getAuthority(),
                    check.getPath(),
                    builder.toString(),
                    check.getFragment()
            ).toString();
        }

        public static Map<String, String> params(String url) throws URISyntaxException {
            return new HashMap<>() {{
                Arrays.stream(new URI(url).getQuery().split("&")).forEach(s -> {
                    put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=")));
                });
            }};
        }
    }
}
