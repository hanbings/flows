package io.hanbings.fluocean.microsoft;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record MicrosoftAccess(
        @SerializedName("access_token")
        String accessToken,
        @SerializedName("token_type")
        String tokenType,
        @SerializedName("expires_in")
        String expiresIn,
        @SerializedName("scope")
        String scope,
        @SerializedName("refresh_token")
        String refreshToken,
        @SerializedName("id_token")
        String idToken
) {
    record Wrong(
            @SerializedName("error")
            String error,
            @SerializedName("error_description")
            String errorDescription,
            @SerializedName("error_codes")
            List<String> errorCodes,
            @SerializedName("timestamp")
            String timestamp,
            @SerializedName("trace_id")
            String traceId,
            @SerializedName("correlation_id")
            String correlationId
    ) {
    }
}
