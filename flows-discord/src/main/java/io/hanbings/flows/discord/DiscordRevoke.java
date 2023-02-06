package io.hanbings.flows.discord;

import io.hanbings.flows.common.interfaces.Revoke;

public record DiscordRevoke() implements Revoke {
    public record Wrong() implements Revoke.Wrong {
    }
}
