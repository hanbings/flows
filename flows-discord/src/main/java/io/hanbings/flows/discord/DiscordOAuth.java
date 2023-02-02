package io.hanbings.flows.discord;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.interfaces.Callback;
import io.hanbings.flows.common.interfaces.Refreshable;
import io.hanbings.flows.common.interfaces.Response;
import io.hanbings.flows.common.interfaces.Revokable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class DiscordOAuth
        extends
        OAuth<DiscordAccess, DiscordAccess.Wrong>
        implements
        Refreshable<DiscordAccess, DiscordAccess.Wrong>,
        Revokable<DiscordRevoke, DiscordRevoke.Wrong> {
    final String refreshment = "https://discord.com/api/oauth2/token";
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
    public Callback<DiscordAccess, DiscordAccess.Wrong> token(String code, String state, String redirect) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.access(),
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "grant_type", "authorization_code",
                                "redirect_uri", redirect,
                                "code", code,
                                "state", state
                        ),
                        Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/x-www-form-urlencoded"
                        )
                );

        if (response.code() == 200) {
            DiscordAccess access = this.serialization()
                    .get()
                    .object(DiscordAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);

        }

        if (response.code() == 400) {
            DiscordAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(DiscordAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<DiscordAccess, DiscordAccess.Wrong> refresh(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.refreshment,
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "grant_type", "refresh_token",
                                "refresh_token", token
                        ),
                        Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/x-www-form-urlencoded"
                        )
                );

        if (response.code() == 200) {
            DiscordAccess access = this.serialization()
                    .get()
                    .object(DiscordAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);

        }

        if (response.code() == 400) {
            DiscordAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(DiscordAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<DiscordRevoke, DiscordRevoke.Wrong> revoke(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.revocation,
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "token", token
                        ),
                        Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/x-www-form-urlencoded"
                        )
                );

        if (response.code() == 200) {
            DiscordRevoke access = this.serialization()
                    .get()
                    .object(DiscordRevoke.class, response.raw());

            return OAuthCallback.response(null, access, null, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}