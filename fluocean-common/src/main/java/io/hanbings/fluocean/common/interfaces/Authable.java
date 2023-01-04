package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public interface Authable<D, W> {
    String authorize();

    String authorize(List<String> scopes);

    String authorize(Map<String, String> params);

    String authorize(List<String> scopes, Map<String, String> params);

    Callback<D, W> token(String url);

    Callback<D, W> token(String code, String state);
}
