package io.hanbings.fluocean.facebook;

import com.google.gson.annotations.SerializedName;
import io.hanbings.fluocean.common.interfaces.Access;

public record FacebookAccess(
        @SerializedName("access_token")
        String accessToken
) implements Access {
    record Wrong(
            @SerializedName("error")
            String error
    ) implements Access.Wrong {
    }
}
