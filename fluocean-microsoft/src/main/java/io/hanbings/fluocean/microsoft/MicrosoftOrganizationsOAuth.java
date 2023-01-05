package io.hanbings.fluocean.microsoft;

@SuppressWarnings("unused")
public class MicrosoftOrganizationsOAuth extends MicrosoftOAuth {
    private MicrosoftOrganizationsOAuth() {
        super(null, null, null, null, null);
    }

    public MicrosoftOrganizationsOAuth(String client, String secret, String redirect) {
        super(
                "https://login.microsoftonline.com/common/oauth2/v2.0/authorize",
                "https://login.microsoftonline.com/common/oauth2/v2.0/token",
                client,
                secret,
                redirect
        );
    }
}