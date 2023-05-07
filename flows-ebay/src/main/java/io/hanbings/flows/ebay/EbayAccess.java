package io.hanbings.flows.ebay;

import io.hanbings.flows.common.interfaces.Access;

public record EbayAccess(
        String accessToken
) implements Access {
    public record Wrong(
            String error,
            String errorDescription
    ) implements Access.Wrong {
    }
}
