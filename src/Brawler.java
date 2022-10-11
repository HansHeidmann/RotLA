///
/// Brawler -> SUBCLASS of Adventurer
///
public class Brawler extends Adventurer {
    public Brawler(String type, CombatAlgorithm combatStrategy, SearchAlgorithm searchStrategy, Room currentRoom) {
        this.type = type;
        this.combatStrategy = combatStrategy;
        this.searchStrategy = searchStrategy;
        this.currentRoom = currentRoom;
    }
    public void fight(Creature creature, int adventurerRoll, int enemyRoll) {
        //super.fight(creature, adventurerRoll + 2, enemyRoll); // Brawler +2 fight boost
    }

    public String toString(){
        return "Brawler";
    }
}
