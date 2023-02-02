package io.hanbings.flows.dropbox;

import io.hanbings.flows.common.interfaces.Revoke;

public record DropboxRevoke() implements Revoke {
    record Wrong() implements Revoke.Wrong {
    }
}
