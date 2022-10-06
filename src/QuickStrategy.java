public class QuickStrategy extends SearchAlgorithm {
    // strategy subclass
    public QuickStrategy() {
        this.type = "Quick";
        this.skipRoll =((int)(Math.random() * 3) + 1);
        this.searchMod = 9;
    }
    

}
