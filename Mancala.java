import java.util.*;
import javax.swing.event.*;

/**
 * Mancala - a game in which two players move stones around the board
 * with the goal of getting the most number of stones in their mancala. (Model)
 * Contains 6 pits on the north and south sides of the board, and two "Mancala" 
 * pits on the east and west sides of the board.
 * @author Austin Rivard
 * @author Robert Yav
 * @author Brendan Requierme
 */
public class Mancala {
    private boolean player1Turn = true;
    private boolean actionMade = false;
    private int numberOfUndos = 0;
    private ArrayList<Pit> pitList;
    private ArrayList<ChangeListener> listeners;

    public Mancala(int numberOfStones) {
        listeners = new ArrayList<ChangeListener>();
        pitList = new ArrayList<Pit>();
        for (int i=0; i<14;i++) {
            
            if (i== 6 || i== 13) {
                pitList.add(new Pit(0));
            }
            else 
            pitList.add(new Pit(numberOfStones));
        }
    }
    /**
     * Given a pit, takes the stones from that pit and places one stone each into further pits.
     * Special cases for when a player obtains an extra turn by ending in their own mancala and when a player steals from the opposite adjacent side.
     * @param index Pit that is being chosen.
     * @return Whether or not an action has been made.
     */
    public boolean pickPit(int index) {
        if ( (index > 6 && player1Turn) || (index < 6 && !player1Turn)) {  //Player didn't choose a pit on their side.
            System.out.println("Please choose a pit from your side.");
            return false;
        }
        int moves = pitList.get(index).getStones();
        if(moves == 0) { //Player chose an empty pit on their side.
            System.out.println("Please choose a pit with stones in it.");
            return false;
        }
        if (actionMade) {return false;}
        if (index < 0 || index == 6 || index == 13) {return false;}
        pitList.get(index).removeStones();
        int currentIndex = index;
    
        for (int i = moves; i > 0; i--) {
            if (currentIndex == 6 && !player1Turn) {i++;}
            else if (currentIndex == 13 && player1Turn) {i++;}
            else {
                if (currentIndex == 13) {
                    pitList.get(0).addStones(1);
                } else {
                    pitList.get(currentIndex+1).addStones(1);
                }
            }
            currentIndex++;
            if (currentIndex > 13) {currentIndex = 0;}
        }
        int indexOfOpposite = 12 - currentIndex;
        if(currentIndex == 13 || currentIndex == 6) { // Special Case: Marble Lands in Mancala
            for (Pit p: pitList) {
                p.updateOldStones();
            }
            actionMade = !actionMade;
        } 
        else if(pitList.get(currentIndex).getStones()==1 && currentIndex > 6 && player1Turn == false) // Special Case: Marble lands in empty spot in player 2's side of board
        {
                pitList.get(13).steal(pitList.get(indexOfOpposite));
                pitList.get(13).steal(pitList.get(currentIndex)); 
        } 
        else if(pitList.get(currentIndex).getStones()==1 && currentIndex < 6 && player1Turn == true) // Special Case: Marble lands in empty spot on player 1's side of board
        {
                pitList.get(6).steal(pitList.get(indexOfOpposite));
                pitList.get(6).steal(pitList.get(currentIndex));
        }
        
        notifyView();
        actionMade = !actionMade;
        
        return true;
    }
    
    /**
     * A player ends their turn. Checks if all stones on a player's side is empty before either continuing or ending the game.
     */
    public void endTurn() {
        System.out.println("Endturn button was pressed.");
        if (!actionMade) {return;}
        for (Pit p: pitList) {
            p.updateOldStones();
        }
        refreshUndo();
        player1Turn = !player1Turn;
        actionMade = false;

        int index;
        //Function to check special endgame occurrence if one player's side is all empty.
        int numberOfStonesPlayer1= 0;
        int numberOfStonesPlayer2 = 0;
        for (index = 0; index < 6; index++) {
            numberOfStonesPlayer1 = numberOfStonesPlayer1 + pitList.get(index).getStones();
        }
        
        for (index = 7; index < 13; index++) {
            numberOfStonesPlayer2 = numberOfStonesPlayer2 + pitList.get(index).getStones();
        }
        if (numberOfStonesPlayer1 == 0) {
            for (index = 7; index < 13; index++) {
                pitList.get(13).steal(pitList.get(index));
                //endgame
            }
        }
        if (numberOfStonesPlayer2 == 0) {
            for (index = 0; index < 6; index++) {
                pitList.get(6).steal(pitList.get(index));
                //endgame
            }
        }
        notifyView();

        if(player1Turn) {System.out.println("It is now player 1's turn.");} 
        else { System.out.println("It is now player 2's turn.");}

        return;
    }

    /**
     * Returns number of undos a player made during their turn.
     * @return Number of undos made during a player's turn.
     */
    public int getNumOfUndos() {
        return numberOfUndos;
    }

    /**
     * Undoes a player's turn back to the beginning of the turn. Can only be used three times.
     * @return Whether or not a player's turn is able to be undone. If true, undone is complete.
     */
    public boolean undo() {

        if (!actionMade) { 
            return false; 
        }
        if (numberOfUndos >= 3) {
            System.out.println("Max number of undos taken.");
            return false;
        }
        for (Pit p: pitList) {
            p.revertStones();
        }
        actionMade = false;
        numberOfUndos++;
        return true;
    }

    /**
     * Gets whether it is player 1's turn or player 2's turn
     * @return Whether it is Player 1's turn
     */
    public boolean getTurn() {
        return player1Turn;
    }

    /**
     * Used after the completion of a player's turn. Refreshes the undos.
     */
    public void refreshUndo() {
        numberOfUndos = 0;
        return;
    }

    /**
     * Returns the pitlist of the mancala board.
     * @return the current pitlist in the game.
     */
    public ArrayList<Pit> getPitList() {
        return pitList;
    }
    /**
     * Attaches a ChangeListener to Mancala.
     * @param cl ChangeListner to be added.
     */
    public void attachChangeListener(ChangeListener cl) {
        listeners.add(cl);
    }
    /**
     * Notifies change listeners of changes within mancala.
     */
    public void notifyView() {
        for (ChangeListener listener:listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }
}
