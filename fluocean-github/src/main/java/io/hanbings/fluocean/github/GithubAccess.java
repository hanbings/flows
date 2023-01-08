package io.hanbings.fluocean.github;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.fluocean.common.interfaces.Access;

public record GithubAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("token_type")
        @SerializedName("token_type")
        String tokenType,
        @JsonProperty("scope")
        @SerializedName("scope")
        String scope
) implements Access {
    record Wrong(
            @JsonProperty("error_uri")
            @SerializedName("error_uri")
            String errorUri,
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription
    ) implements Access.Wrong {
    }
}
