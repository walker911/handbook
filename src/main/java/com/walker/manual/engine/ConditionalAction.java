package com.walker.manual.engine;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
public interface ConditionalAction {

    boolean evaluate(Facts facts);

    void perform(Facts facts);
}
