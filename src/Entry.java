import RPS.game.Game;
import RPS.io.IOProvider;

public class Entry {

    public static void main(String ...args) {
        Game game = new Game(new IOProvider());
        game.start();
    }
}
