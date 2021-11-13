import javax.swing.*;
import java.awt.*;

public class PitButton extends JButton {
    private int index;

    public PitButton(Icon icon, int index) {
        super(icon);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}