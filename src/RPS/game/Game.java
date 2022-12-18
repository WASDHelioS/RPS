package RPS.game;

import RPS.game.objects.Player;
import RPS.io.IOProvider;
import RPS.rules.RPSRuleSet;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final IOProvider ioProvider;

    public Game(IOProvider ioProvider) {
        this.ioProvider = ioProvider;
    }

    public void start() {

        Integer humanAmount = ioProvider.writeReadPromptForInt("Hello, and welcome to Rock Paper Scissors! Please enter the amount of Human players.");

        Integer botAmount = ioProvider.writeReadPromptForInt("Now, please enter the amount of bots you would like to add.");

        Integer rounds = ioProvider.writeReadPromptForInt("Lastly, please enter the amount of rounds you would like to play per match.");

        MatchEngine matchEngine = new MatchEngine(ioProvider);

        for(int i = 1; i <= humanAmount; i++) {
            ioProvider.write("Please enter your name for player " + i);
            String name = ioProvider.readString();
            matchEngine.withPlayer(new Player(name, false));
        }

        for(int i = 1; i <= botAmount; i++) {
            matchEngine.withPlayer(new Player("bot"+i, true));
        }

        matchEngine
                .withRounds(rounds)
                .withRuleSet(RPSRuleSet.getInstance()); // possibly changeable through input as well

        List<Player> matchWinner = matchEngine.run();

        if(matchWinner.size() == 1) {
            ioProvider.write("Congratulations to " + matchWinner.get(0).getName() + "! you have won");
        } else {
            ioProvider.write("Draw detected! Congratulations to ");
            matchWinner.forEach(p -> ioProvider.write(p.getName() + ", "));
            ioProvider.writeReadPromptForInt("for drawing your match.");
        }
    }

}
