package io.hanbings.flows.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Profile;

public record GoogleProfile(
        @JsonProperty("sub")
        @SerializedName("sub")
        String sub,
        @JsonProperty("name")
        @SerializedName("name")
        String name,
        @JsonProperty("given_name")
        @SerializedName("given_name")
        String givenName,
        @JsonProperty("family_name")
        @SerializedName("family_name")
        String familyName,
        @JsonProperty("picture")
        @SerializedName("picture")
        String picture,
        @JsonProperty("email")
        @SerializedName("email")
        String email,
        @JsonProperty("locale")
        @SerializedName("locale")
        String locale
) implements Profile {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_description")
            @SerializedName("error_description")
            String errorDescription
    ) implements Profile.Wrong {
    }
}
