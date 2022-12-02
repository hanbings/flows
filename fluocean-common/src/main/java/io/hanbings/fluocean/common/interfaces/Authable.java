package io.hanbings.fluocean.common.interfaces;

import java.util.List;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public interface Authable<R extends Requestable<P>, P extends Responsable> {
    String authorize();
    String authorize(String state);
    String authorize(List<Enum<? extends ScopeEnum>> scopes);
    String authorize(Map<String, String> params);
    String authorize(String state, List<Enum<? extends ScopeEnum>> scopes, Map<String, String> params);
    P token(String code);
    P token(String url, boolean code);
}
