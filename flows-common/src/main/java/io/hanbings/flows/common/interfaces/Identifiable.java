package io.hanbings.flows.common.interfaces;

/**
 * 描述 OAuth 是否可提供身份识别信息
 * 具有该能力的 OAuth 适配器将继承该接口
 *
 * @param <D> 身份识别信息的数据模型
 * @param <W> 身份识别信息的错误信息
 */
@SuppressWarnings("unused")
public interface Identifiable<D extends Identify, W extends Identify.Wrong> {
    /**
     * 传入具有权限的 token
     *
     * @param token access token
     * @return Callback 数据
     */
    Callback<D, W> identify(String token);
}
