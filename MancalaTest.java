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
        JFrame frame = new JFrame("Mancala Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 200);
        frame.setPreferredSize(new Dimension(300, 400));

        JPanel settingsPanel = new JPanel();

        JRadioButton[] numStonesButtons = new JRadioButton[2];
        numStonesButtons[0] = new JRadioButton("3");
        numStonesButtons[1] = new JRadioButton("4");
        ButtonGroup numStonesGroup = new ButtonGroup();
        for (JRadioButton btn : numStonesButtons) {
            numStonesGroup.add(btn);        
            settingsPanel.add(btn);
        }

        JRadioButton[] mancalaStylesButtons = new JRadioButton[2];
        mancalaStylesButtons[0] = new JRadioButton(new ImageIcon("img/mancala0.png"));
        mancalaStylesButtons[1] = new JRadioButton(new ImageIcon("img/mancala1.png"));
        ButtonGroup mancalaStylesGroup = new ButtonGroup();
        for (JRadioButton btn : mancalaStylesButtons) {
            mancalaStylesGroup.add(btn);
            settingsPanel.add(btn);
        }

        JRadioButton[] pitsStylesButtons = new JRadioButton[2];
        pitsStylesButtons[0] = new JRadioButton(new ImageIcon("img/pits0.png"));
        pitsStylesButtons[1] = new JRadioButton(new ImageIcon("img/pits1.png"));
        ButtonGroup pitsStylesGroup = new ButtonGroup();
        for (JRadioButton btn : pitsStylesButtons) {
            pitsStylesGroup.add(btn);
            settingsPanel.add(btn);
        }

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(event -> {
            int numStones = 0;
            for (JRadioButton btn : numStonesButtons) if (btn.isSelected()) numStones = Integer.parseInt(btn.getText());

            Icon mancalaIcon = null;
            for (JRadioButton btn : mancalaStylesButtons) if (btn.isSelected()) mancalaIcon = btn.getIcon();

            Icon pitsIcon = null;
            for (JRadioButton btn : pitsStylesButtons) if (btn.isSelected()) pitsIcon = btn.getIcon();

            if (numStones != 0 && mancalaIcon != null && pitsIcon != null) {
                frame.dispose();
                startGame(numStones, mancalaIcon, pitsIcon);
            }
        });
        settingsPanel.add(startButton);

        frame.add(settingsPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void startGame(int numStones, Icon mancalaIcon, Icon pitsIcon) {
        Style style = new Style() {
            public MancalaPanel mancalaPanel() { return new MancalaPanel(mancalaIcon); }
            public PitsPanel pitsPanel() { return new PitsPanel(pitsIcon); }
        };
        
        Mancala game = new Mancala(numStones);
        MancalaBoard board = new MancalaBoard(game, style);
    }
}