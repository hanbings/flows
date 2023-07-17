package io.hanbings.flows.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Revoke;

public record GoogleRevoke() implements Revoke {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription
    ) implements Revoke.Wrong {
    }
}
