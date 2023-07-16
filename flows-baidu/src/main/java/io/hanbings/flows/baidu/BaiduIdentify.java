package io.hanbings.flows.baidu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Identify;

@SuppressWarnings("SpellCheckingInspection")
public record BaiduIdentify(
        @JsonProperty("openid")
        @SerializedName("openid")
        String openid,
        @JsonProperty("portrait")
        @SerializedName("portrait")
        String avatar,
        @JsonProperty("username")
        @SerializedName("username")
        String username,
        @JsonProperty("username")
        @SerializedName("username")
        String nickname,
        String email,
        @JsonProperty("securemobile")
        @SerializedName("securemobile")
        String phone
) implements Identify {
    public record Wrong(
            @JsonProperty("error_msg")
            @SerializedName("error_msg")
            String error,
            @JsonProperty("error_code")
            @SerializedName("error_code")
            int code
    ) implements Identify.Wrong {
    }
}
