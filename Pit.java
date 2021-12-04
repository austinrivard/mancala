/**
 * Part of the model, pits in the mancala board. Holds stones that are then taken and distributed into other pits.
 */
public class Pit {
    private int stones;
    private int oldStones;
    public Pit(int numberOfStones) {
        this.stones = numberOfStones;
    }
    public int getStones() {return stones;}
    public void addStones(int numberOfStones) {
        stones = stones+numberOfStones;
    }
    public void revertStones() { //Used for the undo functionality in Mancala
        this.stones = oldStones;
    }
    public void updateOldStones() { //Used when a player's turn ends
        this.oldStones = stones;
    }
    public void removeStones() {stones = 0;}
    public void steal(Pit p){
        this.stones = stones + p.getStones();
        p.removeStones();
    }
}
