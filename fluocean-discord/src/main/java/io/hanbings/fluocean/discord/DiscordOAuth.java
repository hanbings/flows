package io.hanbings.fluocean.discord;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthCallback;
import io.hanbings.fluocean.common.interfaces.Callback;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.utils.UrlUtils;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class DiscordOAuth extends OAuth<DiscordAccess, DiscordAccess.Wrong> {
    final String revocation = "https://discord.com/api/oauth2/token/revoke";

    private DiscordOAuth() {
        super(
                "https://discord.com/oauth2/authorize",
                "https://discord.com/api/oauth2/token"
        );
    }

    public DiscordOAuth(String client, String secret, String redirect) {
        super(
                "https://discord.com/oauth2/authorize",
                "https://discord.com/api/oauth2/token"
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        Map<String, String> temp = new HashMap<>() {{
            put("client_id", client());
            put("redirect_uri", redirect());
            put("scope", "identify");
            put("response_type", "code");
            put("state", state().get().add());

            // put scopes
            StringBuilder scope = new StringBuilder();

            IntStream.range(0, scopes.size()).forEach(count -> {
                if (count != 0) {
                    scope.append("%20");
                }

                scope.append(scopes.get(count));
            });

            if (scope.length() != 0) {
                put("scope", scope.toString());
            }

            // merge map
            putAll(params);
        }};

        // url builder
        StringBuilder url = new StringBuilder().append(authorization());

        if (temp.size() != 0) {
            url.append("?");

            // map to string
            temp.forEach((k, v) -> url.append(k).append("=").append(v).append("&"));

            // eat last '&' and return string
            return url.substring(0, url.length() - 1);
        }

        return url.toString();
    }

    @Override
    public Callback<DiscordAccess, DiscordAccess.Wrong> token(String url) {
        String code;
        String state;

        try {
            code = UrlUtils.params(url).get("code");
            state = UrlUtils.params(url).get("state");
        } catch (URISyntaxException e) {
            return OAuthCallback.exception(null, e);
        }

        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        null,
                        this.access(),
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "code", code,
                                "state", state
                        )
                );

        if (response.code() == 200) {
            if (serialization().get().map(String.class, String.class, response.raw()).containsKey("access_token")) {
                DiscordAccess access = this.serialization()
                        .get()
                        .object(DiscordAccess.class, response.raw());

                return OAuthCallback.response(access.accessToken(), access, null);
            } else {
                DiscordAccess.Wrong wrong = this.serialization()
                        .get()
                        .object(DiscordAccess.Wrong.class, response.raw());

                return OAuthCallback.response(null, null, wrong);
            }
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<DiscordAccess, DiscordAccess.Wrong> token(String code, String state) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        null,
                        this.access(),
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "code", code,
                                "state", state
                        )
                );

        if (response.code() == 200) {
            if (serialization().get().map(String.class, String.class, response.raw()).containsKey("access_token")) {
                DiscordAccess access = this.serialization()
                        .get()
                        .object(DiscordAccess.class, response.raw());

                return OAuthCallback.response(access.accessToken(), access, null);
            } else {
                DiscordAccess.Wrong wrong = this.serialization()
                        .get()
                        .object(DiscordAccess.Wrong.class, response.raw());

                return OAuthCallback.response(null, null, wrong);
            }
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
