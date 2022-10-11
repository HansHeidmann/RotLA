///
/// Runner -> SUBCLASS of Adventurer
///
public class Runner extends Adventurer {
    public Runner(String type, CombatAlgorithm combatStrategy, SearchAlgorithm searchStrategy, Room currentRoom) {
        this.type = type;
        this.combatStrategy = combatStrategy;
        this.searchStrategy = searchStrategy;
        this.currentRoom = currentRoom;
    }
    public void takeTurn() {
        // take 2 turns
        for (int i=0; i<2; i++) {
            super.takeTurn();
        }
    }
    public String toString(){
        return "Runner";
    }
}
