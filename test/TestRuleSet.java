import RPS.game.objects.Choice;
import RPS.game.objects.Player;
import RPS.rules.Outcome;
import RPS.rules.RPSRuleSet;
import RPS.rules.RuleEngine;
import RPS.rules.RuleEngineImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static RPS.game.objects.Choice.*;
import static RPS.rules.Outcome.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRuleSet {

    RuleEngine ruleEngine = new RuleEngineImpl(RPSRuleSet.getInstance());
    @Nested
    class Two_Player {

        @Test
        void win_condition_1() {

            Map<Player, Choice> input = Map.of(new Player("Player1", false), ROCK, new Player("Player2", false), PAPER);

            Map<Player, Outcome> outcomes = ruleEngine.determineWinners(input);

            assertEquals(2, outcomes.size());
            outcomes.forEach((key, value) -> {
                if(key.getName().equals("Player1")) assertEquals(LOSE, value);
                if(key.getName().equals("Player2")) assertEquals(WIN, value);
            });
        }

        @Test
        void win_condition_2() {

            Map<Player, Choice> input = Map.of(new Player("Player1", false), ROCK, new Player("Player2", false), SCISSORS);

            Map<Player, Outcome> outcomes = ruleEngine.determineWinners(input);

            assertEquals(2, outcomes.size());
            outcomes.forEach((key, value) -> {
                if(key.getName().equals("Player1")) assertEquals(WIN, value);
                if(key.getName().equals("Player2")) assertEquals(LOSE, value);
            });
        }

        @Test
        void draw_condition() {

            Map<Player, Choice> input = Map.of(new Player("Player1",false), ROCK, new Player("Player2",false), ROCK);

            Map<Player, Outcome> outcomes = ruleEngine.determineWinners(input);

            assertEquals(2, outcomes.size());
            outcomes.forEach((key, value) -> assertEquals(DRAW, value));
        }
    }

    @Nested
    class More_Than_Two_Player {
        @Test
        void win_condition_1() {

            Map<Player, Choice> input = Map.of(new Player("Player1",false), ROCK, new Player("Player2",false), PAPER, new Player("Player3",false), ROCK);

            Map<Player, Outcome> outcomes = ruleEngine.determineWinners(input);

            assertEquals(3, outcomes.size());
            outcomes.forEach((key, value) -> {
                if(key.getName().equals("Player1")) assertEquals(LOSE, value);
                if(key.getName().equals("Player2")) assertEquals(WIN, value);
                if(key.getName().equals("Player3")) assertEquals(LOSE, value);
            });
        }

        @Test
        void win_condition_2() {

            Map<Player, Choice> input = Map.of(new Player("Player1",false), PAPER, new Player("Player2",false), PAPER, new Player("Player3",false), ROCK);

            Map<Player, Outcome> outcomes = ruleEngine.determineWinners(input);

            assertEquals(3, outcomes.size());
            outcomes.forEach((key, value) -> {
                if(key.getName().equals("Player1")) assertEquals(WIN, value);
                if(key.getName().equals("Player2")) assertEquals(WIN, value);
                if(key.getName().equals("Player3")) assertEquals(LOSE, value);
            });
        }

        @Test
        void draw_condition() {
            Map<Player, Choice> input = Map.of(new Player("Player1",false), PAPER, new Player("Player2",false), PAPER, new Player("Player3",false), PAPER);

            Map<Player, Outcome> outcomes = ruleEngine.determineWinners(input);

            assertEquals(3, outcomes.size());
            outcomes.forEach((key, value) -> assertEquals(DRAW, value));
        }

        @Test
        void stalemate_condition() {
            Map<Player, Choice> input = Map.of(new Player("Player1",false), ROCK, new Player("Player2",false), PAPER, new Player("Player3",false), SCISSORS);

            Map<Player, Outcome> outcomes = ruleEngine.determineWinners(input);

            assertEquals(3, outcomes.size());
            outcomes.forEach((key, value) -> assertEquals(STALEMATE, value));
        }
    }


}
