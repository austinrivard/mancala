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
    public boolean pickPit(int index) { //Need to add free turn functionality when landing in your own mancala.
        if (!turnMade) {return false;}
        if (index < 0 || index == 6 || index == 13) {return false;}
        int moves = pitList.get(index).getStones();
        int currentIndex = index;
        
        for (int i = moves; i > 0; i--) {

            pitList.get(currentIndex).addStones(1); // later implement if statements for playerTurn
            if (currentIndex >= 13) {currentIndex = 0;}
        }
        pitList.get(index).removeStones();
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
