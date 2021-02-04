package com.walker.manual;

import com.walker.manual.engine.Action;
import com.walker.manual.engine.BusinessRuleEngine;
import com.walker.manual.engine.Facts;
import com.walker.manual.engine.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
public class BusinessRuleEngineTest {

    @Test
    public void shouldHaveNoRulesInitially() {
        final BusinessRuleEngine engine = new BusinessRuleEngine(null);

        Assertions.assertEquals(0, engine.count());
    }

    @Test
    public void shouldAddTwoActions() {
        final BusinessRuleEngine engine = new BusinessRuleEngine(null);

        engine.addRule((facts) -> {});
        engine.addRule((facts) -> {});

        Assertions.assertEquals(2, engine.count());
    }

    @Test
    public void shouldExecuteOneAction() {
        final BusinessRuleEngine engine = new BusinessRuleEngine(null);
        final Rule mockRule = mock(Rule.class);

        engine.addRule(mockRule);
        engine.run();

        verify(mockRule).perform(null);
    }

    @Test
    public void shouldPerformAnActionWithFacts() {
        final Rule mockRule = mock(Rule.class);
        final Facts facts = mock(Facts.class);
        final BusinessRuleEngine engine = new BusinessRuleEngine(facts);

        engine.addRule(mockRule);
        engine.run();

        verify(mockRule).perform(facts);
    }
}
