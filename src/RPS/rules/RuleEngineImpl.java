package RPS.rules;

import java.util.function.Supplier;

public class RuleEngineImpl extends RuleEngine{

    private final RuleSet ruleSet;

    public RuleEngineImpl(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    public Supplier<RuleSet> getRuleSet() {
        return () -> ruleSet;
    }


}
