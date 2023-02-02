package io.hanbings.flows.facebook;

import io.hanbings.flows.common.OAuth;

public class FacebookOAuth extends OAuth<FacebookAccess, FacebookAccess.Wrong> {
    private FacebookOAuth() {
        super(
                "https://www.facebook.com/v15.0/dialog/oauth",
                "https://graph.facebook.com/v15.0/oauth/access_token"
        );
    }
}
