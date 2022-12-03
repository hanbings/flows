package io.hanbings.fluocean.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public interface Request {
    @Setter
    @Getter
    @AllArgsConstructor
    @SuppressWarnings("unused")
    @Accessors(fluent = true, chain = true)
    class Proxy {
        String host;
        String port;
        String username;
        String password;
    }
}
