package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Response;

import java.util.Map;

public record OAuthResponse(
        boolean exception,
        Throwable throwable,
        int code,
        String raw,
        Map<String, String> data
) implements Response {
}
