public class SearchAlgorithm {
    String type;
    int trapRoll;
    int skipRoll;
    int searchMod;
    public SearchAlgorithm() {
        //
    }
    public int rollDice() {
        return ((int)(Math.random() * 12) + 1); // Two 6-sided dice
    }

}
