package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.function.Lazy;
import io.hanbings.fluocean.common.interfaces.*;
import io.hanbings.fluocean.common.utils.UrlUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * OAuth 平台需要实现的基本接口 <br>
 * 一般来说参数最多的是具体实现，其余均为可选参数的重载 <br>
 * 各个平台的注意事项参见各个平台 *OAuth 类的注释
 *
 * @param <D> data 数据类 是描述成功接收 Access Token 的数据容器
 * @param <W> wrong 错误类 是描述成功请求但返回错误状态码的数据容器
 */
@Setter
@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
@Accessors(fluent = true, chain = true)
public class OAuth<D extends Access, W extends Access.Wrong> implements Accessible<D, W> {
    final String authorization;
    final String access;
    String client;
    String secret;
    String redirect;

    Supplier<Request.Proxy> proxy = null;
    Lazy<Request> request = Lazy.of(
            () -> proxy == null ? new OAuthRequest() : new OAuthRequest(proxy.get()));
    Lazy<Serialization> serialization = Lazy.of(OAuthSerialization::new);
    Lazy<State> state = Lazy.of(() -> new OAuthState(300, () -> UUID.randomUUID().toString()));

    @Override
    public String authorize() {
        return authorize(List.of(), Map.of());
    }

    @Override
    public String authorize(List<String> scopes) {
        return authorize(scopes, Map.of());
    }

    @Override
    public String authorize(Map<String, String> params) {
        return authorize(List.of(), params);
    }

    /**
     * 该方法默认添加 <br>
     * 1. client_id OAuth 服务商提供的 Client ID <br>
     * 2. redirect_uri 在 OAuth 服务商设置回调 url <br>
     * 3. state 自动生成和判断的安全凭据 <br>
     * 在 scope 默认的分隔符是 %20 即空格的 http encode
     *
     * @param scopes 构造 OAuth 请求范围
     * @param params 构造 authorize url 的参数
     * @return 返回 authorize url
     */
    @Override
    public String authorize(List<String> scopes, Map<String, String> params) {
        Map<String, String> temp = new HashMap<>() {{
            put("client_id", client);
            put("redirect_uri", redirect);
            put("state", state.get().add());

            // put scopes
            StringBuilder scope = new StringBuilder();

            IntStream.range(0, scopes.size()).forEach(count -> {
                if (count != 0) {
                    scope.append("%20");
                }

                scope.append(scopes.get(count));
            });

            if (scope.length() != 0) {
                put("scope", scope.toString());
            }

            // merge map
            putAll(params);
        }};

        // url builder
        StringBuilder url = new StringBuilder().append(authorization);

        if (temp.size() != 0) {
            url.append("?");

            // map to string
            temp.forEach((k, v) -> url.append(k).append("=").append(v).append("&"));

            // eat last '&' and return string
            return url.substring(0, url.length() - 1);
        }

        return url.toString();
    }

    @Override
    public Callback<D, W> token(String url) {
        return token(url, null);
    }

    @Override
    public Callback<D, W> token(String url, String redirect) {
        String code;
        String state;

        try {
            code = UrlUtils.params(url).get("code");
            state = UrlUtils.params(url).get("state");
        } catch (URISyntaxException e) {
            return OAuthCallback.exception(null, e);
        }

        return token(code, state, redirect);
    }

    /**
     * 该方法默认未实现 需要依据平台对 OAuth 标准的执行进行视频
     *
     * @param code     授权码
     * @param state    自动生成和判断的安全凭据
     * @param redirect 回调 url
     * @return 返回 authorize url
     */
    @Override
    public Callback<D, W> token(String code, String state, String redirect) {
        throw new UnsupportedOperationException();
    }
}
