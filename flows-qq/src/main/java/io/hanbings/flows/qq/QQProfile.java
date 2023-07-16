package io.hanbings.flows.qq;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Profile;

@SuppressWarnings("SpellCheckingInspection")
public record QQProfile(
        @JsonProperty("ret")
        @SerializedName("ret")
        int ret,
        @JsonProperty("msg")
        @SerializedName("msg")
        String msg,
        @JsonProperty("is_lost")
        @SerializedName("is_lost")
        int isLost,
        @JsonProperty("nickname")
        @SerializedName("nickname")
        String nickname,
        @JsonProperty("figureurl")
        @SerializedName("figureurl")
        String figureurl,
        @JsonProperty("figureurl_1")
        @SerializedName("figureurl_1")
        String figureurl_1,
        @JsonProperty("figureurl_2")
        @SerializedName("figureurl_2")
        String figureurl_2,
        @JsonProperty("figureurl_qq_1")
        @SerializedName("figureurl_qq_1")
        String figureurl_qq_1,
        @JsonProperty("figureurl_qq_2")
        @SerializedName("figureurl_qq_2")
        String figureurl_qq_2,
        @JsonProperty("gender")
        @SerializedName("gender")
        String gender,
        @JsonProperty("gender_type")
        @SerializedName("gender_type")
        int genderType,
        @JsonProperty("province")
        @SerializedName("province")
        String province,
        @JsonProperty("city")
        @SerializedName("city")
        String city,
        @JsonProperty("year")
        @SerializedName("year")
        int year,
        @JsonProperty("constellation")
        @SerializedName("constellation")
        String constellation,
        @JsonProperty("is_yellow_vip")
        @SerializedName("is_yellow_vip")
        String isYellowVip,
        @JsonProperty("yellow_vip_level")
        @SerializedName("yellow_vip_level")
        int yellowVipLevel,
        @JsonProperty("is_yellow_year_vip")
        @SerializedName("is_yellow_year_vip")
        int isYellowYearVip

) implements Profile {
    public record Wrong(
            @JsonProperty("code")
            @SerializedName("code")
            int code,
            @JsonProperty("msg")
            @SerializedName("msg")
            String msg
    ) implements Profile.Wrong {
    }
}
