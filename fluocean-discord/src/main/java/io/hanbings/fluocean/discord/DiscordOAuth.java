package io.hanbings.fluocean.discord;

import io.hanbings.fluocean.common.OAuth;

public class DiscordOAuth extends OAuth<DiscordAccess, DiscordAccess.Wrong> {
    final String revocation = "https://discord.com/api/oauth2/token/revoke";

    private DiscordOAuth() {
        super(
                "https://discord.com/oauth2/authorize",
                "https://discord.com/api/oauth2/token"
        );
    }

    public DiscordOAuth(String client, String secret, String redirect) {
        super(
                "https://discord.com/oauth2/authorize",
                "https://discord.com/api/oauth2/token"
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }
}
