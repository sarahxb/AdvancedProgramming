import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton loadButton;
    private JButton saveButton;
    private JButton exitButton;
    private JLabel scoreLabel;
    private JButton exportButton;


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 3));

        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");


        loadButton.addActionListener(this::loadGame);
        exportButton = new JButton("Export");
        exportButton.addActionListener(this::exportImage);
        saveButton.addActionListener(this::saveGame);
        exitButton.addActionListener(e -> frame.dispose());

        setLayout(new GridLayout(1, 4)); // update layout to fit 4 buttons
        add(loadButton);
        add(saveButton);
        add(exportButton);
        add(exitButton);

        scoreLabel = new JLabel("Player 1: 0.0 | Player 2: 0.0");
        add(scoreLabel);

    }

    private void loadGame(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            frame.getDrawingPanel().loadGame(file);
        }
    }

    private void saveGame(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            frame.getDrawingPanel().saveGame(file);
        }
    }

    public void setScores(double p1, double p2) {
        scoreLabel.setText(String.format("Player 1: %.1f | Player 2: %.1f", p1, p2));
    }

    private void exportImage(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!file.getName().endsWith(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }
            frame.getDrawingPanel().exportAsImage(file);
        }
    }


}
