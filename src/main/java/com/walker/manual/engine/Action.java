package com.walker.manual.engine;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
@FunctionalInterface
public interface Action {
    void perform(Facts facts);
}
