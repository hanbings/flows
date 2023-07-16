package io.hanbings.flows.weibo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Revoke;

public record WeiboRevoke(
        @JsonProperty("result")
        @SerializedName("result")
        String result
) implements Revoke {
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
    ) implements Revoke.Wrong {
    }
}
