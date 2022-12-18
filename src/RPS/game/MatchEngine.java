package RPS.game;

import RPS.game.objects.Player;
import RPS.io.IOProvider;
import RPS.rules.RuleEngineImpl;
import RPS.rules.RuleSet;

import java.util.*;
import java.util.stream.Collectors;

public class MatchEngine {

    private final IOProvider ioProvider;
    private final RoundEngine roundEngine;
    private Map<Player, Integer> players = new HashMap<>();
    private RuleEngineImpl ruleEngine;
    private Integer rounds;

    public MatchEngine(IOProvider ioProvider) {
        this.ioProvider = ioProvider;
        roundEngine = new RoundEngine(ioProvider);
    }

    public List<Player> run() {

        for(int i = 1; i <= rounds; i++) {
            ioProvider.write("Starting round " + i);

            Player roundWinner = roundEngine.run(players.keySet().stream().toList(), ruleEngine);

            ioProvider.write("Congratulations, " + roundWinner.getName() + "! You have won round " + i);
            Integer newWin = players.get(roundWinner);
            newWin++;
            players.put(roundWinner, newWin);
        }

        Integer highestWinCount = players.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getValue();

        return players.entrySet().stream()
                .filter(playerIntegerEntry -> playerIntegerEntry.getValue() == highestWinCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public MatchEngine withPlayer(Player player) {
        players.put(player, 0);
        return this;
    }

    public MatchEngine withRuleSet(RuleSet ruleSet) {
        ruleEngine = new RuleEngineImpl(ruleSet);
        return this;
    }

    public MatchEngine withRounds(Integer rounds) {
        this.rounds = rounds;
        return this;
    }
}
