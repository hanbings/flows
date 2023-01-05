package io.hanbings.fluocean.dropbox;

import io.hanbings.fluocean.common.OAuth;

public class DropboxOAuth extends OAuth<DropboxAccess, DropboxAccess.Wrong> {
    final String refreshment = "https://api.dropboxapi.com/oauth2/token";
    final String revocation = "https://api.dropboxapi.com/2/auth/token/revoke";

    private DropboxOAuth() {
        super(
                "https://www.dropbox.com/oauth2/authorize",
                "https://api.dropboxapi.com/oauth2/token"
        );
    }
}
