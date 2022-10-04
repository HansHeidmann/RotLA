public class StealthStrategy extends CombatAlgorithm {
    public StealthStrategy() {
        this.type = "Stealth";
    }
    public int rollDice(int treasureModifier) {
        System.out.println("stealth combat");
        return ((int)(Math.random() * 12) + 1 + treasureModifier); // Two 6-sided dice
    }

}
