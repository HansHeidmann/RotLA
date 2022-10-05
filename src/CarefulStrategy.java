public class CarefulStrategy extends SearchAlgorithm {
    public CarefulStrategy() {
        this.type = "Careful";
        this.trapRoll = ((int)(Math.random() * 2) + 1);
        this.searchMod = 7;
    }
    
}
