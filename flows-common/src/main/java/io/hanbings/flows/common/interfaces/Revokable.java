package io.hanbings.flows.common.interfaces;

/**
 * OAuth 提供商支持撤消 token 则会实现该接口
 *
 * @param <D> 数据模型
 * @param <W> 错误信息
 */
@SuppressWarnings("SpellCheckingInspection unused")
public interface Revokable<D extends Revoke, W extends Revoke.Wrong> {
    /**
     * 使用具有权限的 token 撤消 token
     *
     * @param token 这个 token 由服务提供商决定
     * @return Callback 数据
     */
    Callback<D, W> revoke(String token);
}
