import java.util.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Panel that contains all of the pits in the view of Mancala.
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */
public class PitsPanel extends JPanel {
    private ArrayList<PitButton> pitsList;
    private int[] stoneCounts;
    private Style style;

    public PitsPanel(Style style) {
        this.style = style;
        stoneCounts = new int[6];
        pitsList = new ArrayList<PitButton>();
        
        for (int i = 0; i < 6; i++) {
            PitButton btn = new PitButton(style);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setOpaque(false);
            btn.setBorderPainted(false);
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    PitButton button = (PitButton)e.getSource();
                    button.setBorderPainted(true);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    PitButton button = (PitButton)e.getSource();
                    button.setBorderPainted(false);
                }
            });
            pitsList.add(btn);
            add(btn);
        }
        setOpaque(false);
    }
    /**
     * Returns a pit given a pit button.
     * @param index of the pit
     * @return a Pit within pit button.
     */
    public PitButton getPitButton(int index) {
        return pitsList.get(index);
    }
}
