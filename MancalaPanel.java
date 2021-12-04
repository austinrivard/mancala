import java.awt.Color;
import javax.swing.*;
/**
 * Panel that represents a mancala board.
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */
public class MancalaPanel extends JPanel {
    public MancalaPanel(Icon icon) {
        JLabel mancalaLabel = new JLabel(icon);
        mancalaLabel.setOpaque(false);
        add(mancalaLabel);
        setBackground(new Color(0, 0, 0, 0));
    }
}
