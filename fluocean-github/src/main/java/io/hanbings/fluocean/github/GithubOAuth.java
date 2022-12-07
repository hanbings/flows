package io.hanbings.fluocean.github;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthCallback;
import io.hanbings.fluocean.common.interfaces.Callback;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.utils.UrlUtils;

import java.net.URISyntaxException;
import java.util.Map;

public class GithubOAuth extends OAuth<GithubAccess, GithubAccess.Error> {
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
    public Callback<GithubAccess, GithubAccess.Error> token(String url) {
        return token(url, true);
    }

    @Override
    public Callback<GithubAccess, GithubAccess.Error> token(String url, boolean raw) {
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
            if (serialization().get().map(String.class, String.class, response.raw()).containsKey("")) {
                GithubAccess access = this.serialization()
                        .get()
                        .object(GithubAccess.class, response.raw());

                return OAuthCallback.response(access.token(), access, null);
            } else {
                GithubAccess.Error error = this.serialization()
                        .get()
                        .object(GithubAccess.Error.class, response.raw());

                return OAuthCallback.response(null, null, error);
            }
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }
}
