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
 * OAuth 提供商支持撤消 token 则会实现该接口
 *
 * @param <D> 数据模型
 * @param <W> 错误信息
 */
@SuppressWarnings("SpellCheckingInspection unused")
public interface Revokable<D extends Revoke, W extends Revoke.Wrong> {
    /**
     * 使用具有权限的 token 撤消 token
     *
     * @param token 这个 token 由服务提供商决定
     * @return Callback 数据
     */
    Callback<D, W> revoke(String token);
}
