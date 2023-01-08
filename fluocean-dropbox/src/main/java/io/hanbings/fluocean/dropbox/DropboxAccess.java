package io.hanbings.fluocean.dropbox;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.fluocean.common.interfaces.Access;

public record DropboxAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        String expiresIn,
        @JsonProperty("token_type")
        @SerializedName("token_type")
        String tokenType,
        @JsonProperty("scope")
        @SerializedName("scope")
        String scope,
        @JsonProperty("account_id")
        @SerializedName("account_id")
        String accountId,
        @JsonProperty("team_id")
        @SerializedName("team_id")
        String teamId,
        @JsonProperty("refresh_token")
        @SerializedName("refresh_token")
        String refreshToken,
        @JsonProperty("id_token")
        @SerializedName("id_token")
        String idToken,
        @JsonProperty("uid")
        @SerializedName("uid")
        String uid
) implements Access {
    record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription,
            @JsonProperty("state")
            @SerializedName("state")
            String state
    ) implements Access.Wrong {
    }
}
