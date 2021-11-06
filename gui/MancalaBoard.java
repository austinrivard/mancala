package gui;

import javax.swing.*;

public class MancalaBoard {
    private Style style;
    private MancalaPanel mancala1, mancala2;
    private PitsPanel pits1, pits2;
    private int numStones;

    public MancalaBoard(Style style, int numStones) {
        this.style = style;
        this.numStones = numStones;
    }
}
