package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Serialization;

import java.util.List;
import java.util.Map;

public class OAuthSerialization implements Serialization {
    @Override
    public <T> T object(Class<T> type, String raw) {
        return null;
    }

    @Override
    public Map<String, Object> map(String raw) {
        return null;
    }

    @Override
    public <T> List<T> list(Class<T> type, String raw) {
        return null;
    }
}
