package io.hanbings.fluocean.google;

import com.google.gson.annotations.SerializedName;

public record GoogleAccess(
        @SerializedName("access_token")
        String accessToken,
        @SerializedName("expires_in")
        String expiresIn,
        @SerializedName("refresh_token")
        String refreshToken,
        @SerializedName("scope")
        String scope,
        @SerializedName("token_type")
        String tokenType
) {
    record Wrong(
            @SerializedName("error")
            String error,
            @SerializedName("error_description")
            String errorDescription
    ) {
    }
}
