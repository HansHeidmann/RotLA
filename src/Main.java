public class Main {
    public static void main(String[] args) {

        BoardRenderer renderer = new BoardRenderer();
        Tracker tracker = new Tracker();
        renderer.addPCL(tracker);
        while (!renderer.gameOver) {

            // main loop
           // renderer.displayGameState();
            renderer.takeTurn();

            // sleep - optional
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        renderer.removePCL(tracker);
        renderer.displayGameState(); // display 1 last time when game has ended
        System.out.println(renderer.endMessage);

    }
}
