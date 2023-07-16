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

package io.hanbings.flows.discord;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Identify;

public record DiscordIdentify(
        @JsonProperty("id")
        @SerializedName("id")
        String openid,
        @JsonProperty("avatar_url")
        @SerializedName("avatar_url")
        String avatar,
        @JsonProperty("login")
        @SerializedName("login")
        String username,
        @JsonProperty("username")
        @SerializedName("username")
        String nickname,
        @JsonProperty("email")
        @SerializedName("email")
        String email,
        String phone
) implements Identify {
    public record Wrong(
            @JsonProperty("code")
            @SerializedName("code")
            int code,
            @JsonProperty("message")
            @SerializedName("message")
            String message
    ) implements Identify.Wrong {
    }
}
