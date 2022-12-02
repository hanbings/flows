package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Requestable;

import java.util.Map;

public class OAuthRequest<P extends OAuthResponse> implements Requestable<P> {

    @Override
    public P get(String url) {
        return null;
    }

    @Override
    public P get(String url, Map<String, String> prams) {
        return null;
    }

    @Override
    public P post(String url) {
        return null;
    }

    @Override
    public P post(String url, Map<String, String> form) {
        return null;
    }
}