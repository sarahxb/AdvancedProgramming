
import java.util.*;

public class Bag {
    private final Queue<Tile> letters = new LinkedList<>();
    private final Random random = new Random();

    public Bag() {
        for (char c = 'a'; c <= 'z'; c++) {
            int points = random.nextInt(10) + 1;
            for (int i = 0; i < 10; i++) {
                letters.add(new Tile(c, points));
            }
        }
    }

    public synchronized List<Tile> extractTiles(int nr) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < nr; i++) {
            if (letters.isEmpty()) {
                break;
            }
            extracted.add(letters.poll());
        }
        return extracted;
    }

    public synchronized boolean isEmpty() {
        return letters.isEmpty();
    }
}

