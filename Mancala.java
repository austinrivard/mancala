import java.util.*;
/**
 * Mancala - a game in which two players move stones around the board
 * with the goal of getting the most number of stones in their mancala.
 */

public class Mancala {
    private boolean playerTurn = true;     //True = Player A's turn. False = Player B's turn. Maybe rename variable?
    private int numberOfUndos = 0;  //Need to implement updating variable after each player's turn. 
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
        int currentIndex = index;
        
        for (int i = moves; i > 0; i--) {

            pitList.get(currentIndex).addStones(1); // later implement if statements for playerTurn
            if (currentIndex >= 13) {currentIndex = 0;}
        }
        pitList.get(index).removeStones();
        return true;
    }
    public boolean undo(){
    /*
    To implement this, was thinking of adding a placeholder for original status of each pit in the board. (done)
    Still need to add onto end turn function.
    */
        if (numberOfUndos==3) {
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
