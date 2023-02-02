package io.hanbings.flows.facebook;

import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Access;

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
