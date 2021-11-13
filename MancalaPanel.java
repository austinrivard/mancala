import javax.swing.*;

public class MancalaPanel extends JPanel {
    public MancalaPanel(MancalaIcon mancalaIcon) {
        add(new JLabel(mancalaIcon));
    }
}
