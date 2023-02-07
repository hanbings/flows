package io.hanbings.flows.common.interfaces;

/**
 * 用于 OAuth 重新授权（刷新）后所返回的数据模型
 * 通常包含 access token 作为权限凭据
 */
@SuppressWarnings("unused")
public interface Refresh {
    /**
     * access token
     *
     * @return 字符串凭据
     */
    String accessToken();

    /**
     * 错误信息
     */
    interface Wrong {
        /**
         * 错误信息
         *
         * @return 字符串错误信息
         */
        String error();
    }
}
