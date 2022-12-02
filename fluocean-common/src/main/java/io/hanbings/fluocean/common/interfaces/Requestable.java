package io.hanbings.fluocean.common.interfaces;

import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public interface Requestable<P extends Responsable> {
    P get(String url);
    P get(String url, Map<String, String> params);
    P post(String url);
    P post(String url, Map<String, String> form);
}
