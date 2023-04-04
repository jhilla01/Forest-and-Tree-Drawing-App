import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Forest extends JFrame {
    private final DrawingPanel drawingPanel;
    private final JTextField treeCountField;
    private final JLabel promptLabel;

    public Forest() {
        super("Forest");
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        promptLabel = new JLabel("Please enter the number of trees:");
        treeCountField = new JTextField(10);
        inputPanel.add(promptLabel);
        inputPanel.add(treeCountField);
        add(inputPanel, BorderLayout.SOUTH);

        treeCountField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int treeCount = Integer.parseInt(treeCountField.getText());
                drawingPanel.setTreeCount(treeCount);
                drawingPanel.repaint();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
    }

    private class DrawingPanel extends JPanel {
        private int treeCount = 0;

        public void setTreeCount(int treeCount) {
            this.treeCount = treeCount;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < treeCount; i++) {
                int x = new Random().nextInt(getWidth());
                int y = new Random().nextInt(getHeight());
                Color treeColor = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                paintTree(g, x, y, treeColor);
            }
        }
    }

    private void paintTree(Graphics g, int x, int y, Color treeColor) {
        g.setColor(treeColor);
        g.fillOval(x, y, 40, 50);

        g.setColor(Color.BLACK);
        g.fillRect(x + 15, y + 50, 10, 30);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Forest forest = new Forest();
                forest.setVisible(true);
            }
        });
    }
}