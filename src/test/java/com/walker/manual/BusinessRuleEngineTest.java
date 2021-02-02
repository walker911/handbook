package com.walker.manual;

import com.walker.manual.engine.Action;
import com.walker.manual.engine.BusinessRuleEngine;
import com.walker.manual.engine.Facts;
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

        engine.addAction((facts) -> {});
        engine.addAction((facts) -> {});

        Assertions.assertEquals(2, engine.count());
    }

    @Test
    public void shouldExecuteOneAction() {
        final BusinessRuleEngine engine = new BusinessRuleEngine(null);
        final Action mockAction = mock(Action.class);

        engine.addAction(mockAction);
        engine.run();

        verify(mockAction).perform(null);
    }

    @Test
    public void shouldPerformAnActionWithFacts() {
        final Action mockAction = mock(Action.class);
        final Facts facts = mock(Facts.class);
        final BusinessRuleEngine engine = new BusinessRuleEngine(facts);

        engine.addAction(mockAction);
        engine.run();

        verify(mockAction).perform(facts);
    }
}
