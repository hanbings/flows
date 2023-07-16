package io.hanbings.flows.baidu;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class BaiduOAuth
        extends
        OAuth<BaiduAccess, BaiduAccess.Wrong>
        implements
        Refreshable<BaiduAccess, BaiduAccess.Wrong>,
        Profilable<BaiduProfile, BaiduProfile.Wrong>,
        Identifiable<BaiduIdentify, BaiduIdentify.Wrong> {
    final String USER_INFO = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";
    final String refreshment = "https://openapi.baidu.com/oauth/2.0/token";

    private BaiduOAuth() {
        super(null, null, null, null);
    }

    public BaiduOAuth(String client, String secret, String redirect) {
        super(
                "https://openapi.baidu.com/oauth/2.0/authorize",
                "https://openapi.baidu.com/oauth/2.0/token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public BaiduOAuth(String client, String secret, String redirect,
                      List<String> scopes, Map<String, String> params) {
        super(
                "https://openapi.baidu.com/oauth/2.0/authorize",
                "https://openapi.baidu.com/oauth/2.0/token",
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
            put("response_type", "code");
            put("scope", "basic");
            put("state", state().get().add());

            if (sco != null) put("scope", sco.get(0));

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
    public Callback<BaiduAccess, BaiduAccess.Wrong> token(String code, String state, String redirect) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.access(),
                        Map.of(
                                "grant_type", "authorization_code",
                                "code", code,
                                "client_id", client(),
                                "client_secret", secret(),
                                "redirect_uri", redirect == null ? this.redirect() : redirect
                        )
                );

        if (response.code() == 200) {
            BaiduAccess access = this.serialization()
                    .get()
                    .object(BaiduAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            BaiduAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(BaiduAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<BaiduIdentify, BaiduIdentify.Wrong> identify(String token) {
        Response response = this.request()
                .get()
                .get(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(
                                "access_token", token
                        )
                );

        if (response.code() == 200) {
            BaiduIdentify identify = this.serialization()
                    .get()
                    .object(BaiduIdentify.class, response.raw());

            return OAuthCallback.response(token, identify, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            BaiduIdentify.Wrong wrong = this.serialization()
                    .get()
                    .object(BaiduIdentify.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<BaiduProfile, BaiduProfile.Wrong> profile(String token) {
        Response response = this.request()
                .get()
                .get(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        USER_INFO,
                        Map.of(
                                "access_token", token
                        )
                );

        if (response.code() == 200) {
            BaiduProfile profile = this.serialization()
                    .get()
                    .object(BaiduProfile.class, response.raw());

            return OAuthCallback.response(token, profile, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            BaiduProfile.Wrong wrong = this.serialization()
                    .get()
                    .object(BaiduProfile.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<BaiduAccess, BaiduAccess.Wrong> refresh(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        refreshment,
                        Map.of(
                                "grant_type", "refresh_token",
                                "refresh_token", token,
                                "client_id", client(),
                                "client_secret", secret()
                        )
                );

        if (response.code() == 200) {
            BaiduAccess access = this.serialization()
                    .get()
                    .object(BaiduAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            BaiduAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(BaiduAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
