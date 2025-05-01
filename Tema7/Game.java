import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new MockD();
    private final List<Player> players = new ArrayList<>();
    private final List<Thread> playerThreads = new ArrayList<>();
    private volatile boolean running = true;
    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }
    public Dictionary getDictionary() {
        return dictionary;
    }
    public boolean isRunning() {
        return running;
    }

    public void stopGame() {
        running = false;
        for (Thread t : playerThreads) {
            t.interrupt();
        }
    }


    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {

        Thread timeThread = new Thread(new TimeKeeper(this, 60));
        timeThread.setDaemon(true);
        timeThread.start();


        for (Player player : players) {
            Thread thread = new Thread(player);
            playerThreads.add(thread);
            thread.start();
        }


        for (Thread t : playerThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        showLeaderboard();
    }





    public void showLeaderboard() {
        System.out.println("\n Final Leaderboard:");


        players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getScore() + " points");

        }


    }

    private int currentPlayerIndex = 0;

    public synchronized boolean isMyTurn(Player player) {
        return players.get(currentPlayerIndex) == player;
    }

    public synchronized void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        notifyAll();
    }

}