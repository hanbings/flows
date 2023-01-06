package io.hanbings.fluocean.github;

import io.hanbings.fluocean.common.OAuth;
import io.hanbings.fluocean.common.OAuthCallback;
import io.hanbings.fluocean.common.interfaces.Callback;
import io.hanbings.fluocean.common.interfaces.Response;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class GithubOAuth extends OAuth<GithubAccess, GithubAccess.Wrong> {
    private GithubOAuth() {
        super(
                "https://github.com/login/oauth/authorize",
                "https://github.com/login/oauth/access_token"
        );
    }

    public GithubOAuth(String client, String secret, String redirect) {
        super(
                "https://github.com/login/oauth/authorize",
                "https://github.com/login/oauth/access_token"
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    @Override
    public Callback<GithubAccess, GithubAccess.Wrong> token(String code, String state, String redirect) {
        Response response = this.request()
                .get()
                .post(
                        this.serialization().get(),
                        this.proxy() == null ? null : this.proxy().get(),
                        this.access(),
                        Map.of(
                                "client_id", this.client(),
                                "client_secret", this.secret(),
                                "redirect_uri", redirect,
                                "code", code,
                                "state", state
                        ),
                        Map.of(
                                "Accept", "application/json",
                                "Content-Type", "application/x-www-form-urlencoded"
                        )
                );

        if (response.code() == 200) {
            if (serialization().get().map(String.class, String.class, response.raw()).containsKey("access_token")) {
                GithubAccess access = this.serialization()
                        .get()
                        .object(GithubAccess.class, response.raw());

                return OAuthCallback.response(access.accessToken(), access, null);
            } else {
                GithubAccess.Wrong wrong = this.serialization()
                        .get()
                        .object(GithubAccess.Wrong.class, response.raw());

                return OAuthCallback.response(null, null, wrong);
            }
        }

        return OAuthCallback.exception(
                null,
                response.exception() ? response.throwable() : new IllegalArgumentException()
        );
    }

    public static void main(String... args) {
        // 创建 OAuth 原始处理器
        OAuth<GithubAccess, GithubAccess.Wrong> oauth = new GithubOAuth(
                "98f0fbc315f6de388ac5",
                "91cea69ced505382a6cfb7e2673716cbb7d869f5",
                "https://nestsid.com/api/v0/login/oauth/github/callback"
        );

        // 生成授权 url
        String url = oauth.authorize();
        // 生成带参数或指定 scope
        String spec = oauth.authorize(List.of("email"), Map.of("Accept", "application/xml"));

        //解析回调的 url 并获取 token
        // 输入原始 url 自动解析 code 以及 state
        oauth.token("");
        // 更改回调地址
        oauth.token("","");
        // 手动指定参数
        oauth.token("", "","");

        // 处理返回值
        oauth.token("code", "state", "callback")
                .succeed(data -> System.out.println(data.accessToken()))
                .fail(wrong -> System.out.println(wrong.errorDescription()))
                .except(throwable -> System.out.println(throwable.getMessage()));

        // 假设请求成功 直接获取数据
        GithubAccess access = oauth.token("code", "state", "callback").data();
    }
}
