package io.hanbings.fluocean.discord;

import com.google.gson.annotations.SerializedName;

public record DiscordAccess(
        @SerializedName("access_token")
        String accessToken,
        @SerializedName("token_type")
        String tokenType,
        @SerializedName("expires_in")
        long expiresIn,
        @SerializedName("refresh_token")
        String refreshToken,
        @SerializedName("scope")
        String scope
) {
    record Wrong(
            @SerializedName("error")
            String error
    ) {
    }
}
