package io.hanbings.fluocean.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.fluocean.common.interfaces.Access;

public record GoogleAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        String expiresIn,
        @JsonProperty("refresh_token")
        @SerializedName("refresh_token")
        String refreshToken,
        @JsonProperty("scope")
        @SerializedName("scope")
        String scope,
        @JsonProperty("token_type")
        @SerializedName("token_type")
        String tokenType
) implements Access {
    record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription
    ) implements Access.Wrong {
    }
}
