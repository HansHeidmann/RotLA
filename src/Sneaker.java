///
/// Sneaker -> SUBCLASS of Adventurer
///
public class Sneaker extends Adventurer {
    public Sneaker(String type, CombatAlgorithm combatStrategy, SearchAlgorithm searchStrategy, Room currentRoom) {
        this.type = type;
        this.combatStrategy = combatStrategy;
        this.searchStrategy = searchStrategy;
        this.currentRoom = currentRoom;
    }
    public void fight(Creature creature, int adventurerRoll, int enemyRoll) {
        // 50% chance Sneaker doesn't have to fight
        /*
        if ((int)(Math.random() * 2) == 0) {
            super.fight(creature);
        }
        */
    }

    public String toString(){
        return "Sneaker";
    }
}
