/*
 * Copyright 2023 Flows
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.hanbings.flows.common;

import io.hanbings.flows.common.function.Lazy;
import io.hanbings.flows.common.interfaces.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

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
    final List<String> scopes;
    final Map<String, String> params;
    String client;
    String secret;
    String redirect;

    Supplier<Request.Proxy> proxy = null;
    Lazy<Request> request = Lazy.of(
            () -> proxy == null ? new OAuthRequest() : new OAuthRequest(proxy.get())
    );
    Lazy<Serialization> serialization = Lazy.of(OAuthSerialization::new);
    Lazy<State> state = Lazy.of(() -> new OAuthState(300, () -> UUID.randomUUID().toString()));

    /**
     * 该方法默认添加 <br>
     * 1. client_id OAuth 服务商提供的 Client ID <br>
     * 2. redirect_uri 在 OAuth 服务商设置回调 url <br>
     * 3. state 自动生成和判断的安全凭据 <br>
     * 在 scope 默认的分隔符是 %20 即空格的 http encode <br>
     * <p>开发者注意事项：</p>
     * 实现该方法时需要注意 null 的参数传入 <br>
     * 传入 null 时使用 this.scopes 和 this.params 获取默认的 scopes 和 params <br>
     *
     * @param scopes 构造 OAuth 请求范围 传入 null 时将会使用构造方法传入的默认 scopes
     * @param params 构造 authorize url 的参数 传入 null 时将会使用构造方法传入的默认 params
     * @return 返回 authorize url
     */
    @Override
    public String authorize(@Nullable List<String> scopes, @Nullable Map<String, String> params) {
        List<String> sco = scopes == null ? this.scopes() : scopes;
        Map<String, String> par = params == null ? this.params() : params;

        Map<String, String> temp = new HashMap<>() {{
            put("client_id", client());
            put("redirect_uri", redirect());
            put("state", state().get().add());

            // put scopes
            StringBuilder scope = new StringBuilder();

            IntStream.range(0, sco.size()).forEach(count -> {
                if (count != 0) {
                    scope.append("%20");
                }

                scope.append(sco.get(count));
            });

            if (scope.length() != 0) {
                put("scope", scope.toString());
            }

            // merge map
            putAll(par);
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
