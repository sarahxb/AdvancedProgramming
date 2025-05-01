import java.util.*;

public class Board {
    private final List<String> words = new ArrayList<>();

    public synchronized void addWord(Player player, String word) {
        words.add(player.getName() + ": " + word);
        System.out.println(player.getName() + ": " + word);
    }

    @Override
    public String toString() {
        return words.toString();
    }
}
