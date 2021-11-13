import java.util.*;
/**
 * Mancala - a game in which two players move stones around the board
 * with the goal of getting the most number of stones in their mancala.
 */

public class Mancala {
    private boolean playerTurn = true;  //True = Player A's turn. False = Player B's turn. Maybe rename variable?
    private boolean turnMade = false;
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
    public boolean pickPit(int index) {
        if (!turnMade) {return false;}
        if (index < 0 || index == 6 || index == 13) {return false;}
        int moves = pitList.get(index).getStones();
        pitList.get(index).removeStones();
        int currentIndex = index;
        
        for (int i = moves; i > 0; i--) {
            if (currentIndex == 6 && !playerTurn) {i++;}
            else if (currentIndex == 13 && playerTurn) {i++;}
            else{pitList.get(currentIndex).addStones(1);}
            currentIndex++;
            if (currentIndex > 13) {currentIndex = 0;}
        }
        int indexOfOpposite = 12 - currentIndex;
        if(currentIndex == 13 || currentIndex == 6){} // Special Case: Marble Lands in Mancala
        else if(pitList.get(currentIndex).getStones()==0 && currentIndex > 6 && playerTurn == false) // Special Case: Marble lands in empty spot in player 2's side of board
        {
            pitList.get(currentIndex).steal(pitList.get(indexOfOpposite));
        } 
        else if(pitList.get(currentIndex).getStones()==0 && currentIndex < 6 && playerTurn == true) // Special Case: Marble lands in empty spot on player 1's side of board
        {
            pitList.get(currentIndex).steal(pitList.get(indexOfOpposite));
        }

        
        turnMade = true;
        return true;
    }
    public void endTurn() { //When player hits endTurn button.
        if (!turnMade) {return;}
        for (Pit p: pitList) {
            p.updateOldStones();
        }
        refreshUndo();
        playerTurn = !playerTurn;
        turnMade = false;
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
        return playerTurn;
    }
    public void refreshUndo() { //Used after the completion of a player's turn
        numberOfUndos = 0;
        return;
    }


}
