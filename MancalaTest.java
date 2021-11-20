/**
 * Provides a user interface to play Mancala.
 * 
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        ImageIcon icon = new ImageIcon("img/mancala0.png");
        icon.setImage(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        mancalaStylesButtons[0] = new JRadioButton(icon);
        icon = new ImageIcon("img/mancala1.png");
        icon.setImage(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        mancalaStylesButtons[1] = new JRadioButton(icon);
        ButtonGroup mancalaStylesGroup = new ButtonGroup();
        for (JRadioButton btn : mancalaStylesButtons) {
            mancalaStylesGroup.add(btn);
            settingsPanel.add(btn);
        }

        JRadioButton[] pitsStylesButtons = new JRadioButton[2];
        icon = new ImageIcon("img/pit0.png");
        icon.setImage(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        pitsStylesButtons[0] = new JRadioButton(icon);
        icon = new ImageIcon("img/pit1.png");
        icon.setImage(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        pitsStylesButtons[1] = new JRadioButton(icon);
        ButtonGroup pitsStylesGroup = new ButtonGroup();
        for (JRadioButton btn : pitsStylesButtons) {
            pitsStylesGroup.add(btn);
            settingsPanel.add(btn);
        }

        JRadioButton[] stonesStylesButtons = new JRadioButton[2];
        icon = new ImageIcon("img/stone0.png");
        icon.setImage(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        stonesStylesButtons[0] = new JRadioButton(icon);
        icon = new ImageIcon("img/stone1.png");
        icon.setImage(icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        stonesStylesButtons[1] = new JRadioButton(icon);
        ButtonGroup stonesStylesGroup = new ButtonGroup();
        for (JRadioButton btn : stonesStylesButtons) {
            stonesStylesGroup.add(btn);
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

            Icon stoneIcon = null;
            for (JRadioButton btn : stonesStylesButtons) if (btn.isSelected()) stoneIcon = btn.getIcon();
            

            if (numStones != 0 && mancalaIcon != null && pitsIcon != null && stoneIcon != null) {
                frame.dispose();
                startGame(numStones, mancalaIcon, pitsIcon, stoneIcon);
            }
        });
        settingsPanel.add(startButton);

        frame.add(settingsPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void startGame(int numStones, Icon mancalaIcon, Icon pitsIcon, Icon stoneIcon) {
        Style style = new Style() {
            public MancalaPanel mancalaPanel() { return new MancalaPanel(mancalaIcon); }
            public PitsPanel pitsPanel() { return new PitsPanel(pitsIcon); }
            public Icon stoneIcon() { return stoneIcon; }
        };
        
        Mancala game = new Mancala(numStones);
        MancalaBoard board = new MancalaBoard(game, style);
    }
}