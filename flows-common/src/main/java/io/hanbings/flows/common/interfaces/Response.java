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

import java.util.Map;

/**
 * 用于描述 http 请求所返回的数据的原始形式
 */
@SuppressWarnings("unused")
public interface Response {
    /**
     * 是否发生了异常
     *
     * @return 是否异常
     */
    boolean exception();

    /**
     * 异常的数据 没有异常发生则为 null
     *
     * @return 异常
     */
    Throwable throwable();

    /**
     * 状态码
     *
     * @return 状态码
     */
    int code();

    /**
     * 请求的原始数据
     *
     * @return 字符串类型原始数据
     */
    String raw();

    /**
     * 如果数据是 json 则会被解析为 Map <br>
     * 事实上大多数 OAuth 接口都返回 json <br>
     * 因为这是 OAuth 协议所规定的 <br>
     * 所以这个方法大多数情况下不为 null
     *
     * @return json 转换而来的键值对
     */
    Map<String, String> data();
}
