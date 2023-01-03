package io.hanbings.fluocean.github;


import com.google.gson.annotations.SerializedName;

public record GithubAccess(
        @SerializedName("access_token")
        String accessToken,
        @SerializedName("token_type")
        String tokenType,
        @SerializedName("scope")
        String scope
) {
    record Wrong(
            @SerializedName("error_uri")
            String errorUri,
            @SerializedName("error")
            String error,
            @SerializedName("error_description")
            String errorDescription
    ) {
    }
}
