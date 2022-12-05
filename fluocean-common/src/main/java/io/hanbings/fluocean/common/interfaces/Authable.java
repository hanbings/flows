package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public interface Authable<T, D, E> {
    String authorize();

    String authorize(List<Enum<?>> scopes);

    String authorize(Map<String, String> params);

    String authorize(List<Enum<?>> scopes, Map<String, String> params);

    Response<T, D, E> token(String url);

    Response<T, D, E> token(String code, boolean raw);
}
