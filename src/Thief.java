///
/// Thief -> SUBCLASS of Adventurer
///
public class Thief extends Adventurer {
    public Thief(String type, CombatAlgorithm combatStrategy, SearchAlgorithm searchStrategy, Room currentRoom) {
        this.type = type;
        this.combatStrategy = combatStrategy;
        this.searchStrategy = searchStrategy;
        this.currentRoom = currentRoom;
    }

    public String toString(){
        return "Thief";
    }
}
