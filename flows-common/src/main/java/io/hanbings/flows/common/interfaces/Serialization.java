package io.hanbings.flows.common.interfaces;

import java.util.List;
import java.util.Map;

public interface Serialization {
    <T> T object(Class<T> type, String raw);

    <K, V> Map<K, V> map(Class<K> key, Class<V> value, String raw);

    <T> List<T> list(Class<T> type, String raw);
}
