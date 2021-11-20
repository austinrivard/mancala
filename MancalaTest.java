/**
 * Provides a user interface to play Mancala.
 * 
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */

import javax.swing.*;
import java.awt.*;

public class MancalaTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Integer[] numStoneOptions = {3, 4};
        int choice = JOptionPane.showInternalOptionDialog(frame,
                                                        "Number of starting stones",
                                                        "Settings",
                                                        JOptionPane.DEFAULT_OPTION,
                                                        JOptionPane.PLAIN_MESSAGE,
                                                        null,
                                                        numStoneOptions,
                                                        null);
        int numStones = numStoneOptions[choice];

        ImageIcon[] mancalaStyles = new ImageIcon[2];
        mancalaStyles[0] = new ImageIcon("img/mancala0.png");
        mancalaStyles[1] = new ImageIcon("img/mancala1.png");

        ImageIcon[] pitsStyles = new ImageIcon[2];
        pitsStyles[0] = new ImageIcon("img/pits0.png");
        pitsStyles[1] = new ImageIcon("img/pits1.png");

        choice = JOptionPane.showInternalOptionDialog(frame,
                                                     "Choose mancala style",
                                                     "",
                                                     JOptionPane.DEFAULT_OPTION,
                                                     JOptionPane.PLAIN_MESSAGE,
                                                     null,
                                                     mancalaStyles,
                                                     null);
        Icon mancalaIcon = mancalaStyles[choice];                               

        choice = JOptionPane.showInternalOptionDialog(frame,
                                                     "Choose pits style",
                                                     "",
                                                     JOptionPane.DEFAULT_OPTION,
                                                     JOptionPane.PLAIN_MESSAGE,
                                                     null,
                                                     pitsStyles,
                                                     null);
        Icon pitsIcon = pitsStyles[choice];

        Style style = new Style() {
            public MancalaPanel mancalaPanel() { return new MancalaPanel(mancalaIcon); }
            public PitsPanel pitsPanel() { return new PitsPanel(pitsIcon); }
        };

        frame.dispose();

        Mancala game = new Mancala(numStones);
        MancalaBoard board = new MancalaBoard(game, style);
    }
}