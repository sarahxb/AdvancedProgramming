import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton loadButton;
    private JButton saveButton;
    private JButton exitButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 3));

        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");

        exitButton.addActionListener(this::exitGame);

        add(loadButton);
        add(saveButton);
        add(exitButton);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
