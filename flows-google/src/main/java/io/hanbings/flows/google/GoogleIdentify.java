package io.hanbings.flows.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Identify;

public record GoogleIdentify(
        @JsonProperty("sub")
        @SerializedName("sub")
        String openid,
        @JsonProperty("picture")
        @SerializedName("picture")
        String avatar,
        @JsonProperty("name")
        @SerializedName("name")
        String username,
        @JsonProperty("username")
        @SerializedName("username")
        String nickname,
        @JsonProperty("email")
        @SerializedName("email")
        String email,
        String phone
) implements Identify {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription
    ) implements Identify.Wrong {
    }
}
