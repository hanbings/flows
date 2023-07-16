package io.hanbings.flows.weibo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Identify;

public record WeiboIdentify(
        @JsonProperty("id")
        @SerializedName("id")
        String openid,
        @JsonProperty("avatar_large")
        @SerializedName("avatar_large")
        String avatar,
        @JsonProperty("name")
        @SerializedName("name")
        String username,
        @JsonProperty("screen_name")
        @SerializedName("screen_name")
        String nickname,
        String email,
        String phone
) implements Identify {
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
    ) implements Identify.Wrong {
    }
}
