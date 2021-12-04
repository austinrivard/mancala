import java.util.Random;
import java.awt.*;
import javax.swing.*;

/**
 * Custom button used for each pit.
 */
public class PitButton extends JButton {
    private int numStones;
    private Style style;

    public PitButton(Style style) {
        super(style.pitIcon());
        this.style = style;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random r = new Random();
        for (int i = 0; i < numStones; i++) {
            style.stoneIcon().paintIcon(this, g, 25 + r.nextInt(25), 30 + r.nextInt(25));
        }
    }

    public void setStoneCount(int numStones) {
        if (this.numStones == numStones) return;
        this.numStones = numStones;
        repaint();
    }
}