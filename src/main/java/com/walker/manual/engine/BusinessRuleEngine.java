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

    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRuleEngine(final Facts facts) {
        this.rules = new ArrayList<>();
        this.facts = facts;
    }

    /**
     * 添加一个动作
     *
     * @param action
     */
    public void addRule(final Rule rule) {
        rules.add(rule);
    }

    /**
     * 统计
     *
     * @return
     */
    public int count() {
        return rules.size();
    }

    /**
     * 运行
     */
    public void run() {
        rules.forEach(rule -> rule.perform(facts));
    }
}
