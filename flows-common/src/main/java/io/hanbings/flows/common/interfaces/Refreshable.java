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
 * 支持刷新的 OAuth 提供商适配将实现该接口
 *
 * @param <D> 刷新后返回的数据模型
 * @param <W> 错误信息
 */
@SuppressWarnings("unused")
public interface Refreshable<D extends Refresh, W extends Refresh.Wrong> {
    /**
     * 刷新 token
     *
     * @param token 旧的凭据或刷新凭据 取决于提供商要求
     * @return Callback 数据
     */
    Callback<D, W> refresh(String token);
}
