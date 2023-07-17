package io.hanbings.flows.microsoft;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Revoke;

import java.util.List;

public record MicrosoftRevoke() implements Revoke {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription,
            @JsonProperty("error_codes")
            @SerializedName("error_codes")
            List<String> errorCodes,
            @JsonProperty("timestamp")
            @SerializedName("timestamp")
            String timestamp,
            @JsonProperty("trace_id")
            @SerializedName("trace_id")
            String traceId,
            @JsonProperty("correlation_id")
            @SerializedName("correlation_id")
            String correlationId
    ) implements Revoke.Wrong {
    }
}
