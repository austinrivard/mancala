import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Provides the layout of the mancala board.
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */
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
                JLabel mancalaLabel;
                if (i == 6) {
                    //mancala a
                    mancalaLabel = mancalaA.getLabel();
                    mancalaLabel.setText("A");
                }
                else {
                    //mancala b
                    mancalaLabel = mancalaB.getLabel();
                    mancalaLabel.setText("B");
                }
                mancalaLabel.setFont(mancalaLabel.getFont().deriveFont(24f));
                mancalaLabel.setForeground(Color.WHITE);
                mancalaLabel.setHorizontalTextPosition(SwingConstants.CENTER);
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
                button.setForeground(Color.WHITE);
                button.setActionCommand(String.valueOf(i));
                button.addActionListener(event -> {
                    PitButton btn = (PitButton) event.getSource();
                    int pitIndex = Integer.valueOf(btn.getActionCommand());
                    System.out.printf("Picked pit %d\n", pitIndex);
                    System.out.println("Pit has " + game.getPitList().get(pitIndex).getStones()+ " stones");
                    game.pickPit(pitIndex);
                    System.out.println("Pit now has " + game.getPitList().get(pitIndex).getStones()+ " stones");
                });
            }
        }

        JButton undoButton = new JButton("Undo");
        undoButton.setVerticalTextPosition(SwingConstants.CENTER);
        undoButton.setFont(undoButton.getFont().deriveFont(16f));
        undoButton.setHorizontalTextPosition(SwingConstants.CENTER);
        undoButton.addActionListener(event -> {
            if (game.undo()){
                System.out.println("Undo Successful, number of undos left: " + (3 - game.getNumOfUndos()));
                updateStoneCount();
            } else {
                System.out.println("Please take an action.");
            }
        });

        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.setVerticalTextPosition(SwingConstants.CENTER);
        endTurnButton.setFont(endTurnButton.getFont().deriveFont(16f));
        endTurnButton.setHorizontalTextPosition(SwingConstants.CENTER);
        endTurnButton.addActionListener(event -> {
            game.endTurn();
        });
        
        JTextArea playerTurn = new JTextArea();
        playerTurn.setText("Player 1's Turn");
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                boolean player1Turn = game.getTurn();
                if (player1Turn) {
                    playerTurn.setText("Player 1's Turn");
                }
                else if (!player1Turn)
                    playerTurn.setText("Player 2's Turn");
            }
        };
        game.attachChangeListener(listener);
        
        ImageIcon boardIcon = (ImageIcon) style.boardIcon();
        boardIcon.setImage(boardIcon.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH));
        boardLabel = new JLabel(style.boardIcon());
        boardLabel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        c.gridwidth = 1;
        c.weighty = 1.0;
        c.weightx = 1.0;
        boardLabel.add(mancalaB, c);

        c.gridx = 9; 
        boardLabel.add(mancalaA, c);

        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 2;
        c.gridy = 1; 
        c.gridheight = 1;
        c.gridwidth = 6;
        boardLabel.add(pitsB, c);

        c.gridy = 6;
        boardLabel.add(pitsA, c);

        c.weighty = 0.0;
        c.weightx = 0.0;
        c.gridwidth = 6;
        c.gridheight = 2;
        c.gridy = 4;
        c.gridx = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(undoButton);
        buttonPanel.add(endTurnButton);
        buttonPanel.setPreferredSize(new Dimension(300, 1500));
        buttonPanel.setMinimumSize(new Dimension(300, 150));
        buttonPanel.setOpaque(false);
        boardLabel.add(buttonPanel, c);
        
        JFrame frame = new JFrame("Mancala");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(boardLabel);
        frame.pack();
        frame.setVisible(true);

        updateStoneCount();
    }

    private void updateStoneCount() {
        ArrayList<Pit> pitList = game.getPitList();
        for (int i = 0; i < pitList.size(); i++) {
            int numStones = pitList.get(i).getStones();
            if (i == 6 || i == 13) {
                //mancala a | mancala b
                MancalaPanel mancalaPanel;
                if (i == 6) mancalaPanel = mancalaA;
                else mancalaPanel = mancalaB;

                mancalaPanel.setStoneCount(numStones);
            }
            else {
                // pits a : i < 6
                // pits b : i > 6
                if (i < 6) {
                    pitsA.getPitButton(i).setStoneCount(numStones);
                } else {
                    pitsB.getPitButton(12 - i).setStoneCount(numStones);
                }
            }
        }
    }
}
