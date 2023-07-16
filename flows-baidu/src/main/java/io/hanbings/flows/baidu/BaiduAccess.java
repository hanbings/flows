package io.hanbings.flows.baidu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Access;
import io.hanbings.flows.common.interfaces.Refresh;

public record BaiduAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        long expiresIn,
        @JsonProperty("refresh_token")
        @SerializedName("refresh_token")
        String refreshToken,
        @JsonProperty("scope")
        @SerializedName("scope")
        String scope,
        @JsonProperty("session_key")
        @SerializedName("session_key")
        String sessionKey,
        @JsonProperty("session_secret")
        @SerializedName("session_secret")
        String sessionSecret
) implements Access, Refresh {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String code,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String error
    ) implements Access.Wrong, Refresh.Wrong {
    }
}
