package com.walker.manual.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 条件检查类
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
public class Inspector {

    private final List<ConditionalAction> conditionalActions;

    public Inspector(final ConditionalAction... conditionalActions) {
        this.conditionalActions = Arrays.asList(conditionalActions);
    }

    public List<Report> inspect(final Facts facts) {
        final List<Report> reports = new ArrayList<>();
        for (ConditionalAction conditionalAction : conditionalActions) {
            final boolean conditionResult = conditionalAction.evaluate(facts);
            reports.add(new Report(conditionalAction, facts, conditionResult));
        }
        return reports;
    }

}
