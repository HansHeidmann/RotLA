public class CombatAlgorithm {
    String type;
    public CombatAlgorithm() {
        //
    }
    public int rollDice(int treasureModifier) {
        System.out.println("untrained combat");
        return ((int)(Math.random() * 12) + 1 + treasureModifier); // Two 6-sided dice
    }
}
