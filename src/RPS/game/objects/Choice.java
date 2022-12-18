package RPS.game.objects;

public enum Choice {
    ROCK,
    PAPER,
    SCISSORS;

    public static Choice get(String in) {
        return Choice.valueOf(in.toUpperCase());
    }
}
