package io.hanbings.fluocean.github;


import com.google.gson.annotations.SerializedName;

public record GithubAccess(
        @SerializedName("access_token")
        String token,
        @SerializedName("token_type")
        String type,
        @SerializedName("scope")
        String scope
) {
    record Wrong(
            @SerializedName("error_uri")
            String url,
            @SerializedName("error")
            String error,
            @SerializedName("error_description")
            String description
    ) {
    }
}
