package io.hanbings.flows.dropbox;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Refresh;

public record DropboxRefresh(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        String expiresIn,
        @JsonProperty("token_type")
        @SerializedName("token_type")
        String tokenType
) implements Refresh {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription,
            @JsonProperty("state")
            @SerializedName("state")
            String state
    ) implements Refresh.Wrong {
    }
}
