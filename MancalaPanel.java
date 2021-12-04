import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MancalaPanel extends JPanel {
    private int numStones;
    private Style style;
    private JLabel mancalaLabel;

    public MancalaPanel(Style style) {
        this.style = style;
        // stoneLabels = new ArrayList<JLabel>();
        mancalaLabel = new JLabel(style.mancalaIcon());
        mancalaLabel.setOpaque(false);
        add(mancalaLabel);
        setBackground(new Color(0, 0, 0, 0));
    }

    public void setStoneCount(int numStones) {
        if (numStones == this.numStones) return;

        // repaint();
        this.numStones = numStones;
        for (int i = 0; i < numStones; i++) {
            JLabel stoneLabel = new JLabel(style.stoneIcon());
            stoneLabel.setOpaque(false);
            mancalaLabel.add(stoneLabel);
            mancalaLabel.revalidate();
        }

        // repaint();
        // printAll(getGraphics());
    //     if (numStones == stoneLabels.size()) return;
    //     System.out.println(style.stoneIcon());
    //     removeAll();
    //     stoneLabels = new ArrayList<JLabel>();
    //     mancalaLabel = new JLabel(style.mancalaIcon());
    //     mancalaLabel.setOpaque(false);
    //     add(mancalaLabel);
    //     setBackground(new Color(0, 0, 0, 0));
        
    //    for (int i = 0; i < numStones; i++) {
    //         JLabel stone = new JLabel(style.stoneIcon());
    //         stoneLabels.add(stone, );
    //         add(stone);
    //     }
    //     System.out.println(stoneLabels.size());
    //     revalidate();
    }
}
