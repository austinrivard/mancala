import java.awt.Color;
import javax.swing.*;

public class MancalaPanel extends JPanel {
    public MancalaPanel(Icon icon) {
        JLabel mancalaLabel = new JLabel(icon);
        mancalaLabel.setOpaque(false);
        add(mancalaLabel);
        setBackground(new Color(0, 0, 0, 0));
    }
}
