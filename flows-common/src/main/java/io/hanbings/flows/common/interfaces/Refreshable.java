package io.hanbings.flows.common.interfaces;

/**
 * 支持刷新的 OAuth 提供商适配将实现该接口
 *
 * @param <D> 刷新后返回的数据模型
 * @param <W> 错误信息
 */
@SuppressWarnings("unused")
public interface Refreshable<D extends Refresh, W extends Refresh.Wrong> {
    /**
     * 刷新 token
     *
     * @param token 旧的凭据或刷新凭据 取决于提供商要求
     * @return Callback 数据
     */
    Callback<D, W> refresh(String token);
}
