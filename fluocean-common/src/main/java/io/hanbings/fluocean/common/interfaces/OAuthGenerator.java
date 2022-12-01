package io.hanbings.fluocean.common.interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.function.Supplier;

@Setter
@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
@Accessors(fluent = true, chain = true)
public abstract class OAuthGenerator {
    String client;
    String secret;
    String redirect;

    Supplier<String> state;

    abstract public String authorize();

    abstract public String authorize(String state);

    abstract public String authorize(Map<String, String> prams);

    abstract public String token(String code);
}
