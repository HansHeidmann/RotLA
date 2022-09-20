import javax.swing.*;
import java.util.ArrayList;

public class Adventurer {

    String type;
    Boolean alive;
    Integer currentRoll;
    Integer damage;
    Integer treasuresFound;
    Room currentRoom;
    BoardRenderer renderer;

    public Adventurer() {
        alive = true;
        damage = 3;
        treasuresFound = 0;
    }

    private int rollDice() {
        return ((int)(Math.random() * 6) + 1); // cast as int for return
    }

    private void takeTurn() {
        move();
        int currentRoll = rollDice();
        if(checkForEnemy() != null) {
            fight(currentRoll);
        } else {
            searchForTreasure();
        }
    }

    private void move() {
        ArrayList<Room> adjacentRooms = currentRoom.adjacentRooms;
        int numberOfAdjacentRooms = adjacentRooms.size();
        int randomAdjacentRoomIndex = (int)(Math.random() * numberOfAdjacentRooms);

        Room previousRoom = currentRoom;

        currentRoom = adjacentRooms.get(randomAdjacentRoomIndex);

        System.out.println("An Adventurer moved from: " + previousRoom.id + " to " + currentRoom.id);
    }

    private Creature checkForEnemy(){
        return null;
    }

    private void fight(int roll) {

    }

    private void searchForTreasure() {

    }
}
