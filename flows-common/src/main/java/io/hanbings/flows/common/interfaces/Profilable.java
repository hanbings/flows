package io.hanbings.flows.common.interfaces;

/**
 * 用于描述 OAuth 提供商提供的个人资料
 *
 * @param <D> 个人资料数据模型
 * @param <W> 错误信息
 */
@SuppressWarnings("SpellCheckingInspection unused")
public interface Profilable<D extends Profile, W extends Profile.Wrong> {
    /**
     * 使用有效 token 获取个人资料
     *
     * @param token access token
     * @return Callback 数据
     */
    Callback<D, W> profile(String token);
}