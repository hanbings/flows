package io.hanbings.flows.microsoft;

@SuppressWarnings("unused")
public class MicrosoftConsumersOAuth extends MicrosoftOAuth {
    private MicrosoftConsumersOAuth() {
        super(null, null, null, null, null);
    }

    public MicrosoftConsumersOAuth(String client, String secret, String redirect) {
        super(
                "https://login.microsoftonline.com/consumers/oauth2/v2.0/authorize",
                "https://login.microsoftonline.com/consumers/oauth2/v2.0/token",
                client,
                secret,
                redirect
        );
    }
}
