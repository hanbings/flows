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
