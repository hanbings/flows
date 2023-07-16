package io.hanbings.flows.weibo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Profile;

@SuppressWarnings("SpellCheckingInspection")
public record WeiboProfile(
        @JsonProperty("id")
        @SerializedName("id")
        String id,
        @JsonProperty("idstr")
        @SerializedName("idstr")
        String idstr,
        @JsonProperty("screen_name")
        @SerializedName("screen_name")
        String screenName,
        @JsonProperty("name")
        @SerializedName("name")
        String name,
        @JsonProperty("province")
        @SerializedName("province")
        String province,
        @JsonProperty("city")
        @SerializedName("city")
        String city,
        @JsonProperty("location")
        @SerializedName("location")
        String location,
        @JsonProperty("description")
        @SerializedName("description")
        String description,
        @JsonProperty("url")
        @SerializedName("url")
        String url,
        @JsonProperty("profile_image_url")
        @SerializedName("profile_image_url")
        String profileImageUrl,
        @JsonProperty("profile_url")
        @SerializedName("profile_url")
        String profileUrl,
        @JsonProperty("domain")
        @SerializedName("domain")
        String domain,
        @JsonProperty("weihao")
        @SerializedName("weihao")
        String weihao,
        @JsonProperty("gender")
        @SerializedName("gender")
        String gender,
        @JsonProperty("followers_count")
        @SerializedName("followers_count")
        int followersCount,
        @JsonProperty("friends_count")
        @SerializedName("friends_count")
        int friendsCount,
        @JsonProperty("statuses_count")
        @SerializedName("statuses_count")
        int statusesCount,
        @JsonProperty("favourites_count")
        @SerializedName("favourites_count")
        int favouritesCount,
        @JsonProperty("created_at")
        @SerializedName("created_at")
        String createdAt,
        @JsonProperty("following")
        @SerializedName("following")
        boolean following,
        @JsonProperty("allow_all_act_msg")
        @SerializedName("allow_all_act_msg")
        boolean allowAllActMsg,
        @JsonProperty("geo_enabled")
        @SerializedName("geo_enabled")
        boolean geoEnabled,
        @JsonProperty("verified")
        @SerializedName("verified")
        boolean verified,
        @JsonProperty("verified_type")
        @SerializedName("verified_type")
        int verifiedType,
        @JsonProperty("remark")
        @SerializedName("remark")
        String remark,
        @JsonProperty("status")
        @SerializedName("status")
        Object status,
        @JsonProperty("allow_all_comment")
        @SerializedName("allow_all_comment")
        boolean allowAllComment,
        @JsonProperty("avatar_large")
        @SerializedName("avatar_large")
        String avatarLarge,
        @JsonProperty("avatar_hd")
        @SerializedName("avatar_hd")
        String avatarHd,
        @JsonProperty("verified_reason")
        @SerializedName("verified_reason")
        String verifiedReason,
        @JsonProperty("follow_me")
        @SerializedName("follow_me")
        boolean followMe,
        @JsonProperty("online_status")
        @SerializedName("online_status")
        int onlineStatus,
        @JsonProperty("bi_followers_count")
        @SerializedName("bi_followers_count")
        int biFollowersCount,
        @JsonProperty("lang")
        @SerializedName("lang")
        String lang
) implements Profile {
    public record Wrong(
            @JsonProperty("error")
            @SerializedName("error")
            String error,
            @JsonProperty("error_code")
            @SerializedName("error_code")
            int code,
            @JsonProperty("request")
            @SerializedName("request")
            String request
    ) implements Profile.Wrong {
    }
}
