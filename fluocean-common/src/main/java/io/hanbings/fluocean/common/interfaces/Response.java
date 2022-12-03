package io.hanbings.fluocean.common.interfaces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public interface Response<T> {
    T data();

    boolean success();

    class Serialization<T> {
        static Gson serializer = new Gson();

        T serialization(String raw, Class<T> type) {
            return serializer.fromJson(raw, TypeToken.get(type));
        }
    }
}
