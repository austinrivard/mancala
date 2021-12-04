import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;
/**
 * Panel that represents a mancala board.
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */
public class MancalaPanel extends JPanel {
    private int numStones;
    private Style style;
    private JLabel mancalaLabel;
    private ArrayList<JLabel> stones;

    public MancalaPanel(Style style) {
        this.style = style;
        stones = new ArrayList<JLabel>();
        mancalaLabel = new JLabel(style.mancalaIcon());
        mancalaLabel.setOpaque(false);
        setOpaque(false);
        add(mancalaLabel);
        setBackground(new Color(0, 0, 0, 0));
    }

    public JLabel getLabel() {
        return mancalaLabel;
    }

    public void paintComponent(Graphics g) {
        // super.paintComponent(g);
        Random r = new Random();
        for (int i = 0; i < numStones; i++) {
            style.stoneIcon().paintIcon(mancalaLabel, g, r.nextInt(50, 80), r.nextInt(100, 300));
        }
    }

    public void setStoneCount(int numStones) {
        if (this.numStones == numStones) return;
        this.numStones = numStones;
        repaint();
    }
}
