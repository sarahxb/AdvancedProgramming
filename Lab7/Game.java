import java.util.*;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new MockD();
    private final List<Player> players = new ArrayList<>();

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }


    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
            new Thread(player).start();
        }
    }


}
