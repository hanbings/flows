package io.hanbings.flows.github;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.OAuthCallback;
import io.hanbings.flows.common.interfaces.Callback;
import io.hanbings.flows.common.interfaces.Identifiable;
import io.hanbings.flows.common.interfaces.Profilable;
import io.hanbings.flows.common.interfaces.Response;

import java.util.Map;

@SuppressWarnings("unused")
public class GithubOAuth
        extends
        OAuth<GithubAccess, GithubAccess.Wrong>
        implements
        Profilable<GithubProfile, GithubProfile.Wrong>,
        Identifiable<GithubIdentify, GithubIdentify.Wrong> {
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
                        this.proxy() == null ? null : this.proxy().get(),
                        "https://api.github.com/user",
                        Map.of(),
                        Map.of(
                                "Accept", "application/vnd.github+json",
                                "Authorization", String.format("Bearer %s", token),
                                "X-GitHub-Api-Version", "2022-11-28"
                        )
                );

        if (response.code() == 200) {

            GithubProfile profile = this.serialization()
                    .get()
                    .object(GithubProfile.class, response.raw());

            return OAuthCallback.response(token, profile, null, response);

        }

        if (response.code() == 401) {
            GithubProfile.Wrong wrong = this.serialization()
                    .get()
                    .object(GithubProfile.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong, response);
        }

        return response.exception() ?
                OAuthCallback.exception(null, response.throwable()) :
                OAuthCallback.response(response);
    }

    @Override
    public Callback<GithubIdentify, GithubIdentify.Wrong> identify(String token) {
        // headers
        Map<String, String> headers = Map.of(
                "Accept", "application/vnd.github+json",
                "Authorization", String.format("Bearer %s", token),
                "X-GitHub-Api-Version", "2022-11-28"
        );
        // profile
        Response profiles = request()
                .get()
                .get(
                        serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        "https://api.github.com/user",
                        Map.of(),
                        headers
                );
        // email
        Response emails = request()
                .get()
                .get(
                        serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        "https://api.github.com/user/emails",
                        Map.of(),
                        headers
                );
        // serialize
        if (profiles.code() == 200 && (emails.code() == 200 || emails.code() == 404)) {
            // serialize email
            GithubIdentify.Emails email = this.serialization().get().object(GithubIdentify.Emails.class, emails.raw());
            GithubProfile profile = this.serialization().get().object(GithubProfile.class, profiles.raw());

            GithubIdentify identify = new GithubIdentify(
                    String.valueOf(profile.id()),
                    profile.avatarUrl(),
                    profile.login(),
                    profile.name(),
                    email.emails() == null ? null : (email.emails().size() == 0 ? null : email.emails().get(0).email()),
                    null
            );

            return OAuthCallback.response(token, identify, null, profiles);
        }

        return (profiles.exception() || emails.exception()) ?
                profiles.exception() ?
                        OAuthCallback.exception(token, profiles.throwable()) :
                        OAuthCallback.exception(token, emails.throwable()) :
                profiles.code() == 200 ?
                        OAuthCallback.response(profiles) :
                        OAuthCallback.response(emails);
    }
}