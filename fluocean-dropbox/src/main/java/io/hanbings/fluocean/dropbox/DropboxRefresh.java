package io.hanbings.fluocean.dropbox;

import com.google.gson.annotations.SerializedName;

public record DropboxRefresh(
        @SerializedName("access_token")
        String accessToken,
        @SerializedName("expires_in")
        String expiresIn,
        @SerializedName("token_type")
        String tokenType
) {
    record Wrong(
            @SerializedName("error")
            String error,
            @SerializedName("error_description")
            String errorDescription,
            @SerializedName("state")
            String state
    ) {
    }
}
