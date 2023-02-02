package io.hanbings.flows.microsoft;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Access;
import io.hanbings.flows.common.interfaces.Refresh;

import java.util.List;

public record MicrosoftAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("token_type")
        @SerializedName("token_type")
        String tokenType,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        String expiresIn,
        @JsonProperty("scope")
        @SerializedName("scope")
        String scope,
        @JsonProperty("refresh_token")
        @SerializedName("refresh_token")
        String refreshToken,
        @JsonProperty("id_token")
        @SerializedName("id_token")
        String idToken
) implements Access, Refresh {
    record Wrong(
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
    ) implements Access.Wrong, Refresh.Wrong {
    }
}
