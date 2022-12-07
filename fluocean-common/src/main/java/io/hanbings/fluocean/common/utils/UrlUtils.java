package io.hanbings.fluocean.common.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UrlUtils {
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
