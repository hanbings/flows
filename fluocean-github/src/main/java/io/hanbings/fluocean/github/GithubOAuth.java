package io.hanbings.fluocean.github;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthCallback;
import io.hanbings.fluocean.common.interfaces.Callback;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.utils.UrlUtils;

import java.net.URISyntaxException;
import java.util.Map;

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
    public Callback<GithubAccess, GithubAccess.Wrong> token(String url) {
        return token(url, true);
    }

    @Override
    public Callback<GithubAccess, GithubAccess.Wrong> token(String url, boolean raw) {
        String code = url;
        try {
            code = raw ? UrlUtils.params(url).get("code") : url;
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
                                "code", code
                        )
                );

        if (response.code() == 200) {
            if (serialization().get().map(String.class, String.class, response.raw()).containsKey("access_token")) {
                GithubAccess access = this.serialization()
                        .get()
                        .object(GithubAccess.class, response.raw());

                return OAuthCallback.response(access.token(), access, null);
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

    public static void main(String... args) {
        OAuth<GithubAccess, GithubAccess.Wrong> oauth = new GithubOAuth(
                "98f0fbc315f6de388ac5",
                "91cea69ced505382a6cfb7e2673716cbb7d869f5",
                "https://nestsid.com/api/v0/login/oauth/github/callback"
        );

        System.out.println(oauth.token("a78d4d35f3780a8a9f28", false));
    }
}
