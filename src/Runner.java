///
/// Runner -> SUBCLASS of Adventurer
///
public class Runner extends Adventurer {
    public Runner() {
        //
    }
    public void takeTurn() {
        // take 2 turns
        for (int i=0; i<2; i++) {
            super.takeTurn();
        }
    }
}
