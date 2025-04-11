import java.util.List;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private boolean running = true;

    public Player(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty()) {
            return false;
        }


        StringBuilder wordBuilder = new StringBuilder();
        for (Tile tile : extracted) {
            wordBuilder.append(tile.getLetter());
        }
        String word = wordBuilder.toString();


        game.getBoard().addWord(this, word);


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return true;
    }

    @Override
    public void run() {
        while (running && !game.getBag().isEmpty()) {
            if (!submitWord()) {
                running = false;
            }
        }
        System.out.println(name + " has stopped.");
    }
}
