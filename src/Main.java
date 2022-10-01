public class Main {
    public static void main(String[] args) {

        BoardRenderer renderer = new BoardRenderer();

        while (!renderer.gameOver) {

            // main loop
            renderer.displayGameState();
            renderer.takeTurn();

            // sleep - optional
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        renderer.displayGameState(); // display 1 last time when game has ended
        System.out.println(renderer.endMessage);

        System.out.println("// Inventories of the Adventurers");
        System.out.println("// 20 Treasure objects (out of 24) because the 4 traps each do 1 damage instead of being picked up");
        System.out.println("// remember to un-comment fight() inside of takeTurn() for Adventurer and Creature");
        for (Adventurer adventurer: renderer.adventurers) {
            System.out.println(adventurer + ": " + adventurer.inventory);
        }

    }
}
