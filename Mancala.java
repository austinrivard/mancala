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
     * Special cases for when a player obtains an extra turn and when a player steals from the opposite adjacent side.
     * @param index Pit that is being chosen.
     * @return Whether or not an action has been made.
     */
    public boolean pickPit(int index) { //Need to add free turn functionality when landing in your own mancala.
        if ( (index > 6 && player1Turn) || (index < 6 && !player1Turn)) {  //Player didn't choose their side.
            System.out.println("Please choose a pit from your side.");
            return false;
        }
        if (actionMade) {return false;}
        if (index < 0 || index == 6 || index == 13) {return false;}
        int moves = pitList.get(index).getStones();
        pitList.get(index).removeStones();
        int currentIndex = index;
    
        for (int i = moves; i > 0; i--) {
            if (currentIndex == 6 && !player1Turn) {i++;}
            else if (currentIndex == 13 && player1Turn) {i++;}
            else if (currentIndex == 12) {
                pitList.get(currentIndex+1).addStones(1);
                currentIndex = 0;
            }
            else{pitList.get(currentIndex+1).addStones(1);}
            currentIndex++;
            if (currentIndex > 13) {currentIndex = 0;}
        }
        int indexOfOpposite = 12 - currentIndex;
        if(currentIndex == 13 || currentIndex == 6){} // Special Case: Marble Lands in Mancala
        else if(pitList.get(currentIndex).getStones()==0 && currentIndex > 6 && player1Turn == false) // Special Case: Marble lands in empty spot in player 2's side of board
        {
            pitList.get(currentIndex).steal(pitList.get(indexOfOpposite));
        } 
        else if(pitList.get(currentIndex).getStones()==0 && currentIndex < 6 && player1Turn == true) // Special Case: Marble lands in empty spot on player 1's side of board
        {
            pitList.get(currentIndex).steal(pitList.get(indexOfOpposite));
        }
        else {
            //player1Turn = !player1Turn; // Player ends their turn
        }
        for (ChangeListener cl : listeners) {
            cl.stateChanged(new ChangeEvent(this));
        }
        notifyView();
        actionMade = true;
        return true;
    }
    
    /**
     * A player ends their turn. Checks if all stones on a player's side is empty before either continuing or ending the game.
     */
    public void endTurn() {
        if (!actionMade) {return;}
        for (Pit p: pitList) {
            p.updateOldStones();
        }
        refreshUndo();
        player1Turn = !player1Turn;
        System.out.println(player1Turn);
        actionMade = false;

        int index;
        /* Add function where you check each players side after every ENDTURN to see if the game ends. */
        for (index = 0; index < 6; index++) {
            if (pitList.get(index).getStones() != 0) {break;}
        }

        for (index = 7; index < 13; index++) {
            if (pitList.get(index).getStones() != 0) {break;}
        }
        notifyView();
        //^^^Add in a gameEnd function if one of these two conditions are satisfied.

        if(player1Turn) {System.out.println("It is now player 1's turn.");} 
        else { System.out.println("It is now player 2's turn.");}

        return;
    }

    public int getNumOfUndos() {
        return numberOfUndos;
    }

    /**
     * Undoes a player's turn back to the beginning of the turn. Can only be used three times.
     * @return Whether or not a player's turn is able to be undone
     */
    public boolean undo() {
    /*
    Still need to add onto end turn function.
    */
        if (!actionMade) { 
            return false; 
        }
        if (numberOfUndos >= 3) { //Possibly add statement into view saying "Max # of undos reached"?
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

    public void attachChangeListener(ChangeListener cl) {
        listeners.add(cl);
    }
    public void notifyView() {
        for (ChangeListener listener:listeners) {
            listener.stateChanged(new ChangeEvent(this));;
        }
    }
}
