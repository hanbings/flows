package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Authable;
import io.hanbings.fluocean.common.interfaces.ScopeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@SuppressWarnings("unused")
@Accessors(fluent = true, chain = true)
public class OAuth<R extends OAuthRequest<P>, P extends OAuthResponse> implements Authable<R, P> {
    String authorization;
    String access;

    private OAuth() {}

    @Override
    public String authorize() {
        return null;
    }

    @Override
    public String authorize(String state) {
        return null;
    }

    @Override
    public String authorize(List<Enum<? extends ScopeEnum>> scopes) {
        return null;
    }

    @Override
    public String authorize(Map<String, String> params) {
        return null;
    }

    @Override
    public String authorize(String state, List<Enum<? extends ScopeEnum>> scopes, Map<String, String> params) {
        return null;
    }

    @Override
    public P token(String code) {
        return null;
    }

    @Override
    public P token(String url, boolean code) {
        return null;
    }
}
