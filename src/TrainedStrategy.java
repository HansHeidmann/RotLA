public class TrainedStrategy extends CombatAlgorithm {
    // strategy subclass
    public TrainedStrategy() {
        this.type = "Trained";
    }
    public int rollDice(int treasureModifier) {
        //debug
        //System.out.println("trained combat");
        return ((int)(Math.random() * 12) + 1 + treasureModifier + 1); // Two 6-sided dice
    }
}
