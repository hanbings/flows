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

package io.hanbings.flows.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * http 请求封装
 */
@SuppressWarnings("unused")
public interface Request {
    /**
     * Get 请求
     *
     * @param serialization 解析器
     * @param proxy         代理
     * @param url           请求的 url
     * @return Response 数据
     */
    Response get(Serialization serialization, Proxy proxy, String url);

    /**
     * Get 请求
     *
     * @param serialization 解析器
     * @param proxy         代理
     * @param url           请求的 url
     * @param params        query 参数
     * @return Response 数据
     */
    Response get(Serialization serialization, Proxy proxy, String url, Map<String, String> params);

    /**
     * Get 请求
     *
     * @param serialization 解析器
     * @param proxy         代理
     * @param url           请求的 url
     * @param params        query 参数
     * @param headers       添加 headers
     * @return Response 数据
     */
    Response get(Serialization serialization, Proxy proxy, String url,
                 Map<String, String> params, Map<String, String> headers);

    /**
     * Post 请求
     *
     * @param serialization 解析器
     * @param proxy         代理
     * @param url           请求的 url
     * @return Response 数据
     */
    Response post(Serialization serialization, Proxy proxy, String url);

    /**
     * Post 请求
     *
     * @param serialization 解析器
     * @param proxy         代理
     * @param url           请求的 url
     * @param form          表单数据
     * @return Response 数据
     */
    Response post(Serialization serialization, Proxy proxy, String url, Map<String, String> form);

    /**
     * Post 请求
     *
     * @param serialization 解析器
     * @param proxy         代理
     * @param url           请求的 url
     * @param form          表单数据
     * @param headers       添加 headers
     * @return Response 数据
     */
    Response post(Serialization serialization, Proxy proxy, String url,
                  Map<String, String> form, Map<String, String> headers);

    @Setter
    @Getter
    @AllArgsConstructor
    @SuppressWarnings("unused")
    @Accessors(fluent = true, chain = true)
    class Proxy {
        java.net.Proxy.Type type;
        String host;
        int port;
        String username;
        String password;
    }
}
