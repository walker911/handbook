package com.walker.manual.engine;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/4
 */
@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
