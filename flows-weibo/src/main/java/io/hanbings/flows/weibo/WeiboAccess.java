package io.hanbings.flows.weibo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Access;

public record WeiboAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        long expiresIn,
        @JsonProperty("remind_in")
        @SerializedName("remind_in")
        String remindIn,
        @JsonProperty("uid")
        @SerializedName("uid")
        String uid
) implements Access {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_code")
            @SerializedName("error_code")
            int code,
            @JsonProperty("request")
            @SerializedName("request")
            String request
    ) implements Access.Wrong {
    }
}
