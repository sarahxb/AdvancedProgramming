import java.util.*;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private boolean running = true;
    private final List<Tile> tiles = new ArrayList<>();
    private int score = 0;
    private final Set<String> usedWords = new HashSet<>();


    public Player(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public Set<String> getUsedWords() {
        return usedWords;
    }

    private boolean submitWord() {
        if (tiles.isEmpty()) {
            tiles.addAll(game.getBag().extractTiles(7));
        }
        String word = findWordFromTiles();

        if (word != null) {
            game.getBoard().addWord(this, word);
            int points = calculateWordPoints(word);
            score += points;

            removeTilesForWord(word);
            List<Tile> newTiles = game.getBag().extractTiles(word.length());
            tiles.addAll(newTiles);

              return true;
        } else {

            tiles.clear();
            tiles.addAll(game.getBag().extractTiles(7));
            return false;
        }
    }

    private String findWordFromTiles() {
        List<Character> available = new ArrayList<>();
        for (Tile tile : tiles) {
            available.add(tile.getLetter());
        }

        for (int size = available.size(); size >= 2; size--) {
            List<List<Character>> combinations = generateCombinations(available, size);
            for (List<Character> combo : combinations) {
                StringBuilder sb = new StringBuilder();
                for (char c : combo) {
                    sb.append(c);
                }
                String candidate = sb.toString().toLowerCase();
                if (game.getDictionary().isWord(candidate) && !usedWords.contains(candidate)) {
                    usedWords.add(candidate);
                    return candidate;
                }
            }
        }
        return null;
    }


    private List<List<Character>> generateCombinations(List<Character> letters, int length) {
        List<List<Character>> result = new ArrayList<>();
        generateCombinationsHelper(letters, new ArrayList<>(), result, length, 0);
        return result;
    }

    private void generateCombinationsHelper(List<Character> letters, List<Character> current, List<List<Character>> result, int length, int index) {
        if (current.size() == length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < letters.size(); i++) {
            current.add(letters.get(i));
            generateCombinationsHelper(letters, current, result, length, i + 1);
            current.remove(current.size() - 1);
        }
    }

    private int calculateWordPoints(String word) {
        int points = 0;
        for (char c : word.toCharArray()) {
            for (Tile t : tiles) {
                if (t.getLetter() == c) {
                    points += t.getPoints();
                    break;
                }
            }
        }
        return points;
    }

    private void removeTilesForWord(String word) {
        for (char c : word.toCharArray()) {
            for (Iterator<Tile> it = tiles.iterator(); it.hasNext();) {
                Tile t = it.next();
                if (t.getLetter() == c) {
                    it.remove();
                    break;
                }
            }
        }
    }

    @Override
    public void run() {
        while (game.isRunning()) {
            synchronized (game) {
                while (!game.isMyTurn(this) && game.isRunning()) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }


                if (!game.isRunning() || game.getBag().isEmpty()) {
                    game.nextTurn();
                    game.notifyAll();
                    break;
                }

                if (!submitWord()) {
                    running = false;
                }

                game.nextTurn();
                game.notifyAll();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(name + " has stopped with " + score + " points.");
    }


}
