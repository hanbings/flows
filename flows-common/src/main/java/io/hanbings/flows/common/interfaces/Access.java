package io.hanbings.flows.common.interfaces;

/**
 * 用于 OAuth 授权后所返回的数据模型
 * 通常包含 access token 作为权限凭据
 */
@SuppressWarnings("unused")
public interface Access {
    /**
     * 权限凭据
     *
     * @return 字符串类型凭据
     */
    String accessToken();

    /**
     * 用于描述 OAuth 授权错误返回的数据模型
     */
    interface Wrong {
        /**
         * 错误的详细信息
         *
         * @return 错误信息
         */
        String error();
    }
}
