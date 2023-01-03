package io.hanbings.fluocean.facebook;

import io.hanbings.fluocean.common.OAuth;

public class FacebookOAuth extends OAuth<FacebookAccess, FacebookAccess.Wrong> {
    private FacebookOAuth() {
        super(
                "https://www.facebook.com/v15.0/dialog/oauth",
                "https://graph.facebook.com/v15.0/oauth/access_token"
        );
    }
}
