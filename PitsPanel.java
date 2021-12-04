import java.util.ArrayList;
import javax.swing.*;
/**
 * Panel that contains all of the pits in the view of Mancala.
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */
public class PitsPanel extends JPanel {
    private ArrayList<PitButton> pitsList;

    public PitsPanel(Icon icon) {
        pitsList = new ArrayList<PitButton>();
        
        for (int i = 0; i < 6; i++) {
            PitButton btn = new PitButton(icon);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setOpaque(false);
            btn.setBorderPainted(false);
            
            pitsList.add(btn);
            add(btn);
        }
        setOpaque(false);
    }

    public PitButton getPitButton(int index) {
        return pitsList.get(index);
    }
}
