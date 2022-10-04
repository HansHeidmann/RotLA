public class ExpertStrategy extends CombatAlgorithm {
    public ExpertStrategy() {
        this.type = "Expert";
    }
    public int rollDice(int treasureModifier) {
        System.out.println("expert combat");
        return ((int)(Math.random() * 12) + 1 + treasureModifier); // Two 6-sided dice
    }
}
