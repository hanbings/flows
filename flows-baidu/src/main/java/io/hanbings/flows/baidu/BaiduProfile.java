package io.hanbings.flows.baidu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Profile;

@SuppressWarnings("SpellCheckingInspection")
public record BaiduProfile(
        @JsonProperty("openid")
        @SerializedName("openid")
        String openid,
        @JsonProperty("unionid")
        @SerializedName("unionid")
        String unionid,
        @JsonProperty("userid")
        @SerializedName("userid")
        String userid,
        @JsonProperty("securemobile")
        @SerializedName("securemobile")
        String securemobile,
        @JsonProperty("username")
        @SerializedName("username")
        String username,
        @JsonProperty("portrait")
        @SerializedName("portrait")
        String portrait,
        @JsonProperty("userdetail")
        @SerializedName("userdetail")
        String userdetail,
        @JsonProperty("birthday")
        @SerializedName("birthday")
        String birthday,
        @JsonProperty("marriage")
        @SerializedName("marriage")
        String marriage,
        @JsonProperty("sex")
        @SerializedName("sex")
        String sex,
        @JsonProperty("blood")
        @SerializedName("blood")
        String blood,
        @JsonProperty("is_bind_mobile")
        @SerializedName("is_bind_mobile")
        int isBindMobile,
        @JsonProperty("is_realname")
        @SerializedName("is_realname")
        int isRealname
) implements Profile {
    public record Wrong(
            @JsonProperty("error_code")
            @SerializedName("error_code")
            int errorCode,
            @JsonProperty("error_msg")
            @SerializedName("error_msg")
            String errorMsg
    ) implements Profile.Wrong {
    }
}
