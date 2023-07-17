package io.hanbings.flows.microsoft;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Profile;

import java.util.List;

public record MicrosoftProfile(
        @JsonProperty("businessPhones")
        @SerializedName("businessPhones")
        List<String> businessPhones,
        @JsonProperty("displayName")
        @SerializedName("displayName")
        String displayName,
        @JsonProperty("givenName")
        @SerializedName("givenName")
        String givenName,
        @JsonProperty("jobTitle")
        @SerializedName("jobTitle")
        String jobTitle,
        @JsonProperty("mail")
        @SerializedName("mail")
        String mail,
        @JsonProperty("mobilePhone")
        @SerializedName("mobilePhone")
        String mobilePhone,
        @JsonProperty("officeLocation")
        @SerializedName("officeLocation")
        String officeLocation,
        @JsonProperty("preferredLanguage")
        @SerializedName("preferredLanguage")
        String preferredLanguage,
        @JsonProperty("surname")
        @SerializedName("surname")
        String surname,
        @JsonProperty("userPrincipalName")
        @SerializedName("userPrincipalName")
        String userPrincipalName,
        @JsonProperty("id")
        @SerializedName("id")
        String id
) implements Profile {
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
    ) implements Profile.Wrong {
    }
}
