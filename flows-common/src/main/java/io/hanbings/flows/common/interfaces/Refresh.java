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
 * 用于 OAuth 重新授权（刷新）后所返回的数据模型
 * 通常包含 access token 作为权限凭据
 */
@SuppressWarnings("unused")
public interface Refresh {
    /**
     * access token
     *
     * @return 字符串凭据
     */
    String accessToken();

    /**
     * 错误信息
     */
    interface Wrong {
        /**
         * 错误信息
         *
         * @return 字符串错误信息
         */
        String error();
    }
}
