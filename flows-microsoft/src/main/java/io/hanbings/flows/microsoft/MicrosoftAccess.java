/*
 * Copyright 2023 Flows
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    ) implements Access.Wrong, Refresh.Wrong {
    }
}
