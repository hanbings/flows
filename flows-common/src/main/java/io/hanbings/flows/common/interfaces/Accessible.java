package io.hanbings.flows.common.interfaces;

import io.hanbings.flows.common.OAuthCallback;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于描述可 OAuth 授权的提供商
 * @param <D> Data 继承 Access 接口的数据模型
 * @param <W> Wrong 继承 Access.Wrong 接口的错误信息模型
 */
@SuppressWarnings("unused")
public interface Accessible<D extends Access, W extends Access.Wrong> {
    /**
     * 生成授权 url
     * @return 字符串 url
     */
    default String authorize() {
        return authorize(null, null);
    }

    /**
     * 指定 Scope 生成授权 url
     * @param scopes 指定的 Scope 列表
     * @return 字符串 url
     */
    default String authorize(List<String> scopes) {
        return authorize(scopes, null);
    }

    /**
     * 指定 query 参数生成授权 url
     * @param params 指定的 http query 参数
     * @return 字符串 url
     */
    default String authorize(Map<String, String> params) {
        return authorize(null, params);
    }

    /**
     * 指定 Scope 和 query 参数生成授权 url
     * @param scopes 指定的 Scope 列表
     * @param params 指定的 http query 参数
     * @return 字符串 url
     */
    String authorize(List<String> scopes, Map<String, String> params);

    /**
     * code 换 token 执行授权码模式流程
     * @param url callback 返回的完整 url 将自动解析
     * @return Callback 数据
     */
    default Callback<D, W> token(String url) {
        return token(url, null);
    }

    /**
     * code 换 token 执行授权码模式流程
     * @param url callback 返回的完整 url 将自动解析
     * @param redirect 回调数据被传送到的地址 需要在提供商处注册该域
     * @return Callback 数据
     */
    default Callback<D, W> token(String url, String redirect) {
        String code;
        String state;

        try {
            code = Accessible.Utils.params(url).get("code");
            state = Accessible.Utils.params(url).get("state");
        } catch (URISyntaxException e) {
            return OAuthCallback.exception(null, e);
        }

        return token(code, state, redirect);
    }

    /**
     * 使用授权码获取 token
     * @param code 授权码
     * @param state state 码
     * @param redirect 回调地址
     * @return Callback 数据
     */
    Callback<D, W> token(String code, String state, String redirect);

    class Utils {
        public static String params(String url, Map<String, String> params) throws URISyntaxException {
            URI check = new URI(url);

            StringBuilder builder = new StringBuilder();

            if (check.getQuery() != null) {
                builder.append(check.getQuery()).append("&");
            }

            params.forEach((k, v) -> {
                builder.append(k).append("=").append(v).append("&");
            });

            return new URI(
                    check.getScheme(),
                    check.getAuthority(),
                    check.getPath(),
                    builder.toString(),
                    check.getFragment()
            ).toString();
        }

        public static Map<String, String> params(String url) throws URISyntaxException {
            return new HashMap<>() {{
                Arrays.stream(new URI(url).getQuery().split("&")).forEach(s -> {
                    put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=")));
                });
            }};
        }
    }
}
