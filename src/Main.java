public class Main {
    public static void main(String[] args) {

        BoardRenderer renderer = new BoardRenderer();

        while (!renderer.gameOver) {
            // main loop
            renderer.displayGameState();
            renderer.takeTurn();
            // sleep
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
