package io.hanbings.fluocean.dropbox;

import io.hanbings.fluocean.common.interfaces.Revoke;

public record DropboxRevoke() implements Revoke {
    record Wrong() implements Revoke.Wrong {
    }
}
