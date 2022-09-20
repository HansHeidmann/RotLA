import java.util.ArrayList;

public class Creature {

    String type;
    Integer damage;
    Boolean alive;
    Room currentRoom;


    public Creature() {
        damage = 0;
        alive = true;
    }

    public int rollDice() {
        return ((int)(Math.random() * 12) + 1); // Two 6-sided dice
    }

    public void takeTurn() {
        move();
        checkForEnemy();
    }

    private void move() {
        ArrayList<Room> adjacentRooms = currentRoom.adjacentRooms;
        int numberOfAdjacentRooms = adjacentRooms.size();
        int randomAdjacentRoomIndex = (int)(Math.random() * numberOfAdjacentRooms);

        Room previousRoom = currentRoom;
        previousRoom.removeCreature(this);

        currentRoom = adjacentRooms.get(randomAdjacentRoomIndex);
        currentRoom.addCreature(this);

        System.out.println("A(n) " + type + " moved from: " + previousRoom.id + " to " + currentRoom.id);
    }

    private void checkForEnemy(){
        //
    }

    public void fight(Adventurer adventurer, int roll) {
        System.out.println(type + " fighting " + adventurer);
    }

    public void checkIfDead() {
        if (damage == 1) {
            die();
        }
    }

    private void die() {
        alive = false;
    }

}
