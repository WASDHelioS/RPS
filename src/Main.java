import RPS.game.Game;
import RPS.io.IOProvider;

public class Main {

    public static void main(String ...args) {
        Game game = new Game(new IOProvider());
        game.start();
    }
}
