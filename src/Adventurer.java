import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adventurer {

    String type;
    Boolean alive;
    Integer damage;
    Integer treasuresFound;
    Room currentRoom;

    public Adventurer() {
        alive = true;
        damage = 0;
        treasuresFound = 0;
    }

    public int rollDice() {
        return ((int)(Math.random() * 12) + 1); // Two 6-sided dice
    }

    public void takeTurn() {
        move();
        if (currentRoom.creatures.size() > 0) { // if there is at least one enemy
            for (Creature creature: currentRoom.creatures) {
                if (!currentRoom.renderer.gameOver) {
                    fight(creature, rollDice(), creature.rollDice());
                }
            }
        } else {
            searchForTreasure(rollDice());
        }
    }

    private void move() {
        ArrayList<Room> adjacentRooms = currentRoom.adjacentRooms;
        int numberOfAdjacentRooms = adjacentRooms.size();
        int randomAdjacentRoomIndex = (int)(Math.random() * numberOfAdjacentRooms);

        Room previousRoom = currentRoom;
        previousRoom.removeAdventurer(this);

        currentRoom = adjacentRooms.get(randomAdjacentRoomIndex);
        currentRoom.addAdventurer(this);

        System.out.println(type + " moved from: " + previousRoom.id + " to " + currentRoom.id);
    }

    public void fight(Creature creature, int adventurerRoll, int creatureRoll) {
        System.out.println("***** BATTLE *****");
        System.out.println("Room ID: " + currentRoom.id);
        System.out.println("Room Creatures: " + currentRoom.creatures);
        System.out.println(type + " fighting " + creature);
        if (adventurerRoll > creatureRoll) {
            System.out.println(type + " did 1 damage to " + creature);
            creature.damage++; // creature dies (all types have 1 health)
            creature.checkIfDead();
        } else {
            System.out.println(creature + " did 1 damage to " + type);
            damage++; // adventurer takes 1 damage
            if(damage >= 3){
                die();
                currentRoom.removeAdventurer(this);
            }
        }
        System.out.println("Room Creatures update: " + currentRoom.creatures);
    }

    private void searchForTreasure(int treasureRoll) {
        if (treasureRoll >= 10) {
            treasuresFound++;
        }
    }

    public void checkIfDead() {
        if (damage == 3) {
            alive = false;
            die();
        }
    }

    private void die() {
        alive = false;
    }
}
