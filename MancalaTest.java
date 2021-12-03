/**
 * Provides a user interface to play Mancala.
 * 
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

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
            ImageIcon icon = new ImageIcon(String.format("img/board%d.png", i));
            icon.setImage(icon.getImage().getScaledInstance(800, 400, java.awt.Image.SCALE_SMOOTH));
            JButton newButton = new JButton(icon);
            newButton.setFocusPainted(false);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBorderPainted(false);
            newButton.setPreferredSize(new Dimension(32, 32));
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
        c.gridy = 4;
        c.gridwidth = 2;
        settingsPanel.add(new JLabel("Mancala style"), c);

        c.gridy = 5;
        c.gridwidth = 1;
        JButton[] mancalaStylesButtons = new JButton[2];
        for (int i = 0; i <= 1; i++) {
            c.gridx = i;
            ImageIcon icon = new ImageIcon(String.format("img/mancala%d.png", i));
            icon.setImage(icon.getImage().getScaledInstance(60, 100, java.awt.Image.SCALE_SMOOTH));
            JButton newButton = new JButton(icon);
            newButton.setFocusPainted(false);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBorderPainted(false);
            newButton.setPreferredSize(new Dimension(32, 32));
            newButton.addActionListener(e -> {
                JButton srcBtn = (JButton) e.getSource();
                for (JButton btn : mancalaStylesButtons) {
                    boolean selected = btn == srcBtn;
                    btn.setSelected(selected);
                    btn.setBorderPainted(selected);
                }
            });
            settingsPanel.add(newButton, c);
            mancalaStylesButtons[i] = newButton;
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
            ImageIcon icon = new ImageIcon(String.format("img/pit%d.png", i));
            icon.setImage(icon.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
            JButton newButton = new JButton(icon);
            newButton.setFocusPainted(false);
            newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            newButton.setBorderPainted(false);
            newButton.setPreferredSize(new Dimension(32, 32));
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
            icon.setImage(icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
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

            Icon boardIcon = null;
            for (JButton btn : boardStylesButtons) if (btn.isSelected()) boardIcon = btn.getIcon();

            Icon mancalaIcon = null;
            for (JButton btn : mancalaStylesButtons) if (btn.isSelected()) mancalaIcon = btn.getIcon();

            Icon pitsIcon = null;
            for (JButton btn : pitsStylesButtons) if (btn.isSelected()) pitsIcon = btn.getIcon();

            Icon stoneIcon = null;
            for (JButton btn : stonesStylesButtons) if (btn.isSelected()) stoneIcon = btn.getIcon();
            

            if (numStones != 0 && boardIcon != null 
                && mancalaIcon != null && pitsIcon != null && stoneIcon != null) {
                frame.dispose();
                startGame(numStones, boardIcon, mancalaIcon, pitsIcon, stoneIcon);
            }
        });
        c.gridy = 10;
        c.gridx = 0;
        c.gridwidth = 2;
        // c.weightx = 1.;
        settingsPanel.add(startButton, c);

        frame.add(settingsPanel);
        frame.pack();
        frame.setVisible(true);
    }

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