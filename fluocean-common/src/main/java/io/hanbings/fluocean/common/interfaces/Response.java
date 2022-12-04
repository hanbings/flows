package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

public interface Response<T> {
    T data();

    boolean success();

    interface Serialization<T> {
        T serialize(String raw);

        Map<String, Object> map(String raw);

        <E> List<E> list(String raw, Class<E> clazz);
    }
}
