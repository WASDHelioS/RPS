package RPS.util;

import RPS.game.objects.Choice;

import java.util.List;
import java.util.Random;

public class RandomChoicePicker {

    public static Choice pickChoice(List<Choice> choices) {
        return choices.get(new Random().nextInt(0, choices.size()-1));
    }
}
