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

package io.hanbings.flows.dropbox;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.OAuthSerialization;
import io.hanbings.flows.common.interfaces.Callback;
import io.hanbings.flows.common.interfaces.Refreshable;
import io.hanbings.flows.common.interfaces.Response;
import io.hanbings.flows.common.interfaces.Revokable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class DropboxOAuth
        extends
        OAuth<DropboxAccess, DropboxAccess.Wrong>
        implements
        Refreshable<DropboxRefresh, DropboxRefresh.Wrong>,
        Revokable<DropboxRevoke, DropboxRevoke.Wrong> {
    final String refreshment = "https://api.dropboxapi.com/oauth2/token";
    final String revocation = "https://api.dropboxapi.com/2/auth/token/revoke";

    private DropboxOAuth() {
        super(null, null, null, null);
    }

    public DropboxOAuth(String client, String secret, String redirect) {
        super(
                "https://www.dropbox.com/oauth2/authorize",
                "https://api.dropboxapi.com/oauth2/token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public DropboxOAuth(String client, String secret, String redirect,
                        List<String> scopes, Map<String, String> params) {
        super(
                "https://www.dropbox.com/oauth2/authorize",
                "https://api.dropboxapi.com/oauth2/token",
                scopes,
                params
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        return super.authorize(
                scopes,
                new HashMap<>() {{
                    putAll(params);
                    put("response_type", "code");
                }}
        );
    }

    @Override
    public Callback<DropboxAccess, DropboxAccess.Wrong> token(String code, String state, String redirect) {
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
                                "code", code
                        ),
                        Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/x-www-form-urlencoded"
                        )
                );

        if (response.code() == 200) {
            DropboxAccess access = this.serialization()
                    .get()
                    .object(DropboxAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);

        }

        if (response.code() == 400) {
            DropboxAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(DropboxAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<DropboxRevoke, DropboxRevoke.Wrong> revoke(String token) {
        Response response = this.request()
                .get()
                .post(
                        new OAuthSerialization() {
                            @Override
                            public <K, V> Map<K, V> map(Class<K> key, Class<V> value, String raw) {
                                return Map.of();
                            }
                        },
                        this.proxy() == null ? null : this.proxy().get(),
                        this.revocation,
                        Map.of(),
                        Map.of(
                                "Authorization", String.format("Bearer %s", token)
                        )
                );

        if (response.code() == 200) {
            DropboxRevoke access = this.serialization()
                    .get()
                    .object(DropboxRevoke.class, "{ }");

            return OAuthCallback.response(null, access, null, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<DropboxRefresh, DropboxRefresh.Wrong> refresh(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.access(),
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
            DropboxRefresh access = this.serialization()
                    .get()
                    .object(DropboxRefresh.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);

        }

        if (response.code() == 400) {
            DropboxRefresh.Wrong wrong = this.serialization()
                    .get()
                    .object(DropboxRefresh.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
