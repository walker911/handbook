package com.walker.manual.engine;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
public class Main {
    public static void main(String[] args) {
        Facts facts = new Facts();
        facts.addFact("jobTitle", "CEO");
        facts.addFact("name", "Mark");

        BusinessRuleEngine engine = new BusinessRuleEngine(facts);
        engine.addRule(fact -> {
            final String jobTitle = fact.getFact("jobTitle");
            if ("CEO".equals(jobTitle)) {
                final String name = fact.getFact("name");
            }
        });

    }
}
