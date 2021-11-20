import java.util.ArrayList;
import javax.swing.*;

public class PitsPanel extends JPanel {
    private ArrayList<PitButton> pitsList;

    public PitsPanel(Icon icon) {
        for (int i = 0; i < 6; i++) {
            pitsList.add(new PitButton(icon, i));
            add(pitsList.get(i));
        }
    }

    public PitButton getPitButton(int index) {
        return pitsList.get(index);
    }
}