package com.walker.manual.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
public class BusinessRuleEngine {

    private final List<Action> actions;
    private final Facts facts;

    public BusinessRuleEngine(final Facts facts) {
        this.actions = new ArrayList<>();
        this.facts = facts;
    }

    /**
     * 添加一个动作
     *
     * @param action
     */
    public void addAction(final Action action) {
        actions.add(action);
    }

    /**
     * 统计
     *
     * @return
     */
    public int count() {
        return actions.size();
    }

    /**
     * 运行
     */
    public void run() {
        actions.forEach(action -> action.perform(facts));
    }
}
