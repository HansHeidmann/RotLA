public class Sneaker extends Adventurer {
    public Sneaker() {
    }

    public void fight(Creature creature, int adventurerRoll, int enemyRoll) {
        // 50% chance Sneaker doesn't have to fight
        if ((int)(Math.random() * 2) == 0) {
            super.fight(creature, adventurerRoll, enemyRoll);
        } else {
            System.out.println("Sneaker didn't have to fight.");
        }
    }
}
