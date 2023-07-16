package io.hanbings.flows.weibo;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.interfaces.Callback;
import io.hanbings.flows.common.interfaces.Identifiable;
import io.hanbings.flows.common.interfaces.Profilable;
import io.hanbings.flows.common.interfaces.Revokable;

import java.util.List;
import java.util.Map;

public class WeiboOAuth
        extends
        OAuth<WeiboAccess, WeiboAccess.Wrong>
        implements
        Revokable<WeiboRevoke, WeiboRevoke.Wrong>,
        Profilable<WeiboProfile, WeiboProfile.Wrong>,
        Identifiable<WeiboIdentify, WeiboIdentify.Wrong> {

    private WeiboOAuth() {
        super(null, null, null, null);
    }

    public WeiboOAuth(String client, String secret, String redirect) {
        super(
                "https://api.weibo.com/oauth2/authorize",
                "https://api.weibo.com/oauth2/access_token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public WeiboOAuth(String client, String secret, String redirect,
                      List<String> scopes, Map<String, String> params) {
        super(
                "https://api.weibo.com/oauth2/authorize",
                "https://api.weibo.com/oauth2/access_token",
                scopes,
                params
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        return null;
    }

    @Override
    public Callback<WeiboAccess, WeiboAccess.Wrong> token(String code, String state, String redirect) {
        return null;
    }

    @Override
    public Callback<WeiboIdentify, WeiboIdentify.Wrong> identify(String token) {
        return null;
    }

    @Override
    public Callback<WeiboProfile, WeiboProfile.Wrong> profile(String token) {
        return null;
    }

    @Override
    public Callback<WeiboRevoke, WeiboRevoke.Wrong> revoke(String token) {
        return null;
    }
}
