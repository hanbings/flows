package io.hanbings.flows.microsoft;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Identify;

import java.util.List;

public record MicrosoftIdentify(
        @JsonProperty("id")
        @SerializedName("id")
        String openid,
        String avatar,
        @JsonProperty("givenName")
        @SerializedName("givenName")
        String username,
        @JsonProperty("displayName")
        @SerializedName("displayName")
        String nickname,
        @JsonProperty("mail")
        @SerializedName("mail")
        String email,
        @JsonProperty("mobilePhone")
        @SerializedName("mobilePhone")
        String phone
) implements Identify {
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
    ) implements Identify.Wrong {
    }
}
