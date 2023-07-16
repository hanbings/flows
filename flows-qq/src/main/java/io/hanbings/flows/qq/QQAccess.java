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

package io.hanbings.flows.qq;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Access;
import io.hanbings.flows.common.interfaces.Refresh;

public record QQAccess(
        @JsonProperty("access_token")
        @SerializedName("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        @SerializedName("expires_in")
        long expiresIn,
        @JsonProperty("refresh_token")
        @SerializedName("refresh_token")
        String refreshToken
) implements Access, Refresh {
    public record Wrong(
            @JsonProperty("msg")
            @SerializedName("msg")
            String error,
            @JsonProperty("code")
            @SerializedName("code")
            int code
    ) implements Access.Wrong, Refresh.Wrong {
    }
}
