package io.hanbings.fluocean.common.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public record Email(
        @JsonProperty("email")
        @SerializedName("email")
        String email
) {
}
