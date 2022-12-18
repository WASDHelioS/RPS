package RPS.game;

import RPS.game.objects.Choice;
import RPS.game.objects.Player;
import RPS.io.IOProvider;
import RPS.rules.Outcome;
import RPS.rules.RuleEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static RPS.rules.Outcome.LOSE;
import static RPS.util.RandomChoicePicker.pickChoice;

public class RoundEngine{

    private final IOProvider ioProvider;
    public RoundEngine (IOProvider ioProvider) {
        this.ioProvider = ioProvider;
    }
    public Player run(List<Player> players, RuleEngine ruleEngine) {

        List<Player> playerOutcomes = runTurn(players, ruleEngine);

        while(playerOutcomes.size() > 1)  {
            playerOutcomes = runTurn(playerOutcomes, ruleEngine);
        }

        return playerOutcomes.get(0);
    }

    private List<Player> runTurn(List<Player> players, RuleEngine ruleEngine) {
        Map<Player, Choice> playerChoiceMap = new HashMap<>();

        for(Player player : players) {
            Choice choice;
            if(player.isBot()) {
                choice = pickChoice(ruleEngine.getRuleSet().get().getChoices());
            } else {
                choice = ioProvider.readChoicePromptFromString(player.getName() + "'s turn to pick: " , ruleEngine.getRuleSet().get());
            }
            playerChoiceMap.put(player, choice);
        }

        playerChoiceMap.forEach((p, c) -> ioProvider.write("Player : " + p.getName() + " - Choice : " + c.name()));

        ioProvider.write("\n Choices are locked in! Determining if there is a winner this round.. \n");

        List<Player> results = ruleEngine.determineWinners(playerChoiceMap).entrySet().stream()
                .peek(this::printResults)
                .filter(playerOutcomeEntry -> !playerOutcomeEntry.getValue().equals(LOSE))
                .map(Map.Entry::getKey)
                .toList();

        ioProvider.write("\n");

        return results;
    }

    private void printResults(Map.Entry<Player, Outcome> playerOutcomeEntry) {
        ioProvider.write("Player : " + playerOutcomeEntry.getKey().getName() + " - Outcome : " + playerOutcomeEntry.getValue().name());
    }

}
