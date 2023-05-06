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

/**
 * 用于 OAuth 授权后所返回的数据模型
 * 通常包含 access token 作为权限凭据
 */
@SuppressWarnings("unused")
public interface Access {
    /**
     * 权限凭据
     *
     * @return 字符串类型凭据
     */
    String accessToken();

    /**
     * 用于描述 OAuth 授权错误返回的数据模型
     */
    interface Wrong {
        /**
         * 错误的详细信息
         *
         * @return 错误信息
         */
        String error();
    }
}
