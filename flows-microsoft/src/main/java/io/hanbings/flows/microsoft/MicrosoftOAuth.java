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

package io.hanbings.flows.microsoft;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class MicrosoftOAuth
        extends
        OAuth<MicrosoftAccess, MicrosoftAccess.Wrong>
        implements
        Refreshable<MicrosoftAccess, MicrosoftAccess.Wrong>,
        Revokable<Revoke, Revoke.Wrong>,
        Profilable<Profile, Profile.Wrong>,
        Identifiable<Identify, Identify.Wrong> {
    final String USER_INFO = "https://graph.microsoft.com/v1.0/me";

    private MicrosoftOAuth() {
        super(null, null, null, null);
    }

    public MicrosoftOAuth(String client, String secret, String redirect) {
        super(
                "https://login.microsoftonline.com/common/oauth2/v2.0/authorize",
                "https://login.microsoftonline.com/common/oauth2/v2.0/token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public MicrosoftOAuth(String authorization, String access,
                          String client, String secret, String redirect,
                          List<String> scopes, Map<String, String> params) {
        super(authorization, access, scopes, params);

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public String refreshment() {
        return "https://login.microsoftonline.com/common/oauth2/v2.0/token";
    }

    public String revocation() {
        return "https://login.microsoftonline.com/common/oauth2/v2.0/logout";
    }

    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        return super.authorize(
                scopes == null ? this.scopes() : scopes,
                new HashMap<>() {{
                    if (params != null) putAll(params);
                    put("response_type", "code");
                }}
        );
    }

    @Override
    public Callback<MicrosoftAccess, MicrosoftAccess.Wrong> token(String code, String state, String redirect) {
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
            MicrosoftAccess access = this.serialization()
                    .get()
                    .object(MicrosoftAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);

        }

        if (response.code() == 400) {
            MicrosoftAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(MicrosoftAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<Identify, Identify.Wrong> identify(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(),
                        Map.of(
                                "Authorization", "Bearer " + token
                        )
                );

        if (response.code() == 200) {
            MicrosoftIdentify identify = this.serialization()
                    .get()
                    .object(MicrosoftIdentify.class, response.raw());

            return OAuthCallback.response(token, identify, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            MicrosoftIdentify.Wrong wrong = this.serialization()
                    .get()
                    .object(MicrosoftIdentify.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<Profile, Profile.Wrong> profile(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(),
                        Map.of(
                                "Authorization", "Bearer " + token
                        )
                );

        if (response.code() == 200) {
            MicrosoftProfile profile = this.serialization()
                    .get()
                    .object(MicrosoftProfile.class, response.raw());

            return OAuthCallback.response(token, profile, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            MicrosoftProfile.Wrong wrong = this.serialization()
                    .get()
                    .object(MicrosoftProfile.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<Revoke, Revoke.Wrong> revoke(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        revocation(),
                        Map.of(),
                        Map.of(
                                "Authorization", "Bearer " + token
                        )
                );

        if (response.code() == 200) {
            MicrosoftRevoke revoke = this.serialization()
                    .get()
                    .object(MicrosoftRevoke.class, response.raw());

            return OAuthCallback.response(token, revoke, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            MicrosoftRevoke.Wrong wrong = this.serialization()
                    .get()
                    .object(MicrosoftRevoke.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<MicrosoftAccess, MicrosoftAccess.Wrong> refresh(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        refreshment(),
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
            MicrosoftAccess access = this.serialization()
                    .get()
                    .object(MicrosoftAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);

        }

        if (response.code() == 400) {
            MicrosoftAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(MicrosoftAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
