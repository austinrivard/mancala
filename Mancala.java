import java.util.*;
/**
 * Mancala - a game in which two players move stones around the board
 * with the goal of getting the most number of stones in their mancala.
 */

public class Mancala {
    private boolean player1Turn = true;
    private boolean actionMade = false;
    private int numberOfUndos = 0;
    private ArrayList<Pit> pitList = new ArrayList<Pit>(14);
    public Mancala(int numberOfStones) {
        for (Pit p: pitList) {
            if (pitList.indexOf(p)== 6 || pitList.indexOf(p)== 13) {
                p = new MancalaPit(0);
            }
            else {p = new Pit(numberOfStones);}
            
        }
    }
    public boolean pickPit(int index) { //Need to add free turn functionality when landing in your own mancala.
        if (!actionMade) {return false;}
        if (index < 0 || index == 6 || index == 13) {return false;}
        int moves = pitList.get(index).getStones();
        pitList.get(index).removeStones();
        int currentIndex = index;
        
        for (int i = moves; i > 0; i--) {
            if (currentIndex == 6 && !player1Turn) {i++;}
            else if (currentIndex == 13 && player1Turn) {i++;}
            else{pitList.get(currentIndex).addStones(1);}
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
            player1Turn = !player1Turn; // Player ends their turn
        }
        
        pitList.get(index).removeStones();
        actionMade = true;
        return true;
    }
    public void endTurn() { //When player hits endTurn button.
        if (!actionMade) {return;}
        for (Pit p: pitList) {
            p.updateOldStones();
        }
        refreshUndo();
        player1Turn = !player1Turn;
        actionMade = false;

        /* Add function where you check each players side after every ENDTURN to see if the game ends. */
        for (index = 0; index < 6; index++) {
            if (pitList.get(index) != 0) {break;}
        }

        for (index = 7; index < 13; index++) {
            if (pitList.get(index) != 0) {break;}
        }

        //^^^Add in a gameEnd function if one of these two conditions are satisfied.
        return;
    }
    public boolean undo() {
    /*
    Still need to add onto end turn function.
    */
        if (numberOfUndos==3) { //Possibly add statement into view saying "Max # of undos reached"?
            return false;
        }
        for (Pit p: pitList) {
            p.revertStones();
        }
        numberOfUndos++;
        return true;
    }
    public boolean getTurn() {
        return player1Turn;
    }
    public void refreshUndo() { //Used after the completion of a player's turn
        numberOfUndos = 0;
        return;
    }


}
