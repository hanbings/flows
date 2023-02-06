package io.hanbings.flows.discord;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Access;
import io.hanbings.flows.common.interfaces.Refresh;

public record DiscordAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("token_type")
        @SerializedName("token_type")
        String tokenType,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        long expiresIn,
        @JsonProperty("refresh_token")
        @SerializedName("refresh_token")
        String refreshToken,
        @JsonProperty("scope")
        @SerializedName("scope")
        String scope
) implements Access, Refresh {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error
    ) implements Access.Wrong, Refresh.Wrong {
    }
}
