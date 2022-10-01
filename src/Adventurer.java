import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

///
/// Adventurer << ABSTRACT >>  --- compare with Creature class to see the code is very COHESIVE
///
public abstract class Adventurer {

    String type;
    Boolean alive;
    Integer damage;
    Integer treasuresFound;
    ArrayList<Treasure> inventory = new ArrayList<>();
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
        if (currentRoom.creatures.size() > 0) { // if there is at least one creature:
            for (Creature creature: currentRoom.creatures) {
                if (!currentRoom.renderer.gameOver && creature.alive) {
                    //fight(creature, rollDice(), creature.rollDice()); //  POLYMORPHISM  allows fight to work with all the different subclasses of creature
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

        // DEBUG
        // System.out.println(type + " moved from: " + previousRoom.id + " to " + currentRoom.id);
    }

    public void fight(Creature creature, int adventurerRoll, int creatureRoll) {

        // DEBUG
        // System.out.println("***** BATTLE *****");
        // System.out.println("Room ID: " + currentRoom.id);
        // System.out.println("Room Creatures: " + currentRoom.creatures);
        // System.out.println(type + " fighting " + creature);

        if (adventurerRoll > creatureRoll) {
            // System.out.println(type + " did 1 damage to " + creature);
            creature.damage++; // creature dies (all types have 1 health)
            if(creature.damage >= 1) {
                creature.die();
            }
        } else {
            // System.out.println(creature + " did 1 damage to " + type);
            damage++; // adventurer takes 1 damage
            if(damage >= 3) {
                die();
            }
        }

        // DEBUG
        // System.out.println("Room Creatures update: " + currentRoom.creatures);
    }

    private void searchForTreasure(int treasureRoll) {

        Treasure treasure;
        if (treasureRoll >= 10) {

            if (currentRoom.treasures.size() > 0) {
                treasure = currentRoom.treasures.get(0);
                // Damage the Adventurer if it's a Trap, and remove the Trap from the Room, then return
                if (Objects.equals(treasure.type, "Trap")) {
                    damage++;
                    currentRoom.removeTreasure(treasure);
                    return;
                }
                // If the Adventurer already has one of this type of Treasure, return (leave the Treasure for someone else)
                if (inventory.size() > 0) {
                    for (Treasure inventoryItem : inventory) {
                        if (Objects.equals(inventoryItem.type, treasure.type)) {
                            return;
                        }
                    }
                }
                // Otherwise, add this Treasure object to Inventory
                inventory.add(treasure);
                treasuresFound++;
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
