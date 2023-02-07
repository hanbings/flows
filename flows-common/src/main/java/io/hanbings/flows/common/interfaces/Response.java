package io.hanbings.flows.common.interfaces;

import java.util.Map;

public interface Response {
    boolean exception();

    Throwable throwable();

    int code();

    String raw();

    Map<String, String> data();
}
