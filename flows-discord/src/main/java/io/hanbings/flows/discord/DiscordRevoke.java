package io.hanbings.flows.discord;

import io.hanbings.flows.common.interfaces.Revoke;

public record DiscordRevoke() implements Revoke {
    record Wrong() implements Revoke.Wrong {
    }
}
