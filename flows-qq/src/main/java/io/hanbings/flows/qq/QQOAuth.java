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

package io.hanbings.flows.qq;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class QQOAuth
        extends
        OAuth<QQAccess, QQAccess.Wrong>
        implements
        Refreshable<QQAccess, QQAccess.Wrong>,
        Profilable<QQProfile, QQProfile.Wrong>,
        Identifiable<QQIdentify, QQIdentify.Wrong> {
    final String USER_INFO = "https://graph.qq.com/user/get_user_info";
    final String OPENID = "https://graph.qq.com/oauth2.0/me";

    private QQOAuth() {
        super(null, null, null, null);
    }

    public QQOAuth(String client, String secret, String redirect) {
        super(
                "https://graph.qq.com/oauth2.0/authorize",
                "https://graph.qq.com/oauth2.0/token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public QQOAuth(String client, String secret, String redirect,
                   List<String> scopes, Map<String, String> params) {
        super(
                "https://graph.qq.com/oauth2.0/authorize",
                "https://graph.qq.com/oauth2.0/token",
                scopes,
                params
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        // List<String> sco = scopes == null ? this.scopes() : scopes;
        Map<String, String> par = params == null ? this.params() : params;

        Map<String, String> temp = new HashMap<>() {{
            put("response_type", "code");
            put("client_id", client());
            put("redirect_uri", redirect());
            put("state", "state");
            put("scope", "get_user_info");

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
    public Callback<QQAccess, QQAccess.Wrong> token(String code, String state, String redirect) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.access(),
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "code", code,
                                "grant_type", "authorization_code",
                                "redirect_uri", redirect,
                                "fmt", "json"
                        )
                );

        if (response.code() == 200) {
            QQAccess access = this.serialization()
                    .get()
                    .object(QQAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            QQAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(QQAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<QQIdentify, QQIdentify.Wrong> identify(String token) {
        String openid = this.getOpenid(token);

        Response response = this.request()
                .get()
                .get(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(
                                "access_token", token,
                                "oauth_consumer_key", this.client(),
                                "openid", openid,
                                "fmt", "json"
                        )
                );

        if (response.code() == 200) {
            QQIdentify identify = this.serialization()
                    .get()
                    .object(QQIdentify.class, response.raw());

            return OAuthCallback.response(
                    token,
                    new QQIdentify(
                            openid,
                            identify.avatar(),
                            identify.username(),
                            identify.nickname(),
                            identify.email(),
                            identify.phone()
                    ),
                    null,
                    response
            );
        }

        if (response.code() != 200 && response.code() != 0) {
            QQIdentify.Wrong wrong = this.serialization()
                    .get()
                    .object(QQIdentify.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<QQProfile, QQProfile.Wrong> profile(String token) {
        String openid = this.getOpenid(token);

        Response response = this.request()
                .get()
                .get(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(
                                "access_token", token,
                                "oauth_consumer_key", this.client(),
                                "openid", openid,
                                "fmt", "json"
                        )
                );

        if (response.code() == 200) {
            QQProfile profile = this.serialization()
                    .get()
                    .object(QQProfile.class, response.raw());

            return OAuthCallback.response(token, profile, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            QQProfile.Wrong wrong = this.serialization()
                    .get()
                    .object(QQProfile.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    private String getOpenid(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        OPENID,
                        Map.of(
                                "access_token", token,
                                "fmt", "json"
                        )
                );

        if (response.code() == 200) {
            QQIdentify identify = this.serialization()
                    .get()
                    .object(QQIdentify.class, response.raw());

            return identify.openid();
        }

        return "";
    }

    @Override
    public Callback<QQAccess, QQAccess.Wrong> refresh(String token) {
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
                                "refresh_token", token,
                                "fmt", "json"
                        )
                );

        if (response.code() == 200) {
            QQAccess access = this.serialization()
                    .get()
                    .object(QQAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            QQAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(QQAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
