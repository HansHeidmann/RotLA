///
/// Brawler -> SUBCLASS of Adventurer
///
public class Brawler extends Adventurer {
    public Brawler() {
        //
    }
    public void fight(Creature creature, int adventurerRoll, int enemyRoll) {
        super.fight(creature, adventurerRoll + 2, enemyRoll); // Brawler +2 fight boost
    }
}
