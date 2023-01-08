package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

public interface Accessible<D extends Access, W extends Access.Wrong> {
    String authorize();

    String authorize(List<String> scopes);

    String authorize(Map<String, String> params);

    String authorize(List<String> scopes, Map<String, String> params);

    Callback<D, W> token(String url);

    Callback<D, W> token(String url, String redirect);

    Callback<D, W> token(String code, String state, String redirect);
}
