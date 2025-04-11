import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private JLabel label;
    private JSpinner spinner;
    private JButton newGameButton;
    private JComboBox<PlayerType> playerOneTypeCombo;
    private JComboBox<PlayerType> playerTwoTypeCombo;
    private JComboBox<Difficulty> playerOneDifficultyCombo;


    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new FlowLayout());


        label = new JLabel("Number of dots:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        newGameButton = new JButton("New Game");


        newGameButton.addActionListener(e -> {
            int numDots = (int) spinner.getValue();
            frame.startNewGame(numDots);
        });

        add(label);
        add(spinner);
        add(newGameButton);


        playerOneTypeCombo = new JComboBox<>(PlayerType.values());
        playerTwoTypeCombo = new JComboBox<>(PlayerType.values());
        playerOneDifficultyCombo = new JComboBox<>(Difficulty.values());



        playerOneTypeCombo.addActionListener(e -> frame.setPlayerOneType((PlayerType) playerOneTypeCombo.getSelectedItem()));
        playerTwoTypeCombo.addActionListener(e -> frame.setPlayerTwoType((PlayerType) playerTwoTypeCombo.getSelectedItem()));
        playerOneDifficultyCombo.addActionListener(e -> frame.setPlayerOneDifficulty((Difficulty) playerOneDifficultyCombo.getSelectedItem()));

        add(new JLabel("Player 1 Type:"));
        add(playerOneTypeCombo);
        add(new JLabel("Difficulty:"));
        add(playerOneDifficultyCombo);
        add(new JLabel("Player 2 Type:"));
        add(playerTwoTypeCombo);

    }
}

