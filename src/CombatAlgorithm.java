public class CombatAlgorithm {
    // strategy pattern
    String type;
    CelebrateDecorator decorator;
    public CombatAlgorithm() {
        //
    }
    public int rollDice(int treasureModifier) {
        System.out.println("untrained combat");
        return ((int)(Math.random() * 12) + 1 + treasureModifier); // Two 6-sided dice
    }

    // SEE CelebrateDecorator subclass
    public void celebrate() {
        decorator = new CelebrateDecorator();

        for(int i=0; i<2; i++) { // flip 2 coins to add 0 to 2 celebrations

            if ((int)(Math.random()*2) == 0) { // coin flip

                int celebrationType = (int)(Math.random()*4); // choose a random of the 4 each of the 2 coin flips

                if (celebrationType == 0) {
                    decorator = new Dance(decorator);
                } else if (celebrationType == 1) {
                    decorator = new Jump(decorator);
                } else if (celebrationType == 2) {
                    decorator = new Shout(decorator);
                } else if (celebrationType == 3) {
                    decorator = new Spin(decorator);
                }
            }
        }

    }
}
