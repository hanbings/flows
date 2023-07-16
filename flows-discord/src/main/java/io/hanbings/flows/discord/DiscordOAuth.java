/*
 * Copyright 2023 Flows
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.hanbings.flows.discord;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Discord OAuth
 * <a href="https://discord.com/developers/docs/topics/oauth2">Discord OAuth2</a>
 * 默认的 scope 是 identify
 * default scope is identify
 * 警告： 使用 profile 至少需要 scope: identify 权限 使用 identify 至少需要 scope: identify email
 * warning: profile need scope: identify permission at least identify need scope: identify email permission at least
 * <a href="https://discord.com/developers/docs/topics/oauth2#shared-resources-oauth2-scopes">Discord Scopes</a>
 */
@SuppressWarnings("unused")
public class DiscordOAuth
        extends
        OAuth<DiscordAccess, DiscordAccess.Wrong>
        implements
        Refreshable<DiscordAccess, DiscordAccess.Wrong>,
        Revokable<DiscordRevoke, DiscordRevoke.Wrong>,
        Profilable<DiscordProfile, DiscordProfile.Wrong>,
        Identifiable<DiscordIdentify, DiscordIdentify.Wrong> {
    final String USER_INFO = "https://discord.com/api/users/@me";
    final String refreshment = "https://discord.com/api/oauth2/token";
    final String revocation = "https://discord.com/api/oauth2/token/revoke";

    private DiscordOAuth() {
        super(null, null, null, null);
    }

    public DiscordOAuth(String client, String secret, String redirect) {
        super(
                "https://discord.com/oauth2/authorize",
                "https://discord.com/api/oauth2/token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public DiscordOAuth(String client, String secret, String redirect,
                        List<String> scopes, Map<String, String> params) {
        super(
                "https://discord.com/oauth2/authorize",
                "https://discord.com/api/oauth2/token",
                scopes,
                params
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        List<String> sco = scopes == null ? this.scopes() : scopes;
        Map<String, String> par = params == null ? this.params() : params;

        Map<String, String> temp = new HashMap<>() {{
            put("client_id", client());
            put("redirect_uri", redirect());
            put("scope", "identify");
            put("response_type", "code");
            put("state", state().get().add());

            // put scopes
            StringBuilder scope = new StringBuilder();

            IntStream.range(0, sco.size()).forEach(count -> {
                if (count != 0) {
                    scope.append("%20");
                }

                scope.append(sco.get(count));
            });

            if (scope.length() != 0) {
                put("scope", scope.toString());
            }

            // merge map
            putAll(par);
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
    public Callback<DiscordProfile, DiscordProfile.Wrong> profile(String token) {
        Response response = request()
                .get()
                .get(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(),
                        Map.of(
                                "Authorization", String.format("Bearer %s", token)
                        )
                );

        if (response.code() == 200) {

            DiscordProfile profile = this.serialization()
                    .get()
                    .object(DiscordProfile.class, response.raw());

            return OAuthCallback.response(token, profile, null, response);

        }

        if (response.code() == 400) {
            DiscordProfile.Wrong wrong = this.serialization()
                    .get()
                    .object(DiscordProfile.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return response.exception()
                ? OAuthCallback.exception(null, response.throwable())
                : OAuthCallback.response(response);

    }

    @Override
    public Callback<DiscordIdentify, DiscordIdentify.Wrong> identify(String token) {
        Response response = request()
                .get()
                .get(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(),
                        Map.of(
                                "Authorization", String.format("Bearer %s", token)
                        )
                );

        if (response.code() == 200) {
            DiscordProfile profile = this.serialization()
                    .get()
                    .object(DiscordProfile.class, response.raw());

            DiscordIdentify identify = new DiscordIdentify(
                    profile.id(),
                    String.format("https://cdn.discordapp.com/avatars/%s/%s.png", profile.id(), profile.avatar()),
                    profile.username(),
                    profile.discriminator(),
                    serialization().get().map(String.class, String.class, response.raw()).getOrDefault("email", null),
                    ""
            );

            return OAuthCallback.response(token, identify, null, response);

        }

        if (response.code() == 400) {
            DiscordIdentify.Wrong wrong = this.serialization()
                    .get()
                    .object(DiscordIdentify.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return response.exception()
                ? OAuthCallback.exception(null, response.throwable())
                : OAuthCallback.response(response);
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
