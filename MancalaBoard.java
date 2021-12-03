import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MancalaBoard {
    private Style style;
    private Mancala game;
    private MancalaPanel mancalaA, mancalaB;
    private PitsPanel pitsA, pitsB;

    public MancalaBoard(Mancala game, Style style) {
        this.game = game;
        this.style = style;
        mancalaA = new MancalaPanel(style.mancalaIcon());
        mancalaB = new MancalaPanel(style.mancalaIcon());
        pitsA = new PitsPanel(style.pitIcon());
        pitsB = new PitsPanel(style.pitIcon());

        for (int i = 0; i < 13; i++) {
            if (i == 6) continue;
            
            PitButton button;
            if (i < 6) button = pitsA.getPitButton(i);
            else button = pitsB.getPitButton(12 - i);

            button.setText(String.format("%s%d", i < 6 ? "A" : "B", i));
            button.setFont(button.getFont().deriveFont(16f));
            button.setVerticalTextPosition(i < 6 ? SwingConstants.BOTTOM : SwingConstants.TOP);
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            button.setActionCommand(String.valueOf(i));
            button.addActionListener(event -> {
                PitButton btn = (PitButton) event.getSource();
                game.pickPit(Integer.valueOf(btn.getActionCommand()));
                int pitIndex = Integer.valueOf(btn.getActionCommand());
                System.out.printf("picked pit %d\n",pitIndex );
                System.out.println("pit has" + game.getPitList().get(pitIndex).getStones()+ "stones");
                //
            });
        }

        JLabel boardLabel = new JLabel(style.boardIcon());
        boardLabel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // c.fill = GridBagConstraints.BOTH;

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 4;
        boardLabel.add(mancalaB, c);

        c.gridx = 9;
        boardLabel.add(mancalaA, c);

        c.gridx = 2;
        c.gridy = 0;
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
}
