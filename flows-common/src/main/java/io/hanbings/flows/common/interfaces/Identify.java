package io.hanbings.flows.common.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 用于描述 OAuth 提供商提供的身份识别信息
 */
@SuppressWarnings("unused")
public interface Identify {
    /**
     * 该身份提供商用户的唯一 ID
     *
     * @return 唯一 ID 字符串
     */
    @NotNull String openid();

    /**
     * 该身份提供商用户的头像
     *
     * @return 头像 url
     */
    @NotNull String avatar();

    /**
     * 用户名
     *
     * @return 字符串用户名
     */
    @NotNull String username();

    /**
     * 用户昵称
     *
     * @return 字符串用户名
     */
    @NotNull String nickname();

    /**
     * 邮件地址 如果提供商有多个邮件地址将默认填入第一个
     *
     * @return 邮件地址字符串
     */
    @Nullable String email();

    /**
     * 手机号码
     *
     * @return 手机号码字符串
     */
    @Nullable String phone();

    /**
     * 错误信息
     */
    interface Wrong {

    }
}
