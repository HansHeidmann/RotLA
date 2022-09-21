import java.util.ArrayList;

///
/// Creature << ABSTRACT >> --- compare with Adventurer class to see use of Cohesion
///
public abstract class Creature {
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
        if (currentRoom.adventurers.size() > 0) { // if there is at least one adventurer
            for (Adventurer adventurer: currentRoom.adventurers) {
                if (!currentRoom.renderer.gameOver && adventurer.alive) {
                    fight(adventurer, rollDice(), adventurer.rollDice());
                }
            }
        }
    }

    public void move() {
        ArrayList<Room> adjacentRooms = currentRoom.adjacentRooms;
        int numberOfAdjacentRooms = adjacentRooms.size();
        int randomAdjacentRoomIndex = (int)(Math.random() * numberOfAdjacentRooms);

        Room previousRoom = currentRoom;
        previousRoom.removeCreature(this);

        currentRoom = adjacentRooms.get(randomAdjacentRoomIndex);
        currentRoom.addCreature(this);

        // System.out.println("A(n) " + type + " moved from: " + previousRoom.id + " to " + currentRoom.id);
    }

    private void checkForEnemy(){
        //
    }

    public void fight(Adventurer adventurer, int creatureRoll, int adventurerRoll) {
        // System.out.println(type + " fighting " + adventurer);

        if (creatureRoll > adventurerRoll) {
            // System.out.println(type + " did 1 damage to " + adventurer);
            adventurer.damage++; // creature dies (all types have 1 health)
            if(adventurer.damage >= 3) {
                adventurer.die();
            }
        } else {
            // System.out.println(adventurer + " did 1 damage to " + type);
            damage++; // adventurer takes 1 damage
            if(damage >= 1) {
                die();
            }
        }
    }

    public void die() {
        alive = false;
    }

    public String toString(){
        return type;
    }

}
