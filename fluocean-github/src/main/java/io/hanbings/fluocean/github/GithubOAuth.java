package io.hanbings.fluocean.github;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthCallback;
import io.hanbings.fluocean.common.interfaces.Callback;
import io.hanbings.fluocean.common.interfaces.Response;

import java.util.Map;

@SuppressWarnings("unused")
public class GithubOAuth extends OAuth<GithubAccess, GithubAccess.Wrong> {
    private GithubOAuth() {
        super(
                "https://github.com/login/oauth/authorize",
                "https://github.com/login/oauth/access_token"
        );
    }

    public GithubOAuth(String client, String secret, String redirect) {
        super(
                "https://github.com/login/oauth/authorize",
                "https://github.com/login/oauth/access_token"
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public Callback<GithubAccess, GithubAccess.Wrong> token(String code, String state, String redirect) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.access(),
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
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
            if (serialization().get().map(String.class, String.class, response.raw()).containsKey("access_token")) {
                GithubAccess access = this.serialization()
                        .get()
                        .object(GithubAccess.class, response.raw());

                return OAuthCallback.response(access.accessToken(), access, null);
            } else {
                GithubAccess.Wrong wrong = this.serialization()
                        .get()
                        .object(GithubAccess.Wrong.class, response.raw());

                return OAuthCallback.response(null, null, wrong);
            }
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
