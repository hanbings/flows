package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public interface Authable<T> {
    String authorize();

    String authorize(List<Enum<?>> scopes);

    String authorize(Map<String, String> params);

    String authorize(List<Enum<?>> scopes, Map<String, String> params);

    Response<T> token(String url);

    Response<T> token(String code, boolean raw);
}
