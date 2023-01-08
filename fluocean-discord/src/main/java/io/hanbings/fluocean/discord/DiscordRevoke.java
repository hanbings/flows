package io.hanbings.fluocean.discord;

import io.hanbings.fluocean.common.interfaces.Revoke;

public record DiscordRevoke() implements Revoke {
    record Wrong() implements Revoke.Wrong {
    }
}
