package com.walker.manual;

import com.walker.manual.engine.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/3
 */
public class InspectorTest {

    /**
     * 违反接口隔离原则（ISP）
     */
    @Test
    public void inspectOneConditionEvaluatesTrue() {
        final Facts facts = new Facts();
        facts.addFact("jobTitle", "CEO");
        final ConditionalAction conditionalAction = new JobTitleCondition();
        final Inspector inspector = new Inspector(conditionalAction);
        List<Report> reports = inspector.inspect(facts);

        Assertions.assertEquals(1, reports.size());
        Assertions.assertTrue(reports.get(0).isPositive());
    }

    private static class JobTitleCondition implements ConditionalAction {
        @Override
        public boolean evaluate(Facts facts) {
            return "CEO".equals(facts.getFact("jobTitle"));
        }

        @Override
        public void perform(Facts facts) {
            throw new UnsupportedOperationException();
        }
    }
}
