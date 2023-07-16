package io.hanbings.flows.qq;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Identify;

@SuppressWarnings("SpellCheckingInspection")
public record QQIdentify(
        @JsonProperty("openid")
        @SerializedName("openid")
        String openid,
        @JsonProperty("figureurl_qq_1")
        @SerializedName("figureurl_qq_1")
        String avatar,
        @JsonProperty("nickname")
        @SerializedName("nickname")
        String username,
        @JsonProperty("name")
        @SerializedName("name")
        String nickname,
        String email,
        String phone
) implements Identify {
    public record Wrong(
            @JsonProperty("code")
            @SerializedName("code")
            int code,
            @JsonProperty("msg")
            @SerializedName("msg")
            String msg
    ) implements Identify.Wrong {
    }
}
