import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ConfigPanel configPanel;
    private DrawingPanel drawingPanel;
    private ControlPanel controlPanel;

    public MainFrame() {
        super("Connect the Dots Game");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }



    public void updateScores(double p1, double p2) {
        controlPanel.setScores(p1, p2);
    }
    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }
    private PlayerType playerOneType = PlayerType.HUMAN;
    private PlayerType playerTwoType = PlayerType.HUMAN;
    private Difficulty playerOneDifficulty = Difficulty.HARD;


    public PlayerType getPlayerOneType() {
        return playerOneType;
    }

    public PlayerType getPlayerTwoType() {
        return playerTwoType;
    }

    public Difficulty getPlayerOneDifficulty() {
        return playerOneDifficulty;
    }

    public Difficulty getPlayerTwoDifficulty() {
        return playerOneDifficulty;
    }

    public void setPlayerOneType(PlayerType type) {
        this.playerOneType = type;
    }

    public void setPlayerTwoType(PlayerType type) {
        this.playerTwoType = type;
    }

    public void setPlayerOneDifficulty(Difficulty difficulty) {
        this.playerOneDifficulty = difficulty;
    }


    public void startNewGame(int numDots) {
        drawingPanel.generateDots(numDots);
    }

}
