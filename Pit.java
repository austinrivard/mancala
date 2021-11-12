public class Pit {
    private int stones;
    public Pit(int numberOfStones) {
        this.stones = numberOfStones;
    }
    public int getStones() {return stones;}
    public void addStones(int numberOfStones) {
        stones = stones+numberOfStones;}
    public void removeStones() {stones = 0;}
}
