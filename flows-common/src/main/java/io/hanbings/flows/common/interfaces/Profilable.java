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
 * 用于描述 OAuth 提供商提供的个人资料
 *
 * @param <D> 个人资料数据模型
 * @param <W> 错误信息
 */
@SuppressWarnings("SpellCheckingInspection unused")
public interface Profilable<D extends Profile, W extends Profile.Wrong> {
    /**
     * 使用有效 token 获取个人资料
     *
     * @param token access token
     * @return Callback 数据
     */
    Callback<D, W> profile(String token);
}