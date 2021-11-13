import java.util.*;
/**
 * Mancala - a game in which two players move stones around the board
 * with the goal of getting the most number of stones in their mancala.
 */

public class Mancala {
    private boolean playerTurn; //True = Player A's turn. False = Player B's turn. Maybe rename variable?
                                //Need to implement updating variable after each player's turn. 
    private ArrayList<Pit> pitList = new ArrayList<Pit>(14);
    public Mancala(int numberOfStones) {
        for (Pit p: pitList) {
            if (pitList.indexOf(p)== 6 || pitList.indexOf(p)== 13) {
                p = new MancalaPit(numberOfStones);
            }
            else {p = new Pit(numberOfStones);}
            
        }
    }
    public boolean pickPit(int index) {
        if (index < 0 || index == 6 || index == 13) {return false;}
        int moves = pitList.get(index).getStones();
        int currentIndex = index;
        
        for (int i = moves; i > 0; i--) {
            pitList.get(currentIndex).addStones(1);
            if (currentIndex >= 13) {currentIndex = 0;}
        }
        return true;
    }
    public boolean undo(){
    /*
    To implement this, was thinking of adding a placeholder for original status of each pit in the board. (done)
    Still need to add onto end turn function.
    */
        for (Pit p: pitList) {
            p.revertStones();
        }
        return true;
    }
    public boolean getTurn(){
        return playerTurn;
    }


}
