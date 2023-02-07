package io.hanbings.flows.common.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface Identify {
    @NotNull String openid();

    @NotNull String avatar();

    @NotNull String username();

    @NotNull String nickname();

    @Nullable String email();

    @Nullable String phone();

    interface Wrong {

    }
}
