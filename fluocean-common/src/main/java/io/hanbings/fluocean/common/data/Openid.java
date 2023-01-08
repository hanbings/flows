package io.hanbings.fluocean.common.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public record Openid(
        @JsonProperty("openid")
        @SerializedName("openid")
        String openid
) {
}
