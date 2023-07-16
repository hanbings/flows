package io.hanbings.flows.baidu;

import io.hanbings.flows.common.OAuth;
import io.hanbings.flows.common.interfaces.Callback;
import io.hanbings.flows.common.interfaces.Identifiable;
import io.hanbings.flows.common.interfaces.Profilable;
import io.hanbings.flows.common.interfaces.Refreshable;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class BaiduOAuth
        extends
        OAuth<BaiduAccess, BaiduAccess.Wrong>
        implements
        Refreshable<BaiduAccess, BaiduAccess.Wrong>,
        Profilable<BaiduProfile, BaiduProfile.Wrong>,
        Identifiable<BaiduIdentify, BaiduIdentify.Wrong> {
    private BaiduOAuth() {
        super(null, null, null, null);
    }

    public BaiduOAuth(String client, String secret, String redirect) {
        super(
                "https://openapi.baidu.com/oauth/2.0/authorize",
                "https://openapi.baidu.com/oauth/2.0/token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public BaiduOAuth(String client, String secret, String redirect,
                      List<String> scopes, Map<String, String> params) {
        super(
                "https://openapi.baidu.com/oauth/2.0/authorize",
                "https://openapi.baidu.com/oauth/2.0/token",
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
    public Callback<BaiduAccess, BaiduAccess.Wrong> token(String code, String state, String redirect) {
        return null;
    }

    @Override
    public Callback<BaiduIdentify, BaiduIdentify.Wrong> identify(String token) {
        return null;
    }

    @Override
    public Callback<BaiduProfile, BaiduProfile.Wrong> profile(String token) {
        return null;
    }

    @Override
    public Callback<BaiduAccess, BaiduAccess.Wrong> refresh(String token) {
        return null;
    }
}
