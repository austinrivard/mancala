import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MancalaBoard {
    private Style style;
    private JLabel boardLabel;
    private Mancala game;
    private MancalaPanel mancalaA, mancalaB;
    private PitsPanel pitsA, pitsB;

    public MancalaBoard(Mancala game, Style style) {
        this.game = game;
        this.style = style;
        mancalaA = new MancalaPanel(style);
        mancalaB = new MancalaPanel(style);
        pitsA = new PitsPanel(style);
        pitsB = new PitsPanel(style);

        game.attachChangeListener(e -> updateStoneCount());

        for (int i = 0; i <= 13; i++) {
            if (i == 6 || i == 13) {
                //mancala a || mancala b
                
            }
            else {
                // pits a : i < 6
                // pits b : i > 6
                PitButton button;
                if (i < 6) {
                    button = pitsA.getPitButton(i);
                    button.setText(String.format("%s%d", "A", i + 1));
                    button.setVerticalTextPosition(SwingConstants.BOTTOM);
                }
                else {
                    button = pitsB.getPitButton(12 - i);
                    button.setText(String.format("%s%d", "B", i - 6));
                    button.setVerticalTextPosition(SwingConstants.TOP);
                } 
                button.setFont(button.getFont().deriveFont(16f));
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setActionCommand(String.valueOf(i));
                button.addActionListener(event -> {
                    PitButton btn = (PitButton) event.getSource();
                    int pitIndex = Integer.valueOf(btn.getActionCommand());
                    game.pickPit(pitIndex);
                    System.out.printf("picked pit %d\n", pitIndex);
                    System.out.println("pit has" + game.getPitList().get(pitIndex).getStones()+ "stones");

                    //test
                    mancalaA.setStoneCount(pitIndex);
                    boardLabel.repaint();
                });
            }
        }

        boardLabel = new JLabel(style.boardIcon());
        boardLabel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 4;
        c.gridwidth = 2;
        c.weighty = 1.0;
        boardLabel.add(mancalaB, c);

        c.gridx = 9;
        boardLabel.add(mancalaA, c);

        c.weighty = 0.0;
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 6;
        boardLabel.add(pitsB, c);

        c.gridy = 3;
        boardLabel.add(pitsA, c);
        
        JFrame frame = new JFrame("Mancala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(boardLabel);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateStoneCount() {
        ArrayList<Pit> pitList = game.getPitList();
        for (int i = 0; i < pitList.size(); i++) {
            int numStones = pitList.get(i).getStones();
            if (i == 6 || i == 13) {
                //mancala a | mancala b
                if (i == 6) {
                    mancalaA.setStoneCount(numStones);
                    mancalaA.repaint();
                }
                else mancalaB.setStoneCount(numStones);
            }
            else {
                // pits a : i < 6
                // pits b : i > 6
                PitButton button;
                if (i < 6) {
                    button = pitsA.getPitButton(i);
                } else {
                    button = pitsB.getPitButton(12 - i);
                }

                for (int j = 0; j < numStones; j++) {
                    button.add(new JLabel(style.stoneIcon()));
                }
            }
        }
        // boardLabel.revalidate();
        
        boardLabel.repaint();
    }
}
