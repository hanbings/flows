package io.hanbings.flows.weibo;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class WeiboOAuth
        extends
        OAuth<WeiboAccess, WeiboAccess.Wrong>
        implements
        Revokable<WeiboRevoke, WeiboRevoke.Wrong>,
        Profilable<WeiboProfile, WeiboProfile.Wrong>,
        Identifiable<WeiboIdentify, WeiboIdentify.Wrong> {
    final String USER_INFO = "https://api.weibo.com/2/users/show.json";
    final String revocation = "https://api.weibo.com/oauth2/revokeoauth2";

    private WeiboOAuth() {
        super(null, null, null, null);
    }

    public WeiboOAuth(String client, String secret, String redirect) {
        super(
                "https://api.weibo.com/oauth2/authorize",
                "https://api.weibo.com/oauth2/access_token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public WeiboOAuth(String client, String secret, String redirect,
                      List<String> scopes, Map<String, String> params) {
        super(
                "https://api.weibo.com/oauth2/authorize",
                "https://api.weibo.com/oauth2/access_token",
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
            put("scope", String.join(",", sco));
            put("state", state().get().add());

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
    public Callback<WeiboAccess, WeiboAccess.Wrong> token(String code, String state, String redirect) {
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
                                "code", code,
                                "redirect_uri", redirect
                        )
                );

        if (response.code() == 200) {
            WeiboAccess access = this.serialization()
                    .get()
                    .object(WeiboAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            WeiboAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(WeiboAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<WeiboIdentify, WeiboIdentify.Wrong> identify(String token) {
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
            WeiboIdentify identify = this.serialization()
                    .get()
                    .object(WeiboIdentify.class, response.raw());

            return OAuthCallback.response(token, identify, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            WeiboIdentify.Wrong wrong = this.serialization()
                    .get()
                    .object(WeiboIdentify.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<WeiboProfile, WeiboProfile.Wrong> profile(String token) {
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
            WeiboProfile profile = this.serialization()
                    .get()
                    .object(WeiboProfile.class, response.raw());

            return OAuthCallback.response(token, profile, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            WeiboProfile.Wrong wrong = this.serialization()
                    .get()
                    .object(WeiboProfile.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<WeiboRevoke, WeiboRevoke.Wrong> revoke(String token) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        revocation,
                        Map.of(
                                "access_token", token
                        )
                );

        if (response.code() == 200) {
            WeiboRevoke revoke = this.serialization()
                    .get()
                    .object(WeiboRevoke.class, response.raw());

            return OAuthCallback.response(token, revoke, null, response);
        }

        if (response.code() != 200 && response.code() != 0) {
            WeiboRevoke.Wrong wrong = this.serialization()
                    .get()
                    .object(WeiboRevoke.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
