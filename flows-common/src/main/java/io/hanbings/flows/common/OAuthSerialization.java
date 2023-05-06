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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.hanbings.flows.common.interfaces.Serialization;

import java.util.List;
import java.util.Map;

public class OAuthSerialization implements Serialization {
    Gson gson = new Gson();

    @Override
    public <T> T object(Class<T> type, String raw) {
        return gson.fromJson(raw, type);
    }

    @Override
    public <K, V> Map<K, V> map(Class<K> key, Class<V> value, String raw) {
        return gson.fromJson(raw, new TypeToken<Map<K, V>>() {
        }.getType());
    }

    @Override
    public <T> List<T> list(Class<T> type, String raw) {
        return gson.fromJson(raw, new TypeToken<List<T>>() {
        }.getType());
    }
}
