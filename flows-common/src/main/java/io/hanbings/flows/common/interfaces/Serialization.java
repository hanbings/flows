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

import java.util.List;
import java.util.Map;

/**
 * json 解析器接口
 * 实现该接口以自定义解析器
 */
@SuppressWarnings("unused")
public interface Serialization {
    /**
     * 从 json 解析到对象
     *
     * @param type 对象类型
     * @param raw  json 原始数据
     * @param <T>  对象类型
     * @return 对象
     */
    <T> T object(Class<T> type, String raw);

    /**
     * 将 json 解析到键值对
     *
     * @param key   键类型
     * @param value 值类型
     * @param raw   json 原始数据
     * @param <K>   键类型
     * @param <V>   值类型
     * @return 键值对
     */
    <K, V> Map<K, V> map(Class<K> key, Class<V> value, String raw);

    /**
     * 将 json 解析到列表
     *
     * @param type 类型
     * @param raw  json 原始数据
     * @param <T>  列表类型
     * @return 列表
     */
    <T> List<T> list(Class<T> type, String raw);
}
