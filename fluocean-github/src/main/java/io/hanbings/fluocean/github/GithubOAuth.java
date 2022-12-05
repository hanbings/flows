package io.hanbings.fluocean.github;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthAccess;

public class GithubOAuth extends OAuth<String, OAuthAccess, OAuthAccess.Error> {
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
}
