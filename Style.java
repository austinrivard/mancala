import javax.swing.Icon;
/**
 * Style of the board, which is created through the GUI.
 */
public interface Style {
    public int numStones();
    public Icon boardIcon();
    public Icon mancalaIcon();
    public Icon pitIcon();
    public Icon stoneIcon();
}