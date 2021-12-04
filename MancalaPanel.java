import java.awt.*;
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
    /**
     * Constructs a mancala board with a given style.
     * @param style Style of the board.
     */
    public MancalaPanel(Style style) {
        this.style = style;
        mancalaLabel = new JLabel(style.mancalaIcon());
        mancalaLabel.setOpaque(false);
        setOpaque(false);
        add(mancalaLabel);
        setBackground(new Color(0, 0, 0, 0));
    }
    /**
     * Returns the mancala label.
     * @return mancala label.
     */
    public JLabel getLabel() {
        return mancalaLabel;
    }
    /**
     * Repaints components inside the mancala board.
     * @param g graphics to be repainted.
     */
    public void repaint(Graphics g) {
        paintComponent(g);
    }
    /**
     * Paints icons within the board.
     */
    public void paintComponent(Graphics g) {
        Random r = new Random();
        for (int i = 0; i < numStones; i++) {
            style.stoneIcon().paintIcon(this, g, 30 + r.nextInt(50), 150 + r.nextInt(100));
        }
    }
    /**
     * Sets the stone count for pits.
     * @param numStones Number of stones to be set to.
     */
    public void setStoneCount(int numStones) {
        if (this.numStones == numStones) return;
        this.numStones = numStones;
        repaint();
    }
}
