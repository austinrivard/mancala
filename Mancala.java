import java.util.*;
/**
 * Mancala - a game in which two players move stones around the board
 * with the goal of getting the most number of stones in their mancala.
 */

public class Mancala {
    private boolean playerTurn = true; // true = player 1's turn; false = player 2's turn
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
        if (index < 0 || index == 6 || index == 13) {return false;}
        int moves = pitList.get(index).getStones();
        pitList.get(index).removeStones();
        int currentIndex = index;
        
        for (int i = moves; i > 0; i--) {
            if (currentIndex == 6 && !playerTurn) {moves++;}
            else if (currentIndex == 13 && playerTurn) {moves++;}
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
        else {
            playerTurn = !playerTurn; // Player ends their turn
        }
        
        return true;
    }
    public boolean undo() {
        if (numberOfUndos==3) {
            return false;
        }
        numberOfUndos++;
        // later implement method for undo
        return true;
    }


}
