package io.hanbings.fluocean.github;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthRequest;
import io.hanbings.fluocean.common.OAuthResponse;

public class GithubOAuth extends OAuth<OAuthRequest<OAuthResponse>, OAuthResponse> {
    public GithubOAuth() {
        super(
                "https://github.com/login/oauth/authorize",
                "https://github.com/login/oauth/access_token"
        );
    }
}
