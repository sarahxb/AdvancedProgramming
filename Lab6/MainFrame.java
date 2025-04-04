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

    public void startNewGame(int numDots) {
        drawingPanel.generateDots(numDots);
    }
}
