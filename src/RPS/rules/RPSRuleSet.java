package RPS.rules;

import RPS.game.objects.Choice;


import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static RPS.game.objects.Choice.*;
import static RPS.rules.Outcome.*;

public class RPSRuleSet extends RuleSet{

    private static final RPSRuleSet instance = new RPSRuleSet();

    private final Map<Choice, Function<Choice, Outcome>> outcomes = Map.of(
            ROCK, (Choice c2) -> switch (c2) {
                case ROCK -> DRAW;
                case PAPER -> LOSE;
                case SCISSORS -> WIN;
            },
            PAPER, (Choice c2) -> switch (c2) {
                case ROCK -> WIN;
                case PAPER -> DRAW;
                case SCISSORS -> LOSE;
            },
            SCISSORS, (Choice c2) -> switch (c2) {
                case ROCK -> LOSE;
                case PAPER -> WIN;
                case SCISSORS -> DRAW;
            }
    );

    public static RPSRuleSet getInstance() {
        return instance;
    }

    private RPSRuleSet() {
    }

    @Override
    public List<Choice> getChoices() {
        return List.of(ROCK, PAPER, SCISSORS);
    }

    @Override
    protected Map<Choice, Function<Choice, Outcome>> getOutcomes() {
        return outcomes;
    }
}
