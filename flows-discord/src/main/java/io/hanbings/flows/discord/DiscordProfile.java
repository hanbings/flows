package io.hanbings.flows.discord;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public record DiscordProfile(
        @JsonProperty("premium_type")
        @SerializedName("premium_type")
        int premiumType,
        @JsonProperty("accent_color")
        @SerializedName("accent_color")
        int accentColor,
        @JsonProperty("verified")
        @SerializedName("verified")
        boolean verified,
        @JsonProperty("flags")
        @SerializedName("flags")
        int flags,
        @JsonProperty("banner")
        @SerializedName("banner")
        String banner,
        @JsonProperty("id")
        @SerializedName("id")
        String id,
        @JsonProperty("avatar")
        @SerializedName("avatar")
        String avatar,
        @JsonProperty("public_flags")
        @SerializedName("public_flags")
        int publicFlags,
        @JsonProperty("email")
        @SerializedName("email")
        String email,
        @JsonProperty("username")
        @SerializedName("username")
        String username,
        @JsonProperty("discriminator")
        @SerializedName("discriminator")
        String discriminator
) {
    public record Wrong(
            @JsonProperty("code")
            @SerializedName("code")
            int code,
            @JsonProperty("errors")
            @SerializedName("errors")
            Object errors,
            @JsonProperty("message")
            @SerializedName("message")
            String message
    ) {
    }
}