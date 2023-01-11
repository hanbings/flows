package io.hanbings.fluocean.github;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthCallback;
import io.hanbings.fluocean.common.interfaces.*;

import java.util.Map;

@SuppressWarnings("unused")
public class GithubOAuth
        extends
        OAuth<GithubAccess, GithubAccess.Wrong>
        implements
        Profilable<GithubProfile, GithubProfile.Wrong>,
        Identifiable<Identify, Identify.Wrong> {
    final String identification = "https://api.github.com/user";

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

                return OAuthCallback.response(access.accessToken(), access, null, response);
            } else {
                GithubAccess.Wrong wrong = this.serialization()
                        .get()
                        .object(GithubAccess.Wrong.class, response.raw());

                return OAuthCallback.response(null, null, wrong, response);
            }
        }

        return response.exception() ?
                OAuthCallback.exception(null, response.throwable()) :
                OAuthCallback.response(response);
    }

    @Override
    public Callback<GithubProfile, GithubProfile.Wrong> profile(String token) {
        Response response = request()
                .get()
                .get(
                        this.serialization().get(),
                        this.proxy().get(),
                        this.identification,
                        Map.of(),
                        Map.of(
                                "Accept", "application/vnd.github+json",
                                "Authorization", String.format("Bearer %s", token),
                                "X-GitHub-Api-Version", "2022-11-28"
                        )
                );

        if (response.code() == 200) {
            if (serialization().get().map(String.class, String.class, response.raw()).containsKey("id")) {
                GithubProfile profile = this.serialization()
                        .get()
                        .object(GithubProfile.class, response.raw());

                return OAuthCallback.response(token, profile, null, response);
            } else {
                GithubProfile.Wrong wrong = this.serialization()
                        .get()
                        .object(GithubProfile.Wrong.class, response.raw());

                return OAuthCallback.response(null, null, wrong, response);
            }
        }

        return response.exception() ?
                OAuthCallback.exception(null, response.throwable()) :
                OAuthCallback.response(response);
    }

    @Override
    public Callback<GithubProfile, GithubProfile.Wrong> profile(String code, String state, String redirect) {
        Callback<GithubAccess, GithubAccess.Wrong> callback = token(code, state, redirect);

        if (callback.success()) {
            return profile(callback.data().accessToken());
        }

        return callback.throwable() == null ?
                OAuthCallback.exception(null, callback.throwable()) :
                OAuthCallback.response(callback.response());
    }

    @Override
    public Callback<Identify, Identify.Wrong> identify(String token) {

        return null;
    }

    @Override
    public Callback<Identify, Identify.Wrong> identify(String code, String state, String redirect) {
        Callback<GithubAccess, GithubAccess.Wrong> callback = token(code, state, redirect);

        if (callback.success()) {
            return identify(callback.data().accessToken());
        }

        return callback.throwable() == null ?
                OAuthCallback.exception(null, callback.throwable()) :
                OAuthCallback.response(callback.response());
    }
}
