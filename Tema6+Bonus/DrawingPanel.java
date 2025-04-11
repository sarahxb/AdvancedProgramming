import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private final int canvasWidth = 600, canvasHeight = 400;
    private final int dotSize = 10;
    private List<Point> dots = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private Point selectedDot = null;
    private boolean isPlayerOneTurn = true;
    private double playerOneScore = 0;
    private double playerTwoScore = 0;
    private Point dragStartDot = null;
    private Point dragEndPoint = null; // for visual drag line

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        setBackground(Color.WHITE);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStartDot = getClickedDot(e.getPoint());
                dragEndPoint = null;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragStartDot != null) {
                    Point targetDot = getClickedDot(e.getPoint());
                    if (targetDot != null && !dragStartDot.equals(targetDot) && !lineExists(dragStartDot, targetDot)) {
                        addLine(dragStartDot, targetDot);
                    }
                }
                dragStartDot = null;
                dragEndPoint = null;
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragStartDot != null) {
                    dragEndPoint = e.getPoint();
                    repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickedDot = getClickedDot(e.getPoint());
                if (clickedDot != null) {
                    if (selectedDot == null) {
                        selectedDot = clickedDot;
                    } else if (!selectedDot.equals(clickedDot) && !lineExists(selectedDot, clickedDot)) {
                        addLine(selectedDot, clickedDot);
                        selectedDot = null;
                    } else {
                        selectedDot = null;
                    }
                    repaint();
                    makeAIMove();
                }
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }


    private void addLine(Point p1, Point p2) {

        Line newLine = new Line(p1, p2, isPlayerOneTurn ? Color.BLUE : Color.RED);
        lines.add(newLine);
        double bestScore = computeBestScore();
        frame.setTitle("Connect the Dots Game - Best possible score: " + String.format("%.2f", bestScore));

        double length = p1.distance(p2);
        if (isPlayerOneTurn) {
            playerOneScore += length;
        } else {
            playerTwoScore += length;
        }


        frame.updateScores(playerOneScore, playerTwoScore);

        isPlayerOneTurn = !isPlayerOneTurn;


        repaint();

       //ceva cred ca e gresit aici
        new javax.swing.Timer(250, e -> {
            boolean isAIturn = (isPlayerOneTurn && frame.getPlayerOneType() == PlayerType.AI)
                    || (!isPlayerOneTurn && frame.getPlayerTwoType() == PlayerType.AI);
            if (isAIturn) {
                makeAIMove();
            }
        }) {{
            setRepeats(false);
            start();
        }};
    }






    public void generateDots(int numDots) {
        dots.clear();
        lines.clear();
        selectedDot = null;
        isPlayerOneTurn = true;
        playerOneScore = 0;
        playerTwoScore = 0;
        frame.updateScores(0, 0);

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

    private boolean lineExists(Point p1, Point p2) {
        for (Line line : lines) {
            if ((line.p1.equals(p1) && line.p2.equals(p2)) ||
                    (line.p1.equals(p2) && line.p2.equals(p1))) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.PINK);
        for (Point dot : dots) {
            g.fillOval(dot.x - dotSize / 2, dot.y - dotSize / 2, dotSize, dotSize);
        }

        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
        }

        if (dragStartDot != null && dragEndPoint != null) {
            g.setColor(isPlayerOneTurn ? Color.BLUE : Color.RED);
            g.drawLine(dragStartDot.x, dragStartDot.y, dragEndPoint.x, dragEndPoint.y);
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

    public void saveGame(File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            GameState state = new GameState();
            state.dots = new ArrayList<>(dots);
            state.isPlayerOneTurn = isPlayerOneTurn;
            state.playerOneScore = playerOneScore;
            state.playerTwoScore = playerTwoScore;
            state.lines = lines.stream()
                    .map(l -> new GameState.LineData(l.p1, l.p2, l.color))
                    .collect(Collectors.toList());
            out.writeObject(state);
            JOptionPane.showMessageDialog(this, "Game saved successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving game.");
        }
    }

    public void loadGame(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            GameState state = (GameState) in.readObject();
            this.dots = state.dots;
            this.isPlayerOneTurn = state.isPlayerOneTurn;
            this.playerOneScore = state.playerOneScore;
            this.playerTwoScore = state.playerTwoScore;
            this.lines = state.lines.stream()
                    .map(ld -> new Line(ld.p1, ld.p2, ld.color))
                    .collect(Collectors.toList());
            frame.updateScores(playerOneScore, playerTwoScore);
            selectedDot = null;
            repaint();
            JOptionPane.showMessageDialog(this, "Game loaded successfully.");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading game.");
        }
        double bestScore = computeBestScore();
        frame.setTitle("Connect the Dots Game - Best possible score: " + String.format("%.2f", bestScore));

    }

    public void exportAsImage(File file) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        g2.dispose();

        try {
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(this, "Image saved successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving image.");
        }
    }

    public double computeBestScore() {
        if (dots.size() <= 1) return 0;


        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < dots.size(); i++) {
            for (int j = i + 1; j < dots.size(); j++) {
                double weight = dots.get(i).distance(dots.get(j));
                edges.add(new Edge(i, j, weight));
            }
        }


        edges.sort(Comparator.comparingDouble(e -> e.weight));


        int[] parent = new int[dots.size()];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        double totalWeight = 0;
        for (Edge edge : edges) {
            int root1 = find(parent, edge.u);
            int root2 = find(parent, edge.v);
            if (root1 != root2) {
                totalWeight += edge.weight;
                parent[root1] = root2;
            }
        }

        return totalWeight;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private static class Edge {
        int u, v;
        double weight;

        Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }


    private List<Line> generateMinimumSpanningTree() {
        List<Line> allEdges = new ArrayList<>();
        for (int i = 0; i < dots.size(); i++) {
            for (int j = i + 1; j < dots.size(); j++) {
                allEdges.add(new Line(dots.get(i), dots.get(j), Color.BLACK));
            }
        }
        allEdges.sort(Comparator.comparingDouble(e -> e.p1.distance(e.p2)));

        List<Line> mst = new ArrayList<>();
        Map<Point, Set<Point>> connected = new HashMap<>();
        for (Point p : dots) {
            Set<Point> set = new HashSet<>();
            set.add(p);
            connected.put(p, set);
        }

        for (Line edge : allEdges) {
            Set<Point> set1 = connected.get(edge.p1);
            Set<Point> set2 = connected.get(edge.p2);

            if (set1 != set2) {
                mst.add(edge);
                set1.addAll(set2);
                for (Point p : set2) {
                    connected.put(p, set1);
                }
            }
        }
        return mst;
    }
    private List<Line> generateRandomSpanningTree() {
        List<Line> allEdges = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < dots.size(); i++) {
            for (int j = i + 1; j < dots.size(); j++) {
                Line edge = new Line(dots.get(i), dots.get(j), Color.BLACK);
                allEdges.add(edge);
            }
        }
        allEdges.sort(Comparator.comparingDouble(e -> e.p1.distance(e.p2) * (0.9 + Math.random() * 0.2)));

        List<Line> mst = new ArrayList<>();
        Map<Point, Set<Point>> connected = new HashMap<>();
        for (Point p : dots) {
            Set<Point> set = new HashSet<>();
            set.add(p);
            connected.put(p, set);
        }

        for (Line edge : allEdges) {
            Set<Point> set1 = connected.get(edge.p1);
            Set<Point> set2 = connected.get(edge.p2);

            if (set1 != set2) {
                mst.add(edge);
                set1.addAll(set2);
                for (Point p : set2) {
                    connected.put(p, set1);
                }
            }
        }
        return mst;
    }


    private double calculateTreeScore(List<Line> tree) {
        return tree.stream().mapToDouble(l -> l.p1.distance(l.p2)).sum();
    }

    //merge cand vrea el AI-ul :(
    private void makeAIMove() {
        PlayerType currentPlayerType = isPlayerOneTurn ? frame.getPlayerOneType() : frame.getPlayerTwoType();
        Difficulty difficulty =  frame.getPlayerOneDifficulty();

        if (currentPlayerType != PlayerType.AI) return;

        List<Point> availableMoves = getAvailableMoves();

        if (!availableMoves.isEmpty()) {
            Point bestMove = getBestMove(availableMoves, difficulty);

            if (selectedDot == null) {
                selectedDot = bestMove;
            } else if (!selectedDot.equals(bestMove) && !lineExists(selectedDot, bestMove)) {
                addLine(selectedDot, bestMove);
                selectedDot = null;
            } else {
                selectedDot = null;
            }
        }
    }



    private List<Point> getAvailableMoves() {
        List<Point> availableMoves = new ArrayList<>();

        for (Point dot : dots) {
            if (!dot.equals(selectedDot)) {
                availableMoves.add(dot);
            }
        }
        return availableMoves;
    }

    private Point getBestMove(List<Point> availableMoves, Difficulty difficulty) {
        Random rand = new Random();

        if (selectedDot == null || availableMoves.isEmpty()) {

            return availableMoves.get(rand.nextInt(availableMoves.size()));
        }

        switch (difficulty) {
            case EASY:

                return availableMoves.get(rand.nextInt(availableMoves.size()));

            case MEDIUM:

                return availableMoves.stream()
                        .min(Comparator.comparingDouble(p -> p.distance(selectedDot)))
                        .orElse(availableMoves.get(0));

            case HARD:

                return availableMoves.stream()
                        .max(Comparator.comparingDouble(p -> p.distance(selectedDot)))
                        .orElse(availableMoves.get(0));

            default:
                return availableMoves.get(rand.nextInt(availableMoves.size()));
        }
    }


    private List<List<Line>> getSpanningTreesSorted() {
        List<List<Line>> trees = new ArrayList<>();
        trees.add(generateMinimumSpanningTree());
        for (int i = 0; i < 5; i++) {
            trees.add(generateRandomSpanningTree());
        }
        trees.sort(Comparator.comparingDouble(this::calculateTreeScore));
        return trees;
    }



}
