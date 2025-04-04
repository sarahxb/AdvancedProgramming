import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private final int canvasWidth = 600, canvasHeight = 400;
    private final int dotSize = 10;
    private List<Point> dots = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private Point selectedDot = null;
    private boolean isPlayerOneTurn = true;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickedDot = getClickedDot(e.getPoint());
                if (clickedDot != null) {
                    if (selectedDot == null) {
                        selectedDot = clickedDot;
                    } else if (!selectedDot.equals(clickedDot)) {
                        lines.add(new Line(selectedDot, clickedDot, isPlayerOneTurn ? Color.BLUE : Color.RED));
                        selectedDot = null;
                        isPlayerOneTurn = !isPlayerOneTurn;
                    }
                    repaint();
                }
            }
        });
    }

    public void generateDots(int numDots) {
        dots.clear();
        lines.clear();
        selectedDot = null;
        isPlayerOneTurn = true;

        Random rand = new Random();
        for (int i = 0; i < numDots; i++) {
            dots.add(new Point(rand.nextInt(canvasWidth - 50) + 25, rand.nextInt(canvasHeight - 50) + 25));
        }
        repaint();
    }

    private Point getClickedDot(Point click) {
        for (Point dot : dots) {
            if (click.distance(dot) < dotSize) {
                return dot;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);
        for (Point dot : dots) {
            g.fillOval(dot.x - dotSize / 2, dot.y - dotSize / 2, dotSize, dotSize);
        }

        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
        }
    }

    private static class Line {
        Point p1, p2;
        Color color;

        public Line(Point p1, Point p2, Color color) {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
        }
    }
}
