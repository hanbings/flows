package io.hanbings.fluocean.github;


import com.google.gson.annotations.SerializedName;
import io.hanbings.fluocean.common.interfaces.Access;

public record GithubAccess(
        @SerializedName("access_token")
        String accessToken,
        @SerializedName("token_type")
        String tokenType,
        @SerializedName("scope")
        String scope
) implements Access {
    record Wrong(
            @SerializedName("error_uri")
            String errorUri,
            @SerializedName("error")
            String error,
            @SerializedName("error_description")
            String errorDescription
    ) implements Access.Wrong {
    }
}
