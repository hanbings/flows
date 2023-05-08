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
import io.hanbings.flows.common.interfaces.Profile;

public record DiscordProfile(
        @JsonProperty("premium_type")
        @SerializedName("premium_type")
        int premiumType,
        @JsonProperty("accent_color")
        @SerializedName("accent_color")
        int accentColor,
        @JsonProperty("flags")
        @SerializedName("flags")
        int flags,
        @JsonProperty("banner")
        @SerializedName("banner")
        String banner,
        @JsonProperty("id")
        @SerializedName("id")
        String id,
        @JsonProperty("avatar")
        @SerializedName("avatar")
        String avatar,
        @JsonProperty("public_flags")
        @SerializedName("public_flags")
        int publicFlags,
        @JsonProperty("username")
        @SerializedName("username")
        String username,
        @JsonProperty("discriminator")
        @SerializedName("discriminator")
        String discriminator,
        @JsonProperty("global_name")
        @SerializedName("global_name")
        String globalName,
        @JsonProperty("display_name")
        @SerializedName("display_name")
        String displayName,
        @JsonProperty("banner_color")
        @SerializedName("banner_color")
        String bannerColor,
        @JsonProperty("locale")
        @SerializedName("locale")
        String locale,
        @JsonProperty("mfa_enabled")
        @SerializedName("mfa_enabled")
        String mfaEnabled,
        @JsonProperty("avatar_description")
        @SerializedName("avatar_description")
        String avatarDescription
) implements Profile {
    public record Wrong(
            @JsonProperty("code")
            @SerializedName("code")
            int code,
            @JsonProperty("message")
            @SerializedName("message")
            String message
    ) implements Profile.Wrong {
    }
}