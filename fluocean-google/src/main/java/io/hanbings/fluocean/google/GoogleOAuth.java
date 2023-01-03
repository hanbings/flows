package io.hanbings.fluocean.google;

import io.hanbings.fluocean.common.OAuth;

public class GoogleOAuth extends OAuth<GoogleAccess, GoogleAccess.Wrong> {
    private GoogleOAuth() {
        super(
                "https://accounts.google.com/o/oauth2/auth",
                "https://www.googleapis.com/oauth2/v3/token"
        );
    }
}
