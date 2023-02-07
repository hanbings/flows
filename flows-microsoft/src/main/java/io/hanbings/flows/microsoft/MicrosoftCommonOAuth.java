package io.hanbings.flows.microsoft;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class MicrosoftCommonOAuth extends MicrosoftOAuth {
    private MicrosoftCommonOAuth() {
        super(null, null, null, null, null, null, null);
    }

    public MicrosoftCommonOAuth(String client, String secret, String redirect) {
        super(
                "https://login.microsoftonline.com/common/oauth2/v2.0/authorize",
                "https://login.microsoftonline.com/common/oauth2/v2.0/token",
                client,
                secret,
                redirect,
                List.of(),
                Map.of()
        );
    }

    public MicrosoftCommonOAuth(String client, String secret, String redirect,
                                List<String> scopes, Map<String, String> params) {
        super(
                "https://login.microsoftonline.com/common/oauth2/v2.0/authorize",
                "https://login.microsoftonline.com/common/oauth2/v2.0/token",
                client,
                secret,
                redirect,
                scopes,
                params
        );
    }
}
