public class UntrainedStrategy extends CombatAlgorithm {
    public UntrainedStrategy() {
        this.type = "Untrained";
    }
    public int rollDice(int treasureModifier) {
        //debug
        //System.out.println("untrained combat");
        return ((int)(Math.random() * 12) + 1 + treasureModifier); // Two 6-sided dice
    }
}
