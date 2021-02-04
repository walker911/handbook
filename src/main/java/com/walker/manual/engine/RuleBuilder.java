package com.walker.manual.engine;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/4
 */
public class RuleBuilder {

    private final Condition condition;

    private RuleBuilder(Condition condition) {
        this.condition = condition;
    }

    public static RuleBuilder when(final Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(final Action action) {
        return new DefaultRule(condition, action);
    }

}
