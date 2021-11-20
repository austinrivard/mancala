import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MancalaBoard {
    private Style style;
    private MancalaPanel mancalaA, mancalaB;
    private PitsPanel pitsA, pitsB;

    public MancalaBoard(Mancala game, Style style) {
        this.style = style;
        mancalaA = style.mancalaPanel();
        mancalaB = style.mancalaPanel();
        pitsA = style.pitsPanel();
        pitsB = style.pitsPanel();

        for (int i = 0; i < 13; i++) {
            if (i == 6) continue;
            
            PitButton button;
            if (i < 6) button = pitsA.getPitButton(i);
            else button = pitsB.getPitButton(12 - i);

            button.addActionListener(event -> {
                PitButton btn = (PitButton) event.getSource();
                game.pickPit(btn.getIndex());
            });
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mancalaA, BorderLayout.EAST);
        frame.add(mancalaB, BorderLayout.WEST);
        frame.add(pitsA, BorderLayout.SOUTH);
        frame.add(pitsB, BorderLayout.NORTH);
        
        frame.pack();
        frame.setVisible(true);
    }
}
