public class StealthStrategy extends CombatAlgorithm {
    // strategy subclass
    public StealthStrategy() {
        this.type = "Stealth";
    }
    public int rollDice(int treasureModifier) {
        //debug
        //System.out.println("stealth combat");
        return ((int)(Math.random() * 12) + 1 + treasureModifier); // Two 6-sided dice
    }

}
