package io.hanbings.fluocean.dropbox;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthCallback;
import io.hanbings.fluocean.common.OAuthSerialization;
import io.hanbings.fluocean.common.interfaces.Callback;
import io.hanbings.fluocean.common.interfaces.Refreshable;
import io.hanbings.fluocean.common.interfaces.Response;
import io.hanbings.fluocean.common.interfaces.Revokable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class DropboxOAuth
        extends
        OAuth<DropboxAccess, DropboxAccess.Wrong>
        implements
        Refreshable<DropboxRefresh, DropboxRefresh.Wrong>,
        Revokable<DropboxRevoke, DropboxRevoke.Wrong> {
    final String refreshment = "https://api.dropboxapi.com/oauth2/token";
    final String revocation = "https://api.dropboxapi.com/2/auth/token/revoke";

    private DropboxOAuth() {
        super(
                "https://www.dropbox.com/oauth2/authorize",
                "https://api.dropboxapi.com/oauth2/token"
        );
    }

    public DropboxOAuth(String client, String secret, String redirect) {
        super(
                "https://www.dropbox.com/oauth2/authorize",
                "https://api.dropboxapi.com/oauth2/token"
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        return super.authorize(
                scopes,
                new HashMap<>() {{
                    putAll(params);
                    put("response_type", "code");
                }}
        );
    }

    @Override
    public Callback<DropboxAccess, DropboxAccess.Wrong> token(String code, String state, String redirect) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.access(),
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "grant_type", "authorization_code",
                                "redirect_uri", redirect,
                                "code", code
                        ),
                        Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/x-www-form-urlencoded"
                        )
                );

        if (response.code() == 200) {
            DropboxAccess access = this.serialization()
                    .get()
                    .object(DropboxAccess.class, response.raw());

            return OAuthCallback.response(access.accessToken(), access, null);

        }

        if (response.code() == 400) {
            DropboxAccess.Wrong wrong = this.serialization()
                    .get()
                    .object(DropboxAccess.Wrong.class, response.raw());

            return OAuthCallback.response(null, null, wrong);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<DropboxRevoke, DropboxRevoke.Wrong> revoke(String token) {
        Response response = this.request()
                .get()
                .post(
                        new OAuthSerialization() {
                            @Override
                            public <K, V> Map<K, V> map(Class<K> key, Class<V> value, String raw) {
                                return Map.of();
                            }
                        },
                        this.proxy() == null ? null : this.proxy().get(),
                        this.revocation,
                        Map.of(),
                        Map.of(
                                "Authorization", String.format("Bearer %s", token)
                        )
                );

        if (response.code() == 200) {
            DropboxRevoke access = this.serialization()
                    .get()
                    .object(DropboxRevoke.class, "{ }");

            return OAuthCallback.response(null, access, null);
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    @Override
    public Callback<DropboxRefresh, DropboxRefresh.Wrong> refresh(String token) {
        return null;
    }
}
