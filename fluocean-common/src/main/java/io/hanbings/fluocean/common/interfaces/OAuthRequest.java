package io.hanbings.fluocean.common.interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
@Accessors(fluent = true, chain = true)
public abstract class OAuthRequest {
    @Setter
    @Getter
    @RequiredArgsConstructor
    @SuppressWarnings("unused")
    @Accessors(fluent = true, chain = true)
    static class OAuthProxy {
        String host;
        String port;
        String username;
        String password;
    }
}
