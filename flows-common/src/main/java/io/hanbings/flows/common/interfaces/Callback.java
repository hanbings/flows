package io.hanbings.flows.common.interfaces;

import java.util.function.Consumer;

/**
 * 回调数据
 *
 * @param <D> 回调数据模型
 * @param <W> 回调错误信息
 */
@SuppressWarnings("unused")
public interface Callback<D, W> {
    /**
     * 当请求返回成功（200 状态码）时该数据不为空
     *
     * @return 指定的回调数据
     */
    D data();

    /**
     * 当请求返回失败（或非 200 状态码）时该数据不为空
     *
     * @return 指定的错误信息
     */
    W wrong();

    /**
     * 请求发出成功并收到返回时（任意状态码）不为空
     *
     * @return 请求所返回的数据原始内容
     */
    Response response();

    /**
     * 请求中携带的 token
     *
     * @return access token
     */
    String token();

    /**
     * 请求发生异常时不为空
     *
     * @return 异常
     */
    Throwable throwable();

    /**
     * 请求成功发起且收到服务器的消息（状态码 200）
     *
     * @param data 处理数据的逻辑
     * @return Callback 成功
     */
    Callback<D, W> succeed(Consumer<D> data);

    /**
     * 请求成功发出且收到服务器的消息但是没有对应的状态码解析器
     *
     * @param response 处理数据的逻辑
     * @return Callback 请求成功
     */
    Callback<D, W> completed(Consumer<Response> response);

    /**
     * 请求成功发出且收到服务器消息但可能是失败状态（状态码 404 502 等）<br>
     * 状态码是否触发该回调取决于实现
     *
     * @param wrong 错误信息
     * @return Callback 失败
     */
    Callback<D, W> fail(Consumer<W> wrong);

    /**
     * 请求发生异常
     *
     * @param throwable 异常
     * @return Callback 异常
     */
    Callback<D, W> except(Consumer<Throwable> throwable);

    /**
     * 是否请求成功且成功解析状态码为 200 的数据
     *
     * @return 是否成功
     */
    boolean success();
}
