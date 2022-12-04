package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

public interface Serialization {
    <T> T object(Class<T> type, String raw);

    Map<String, Object> map(String raw);

    <T> List<T> list(Class<T> type, String raw);
}
