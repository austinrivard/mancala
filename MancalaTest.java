import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
/**
 * Provides a user interface to play Mancala. (View, Controller)
 * 
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */
public class MancalaTest {
    public static void main(String[] args) {
        startSettingsWindow();
    }

    private static void startSettingsWindow() {
        JFrame frame = new JFrame("Style Settings Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 200);
        frame.setPreferredSize(new Dimension(300, 400));

        JPanel settingsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(3, 3, 3, 3);
        c.gridy = 0;
        c.gridwidth = 2;
        JLabel label = new JLabel("Number of stones");
        settingsPanel.add(label, c);
        
        c.gridy = 1;
        c.gridwidth = 1;
        JButton[] numStonesButtons = new JButton[2];
        for (int i = 0; i <= 1; i++) {
            c.gridx = i;
            String text = String.valueOf(i + 3);
            JButton newButton = new JButton(text);
            newButton.setFocusPainted(false);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBorderPainted(false);
            newButton.setPreferredSize(new Dimension(32, 32));
            newButton.addActionListener(e -> {
                JButton srcBtn = (JButton) e.getSource();
                for (JButton btn : numStonesButtons) {
                    boolean selected = btn == srcBtn;
                    btn.setSelected(selected);
                    btn.setBorderPainted(selected);
                }
            });
            settingsPanel.add(newButton, c);
            numStonesButtons[i] = newButton;
        }

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        label = new JLabel("Board style");
        settingsPanel.add(label, c);

        c.gridy = 3;
        c.gridwidth = 1;
        JButton[] boardStylesButtons = new JButton[2];
        for (int i = 0; i <= 1; i++) {
            c.gridx = i;
            String filename = String.format("img/board%d.png", i);
            ImageIcon icon = new ImageIcon(filename);
            icon.setImage(icon.getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
            JButton newButton = new JButton(icon);
            newButton.setFocusPainted(false);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBorderPainted(false);
            newButton.setPreferredSize(new Dimension(32, 32));
            newButton.setActionCommand(filename);
            newButton.addActionListener(e -> {
                JButton srcBtn = (JButton) e.getSource();
                for (JButton btn : boardStylesButtons) {
                    boolean selected = btn == srcBtn;
                    btn.setSelected(selected);
                    btn.setBorderPainted(selected);
                }
            });
            settingsPanel.add(newButton, c);
            boardStylesButtons[i] = newButton;
        }

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        settingsPanel.add(new JLabel("Pit style"), c);

        c.gridy = 7;
        c.gridwidth = 1;
        JButton[] pitsStylesButtons = new JButton[2];
        for (int i = 0; i <= 1; i++) {
            c.gridx = i;
            String filename = String.format("img/pit%d.png", i);
            ImageIcon icon = new ImageIcon(filename);
            icon.setImage(icon.getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
            JButton newButton = new JButton(icon);
            newButton.setFocusPainted(false);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBorderPainted(false);
            newButton.setPreferredSize(new Dimension(32, 32));
            newButton.setActionCommand(filename);
            newButton.addActionListener(e -> {
                JButton srcBtn = (JButton) e.getSource();
                for (JButton btn : pitsStylesButtons) {
                    boolean selected = btn == srcBtn;
                    btn.setSelected(selected);
                    btn.setBorderPainted(selected);
                }
            });
            settingsPanel.add(newButton, c);
            pitsStylesButtons[i] = newButton;
        }

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        settingsPanel.add(new JLabel("Stone style"), c);

        c.gridy = 9;
        c.gridwidth = 1;
        JButton[] stonesStylesButtons = new JButton[2];
        for (int i = 0; i <= 1; i++) {
            c.gridx = i;
            ImageIcon icon = new ImageIcon(String.format("img/stone%d.png", i));
            icon.setImage(icon.getImage().getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
            JButton newButton = new JButton(icon);
            newButton.setFocusPainted(false);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBorderPainted(false);
            newButton.setPreferredSize(new Dimension(32, 32));
            newButton.addActionListener(e -> {
                JButton srcBtn = (JButton) e.getSource();
                for (JButton btn : stonesStylesButtons) {
                    boolean selected = btn == srcBtn;
                    btn.setSelected(selected);
                    btn.setBorderPainted(selected);
                }
            });
            settingsPanel.add(newButton, c);
            stonesStylesButtons[i] = newButton;
        }

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(event -> {
            int numStones = 0;
            for (JButton btn : numStonesButtons) if (btn.isSelected()) numStones = Integer.parseInt(btn.getText());

            String filename = "";
            for (JButton btn : boardStylesButtons) if (btn.isSelected()) filename = btn.getActionCommand();
            ImageIcon boardIcon = new ImageIcon(filename);
            boardIcon.setImage(boardIcon.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH));

            filename = "";
            for (JButton btn : pitsStylesButtons) if (btn.isSelected()) filename = btn.getActionCommand();
            ImageIcon pitsIcon = new ImageIcon(filename);
            pitsIcon.setImage(pitsIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
            ImageIcon mancalaIcon = new ImageIcon(filename);
            
            mancalaIcon.setImage(mancalaIcon.getImage().getScaledInstance(150, 400, Image.SCALE_SMOOTH));

            Icon stoneIcon = null;
            for (JButton btn : stonesStylesButtons) if (btn.isSelected()) stoneIcon = btn.getIcon();
            

            if (numStones != 0 && boardIcon != null && pitsIcon != null && stoneIcon != null) {
                // Icon mancalaIcon = pitsIcon;
                frame.dispose();
                startGame(numStones, boardIcon, mancalaIcon, pitsIcon, stoneIcon);
            }
        });
        c.gridy = 10;
        c.gridx = 0;
        c.gridwidth = 2;
        settingsPanel.add(startButton, c);

        frame.add(settingsPanel);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Starts the game with the options chosen from the GUI.
     * @param numStones Number of stones each pit starts off with.
     * @param boardIcon Style of icon used for the board
     * @param mancalaIcon Style of icon used for the mancala pit
     * @param pitsIcon Style of icon used for the pits
     * @param stoneIcon Style of icon used for the stones
     */
    private static void startGame(int numStones, Icon boardIcon, Icon mancalaIcon, Icon pitsIcon, Icon stoneIcon) {
        Style style = new Style() {
            public int numStones() { return numStones; }
            public Icon boardIcon() { return boardIcon; }
            public Icon mancalaIcon() { return mancalaIcon; }
            public Icon pitIcon() { return pitsIcon; }
            public Icon stoneIcon() { return stoneIcon; }
        };
        Mancala game = new Mancala(numStones);
        MancalaBoard board = new MancalaBoard(game, style); 
    }
}