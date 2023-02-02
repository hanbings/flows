package io.hanbings.flows.common;

import io.hanbings.flows.common.interfaces.Response;

import java.util.Map;

public record OAuthResponse(
        boolean exception,
        Throwable throwable,
        int code,
        String raw,
        Map<String, String> data
) implements Response {
}
