import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private JLabel label;
    private JSpinner spinner;
    private JButton newGameButton;

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
    }
}
