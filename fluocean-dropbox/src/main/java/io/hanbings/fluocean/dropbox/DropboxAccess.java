package io.hanbings.fluocean.dropbox;

import com.google.gson.annotations.SerializedName;

public record DropboxAccess(
        @SerializedName("access_token")
        String accessToken,
        @SerializedName("expires_in")
        String expiresIn,
        @SerializedName("token_type")
        String tokenType,
        @SerializedName("scope")
        String scope,
        @SerializedName("account_id")
        String accountId,
        @SerializedName("team_id")
        String teamId,
        @SerializedName("refresh_token")
        String refreshToken,
        @SerializedName("id_token")
        String idToken,
        @SerializedName("uid")
        String uid
) {
    record Wrong(@SerializedName("error")
                 String error,
                 @SerializedName("error_description")
                 String errorDescription,
                 @SerializedName("state")
                 String state) {
    }
}
