import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {
    public List<Point> dots;
    public List<LineData> lines;
    public boolean isPlayerOneTurn;
    public double playerOneScore;
    public double playerTwoScore;

    public static class LineData implements Serializable {
        public Point p1, p2;
        public Color color;

        public LineData(Point p1, Point p2, Color color) {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
        }
    }
}
