package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public interface Authable<R extends Request, T, P extends Response<T>, S extends Enum<?>> {
    String authorize();

    String authorize(List<S> scopes);

    String authorize(Map<String, String> params);

    String authorize(List<S> scopes, Map<String, String> params);

    P token(String url);

    P token(String code, boolean raw);
}
