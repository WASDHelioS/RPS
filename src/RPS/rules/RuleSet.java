package RPS.rules;

import RPS.game.objects.Choice;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class RuleSet {

    protected Outcome applyRule(Choice c1, Choice c2) {
        return getOutcomes().get(c1).apply(c2);
    }

    protected abstract Map<Choice, Function<Choice, Outcome>> getOutcomes();
    public abstract List<Choice> getChoices();

    protected Integer getStaleMateChoiceThreshold() {
        return getChoices().size();
    };
}
