package io.hanbings.flows.common.interfaces;

/**
 * state 接口 自定义 state
 */
@SuppressWarnings("unused")
public interface State {
    /**
     * 添加一个有效 state
     *
     * @return state
     */
    String add();

    /**
     * 传入一个 state
     *
     * @param state 字符串类型 state
     */
    void add(String state);

    /**
     * 查询是否存在 state 过期也判断为不存在
     *
     * @param state 字符串类型 state
     * @return 是否存在
     */
    boolean has(String state);

    /**
     * 销毁 state
     *
     * @param state 字符串 state
     * @return 是否销毁成功
     */
    boolean fire(String state);

    /**
     * 清空
     */
    void empty();

    /**
     * 清空
     */
    void clean();
}
