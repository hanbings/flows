package io.hanbings.flows.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Identify;

import java.util.List;

public record GithubIdentify(
        @JsonProperty("id")
        @SerializedName("id")
        String openid,
        @JsonProperty("avatar_url")
        @SerializedName("avatar_url")
        String avatar,
        @JsonProperty("login")
        @SerializedName("login")
        String username,
        @JsonProperty("name")
        @SerializedName("name")
        String nickname,
        @JsonProperty("email")
        @SerializedName("email")
        String email,
        String phone
) implements Identify {
    record Wrong(
            @JsonProperty("message")
            @SerializedName("message")
            String message,
            @JsonProperty("documentation_url")
            @SerializedName("documentation_url")
            String documentationUrl
    ) implements Identify.Wrong {
    }

    record Emails(
            List<Email> emails
    ) {

    }

    record Email(
            @JsonProperty("visibility")
            @SerializedName("visibility")
            String visibility,
            @JsonProperty("verified")
            @SerializedName("verified")
            boolean verified,
            @JsonProperty("email")
            @SerializedName("email")
            String email,
            @JsonProperty("primary")
            @SerializedName("primary")
            boolean primary
    ) {

    }
}
