public class TimeKeeper implements Runnable {
    private final Game game;
    private final int limit;

    public TimeKeeper(Game game, int limit) {
        this.game = game;
        this.limit = limit;
    }

    @Override
    public void run() {
        int time = 0;
        try {
            while (time < limit && !game.getBag().isEmpty()) {
                Thread.sleep(1000);
                time++;
                System.out.println("Time time: " + time );
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(" time limit reached or bag is empty . stopping game");
        game.stopGame();

        synchronized (game) {
            game.notifyAll();
        }
    }
}

