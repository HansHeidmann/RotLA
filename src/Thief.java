public class Thief extends Adventurer {
    public Thief() {
    }

    public int rollDice() {
        int roll = (int)((Math.random() * 12) + 1); // Two 6-sided dice
        roll += 1; // (+1) roll boost for thieves
        return roll;
    }
}
