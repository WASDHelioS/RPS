package RPS.rules;

import RPS.game.objects.Choice;
import RPS.game.objects.Player;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static RPS.rules.Outcome.*;

public abstract class RuleEngine {

    public Map<Player, Outcome> determineWinners(Map<Player, Choice> playerChoiceMap) {
        if(playerChoiceMap.values().stream().distinct().count() >= getRuleSet().get().getStaleMateChoiceThreshold()) {
            return playerChoiceMap.keySet().stream()
                    .collect(Collectors.toMap(player -> player, value -> STALEMATE));
        } else {
            return playerChoiceMap.entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    value ->
                                            playerChoiceMap.values().stream().map(c2 ->
                                                    (getRuleSet().get().applyRule(value.getValue(), c2))).distinct().filter(o -> o != DRAW).findFirst().orElse(DRAW))
                            );
        }
    }

    public abstract Supplier<RuleSet> getRuleSet();


}
