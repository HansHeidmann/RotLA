import javax.swing.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    Integer hitPoints;
    Integer treasuresFound;
    ArrayList<Treasure> inventory = new ArrayList<>();
    Room currentRoom;

    CombatAlgorithm combatStrategy;
    SearchAlgorithm searchStrategy;
    private PropertyChangeSupport support;

    public Adventurer() {
        alive = true;
        damage = 0;
        hitPoints = 3;
        treasuresFound = 0;
        support = new PropertyChangeSupport(this);
    }

    public void takeTurn() {
        move();
        if (currentRoom.creatures.size() > 0) { // if there is at least one creature:

            for (Creature creature: currentRoom.creatures) {
                // debug
                // System.out.println(type + " FOUND A FUCKING CREATURE !!! ");
                if (!currentRoom.renderer.gameOver && creature.alive) {
                    fight(creature); //  POLYMORPHISM  allows fight to work with all the different subclasses of creature
                }
            }
        } else {
            searchForTreasure(searchStrategy.rollDice());
        }
    }

    private void move() {
        ArrayList<Room> adjacentRooms = currentRoom.adjacentRooms;
        int numberOfAdjacentRooms = adjacentRooms.size();
        int randomAdjacentRoomIndex = (int)(Math.random() * numberOfAdjacentRooms);

        Room previousRoom = currentRoom;
        previousRoom.removeAdventurer(this);

        currentRoom = adjacentRooms.get(randomAdjacentRoomIndex);
        support.firePropertyChange(this.toString()," enters room ",currentRoom.id); // send alert to Logger
        currentRoom.addAdventurer(this);

        // DEBUG
        // System.out.println(type + " moved from: " + previousRoom.id + " to " + currentRoom.id);
    }

    public void fight(Creature creature) {
        if (!currentRoom.renderer.gameOver && creature.alive) {
            // 50% chance Sneaker doesn't have to fight
            if (Objects.equals(combatStrategy.type, "Stealth") && (int) (Math.random() * 2) == 0) {
                //debug
                //System.out.println(type + " didn't have to fight.");
                return; // do nothing and exit fight()
            }

            // DEBUG
            /*
            System.out.println("***** BATTLE *****");
            System.out.println("Room ID: " + currentRoom.id);
            System.out.println("Room Creatures: " + currentRoom.creatures);
            System.out.println(type + " fighting " + creature);
            System.out.println(type + " inventory: " + inventory);
            */


            int adventurerRollModifier = 0;
            int creatureRollModifier = 0;
            for (Treasure treasure : inventory) {
                if (Objects.equals(treasure.type, "Sword")) {
                    adventurerRollModifier++;
                }
                if (Objects.equals(treasure.type, "Gem")) {
                    creatureRollModifier++;
                }
                if (Objects.equals(treasure.type, "Armor")) {
                    creatureRollModifier--;
                }
            }
            //debug
            /*
            System.out.println(type + " roll mod: " + adventurerRollModifier);
            System.out.println(creature.type + " roll mod: " + creatureRollModifier);
            */

            if (this.combatStrategy.rollDice(adventurerRollModifier) >= creature.rollDice(creatureRollModifier)) {
                //debug
                //System.out.println(type + " did 1 damage to " + creature);
                creature.damage++; // creature dies (all types have 1 health)

                support.firePropertyChange(this.toString()," defeats ",creature.toString()); // send alert to Logger

                // Celebrate
                this.combatStrategy.celebrate();
                if (!Objects.equals(combatStrategy.decorator.getCelebrations(), null)) {
                    support.firePropertyChange(this.toString()," ",combatStrategy.decorator.getCelebrations()); // send alert to Logger
                }

                if (creature.damage >= 1) {
                    support.firePropertyChange(creature.toString()," has ","Died"); // send alert to Logger
                    creature.die();
                }
            } else {
                //debug
                //System.out.println(creature + " did 1 damage to " + type);
                damage++; // adventurer takes 1 damage
                support.firePropertyChange(this.toString()," took ","1 damage"); // send alert to Logger

                if (damage >= hitPoints) {

                    support.firePropertyChange(creature.toString(), " defeats ", this.toString()); // send alert to Logger
                    support.firePropertyChange(this.toString()," has ","Died"); // send alert to Logger
                    die();
                }
            }

            // DEBUG
            // System.out.println("Room Creatures update: " + currentRoom.creatures);
        }
    }


    private void searchForTreasure(int treasureRoll) {

        Treasure treasure;
        if (searchStrategy.rollDice() >= searchStrategy.searchMod && (searchStrategy.skipRoll != 1) )  {

            if (currentRoom.treasures.size() > 0) {
                treasure = currentRoom.treasures.get(0);
                // Damage the Adventurer if it's a Trap, and remove the Trap from the Room, then return
                if (Objects.equals(treasure.type, "Trap") && (searchStrategy.trapRoll != 1) ) {
                    support.firePropertyChange(this.toString()," found a ", "Trap"); // send alert to Logger
                    damage++;
                    support.firePropertyChange(this.toString()," took ", "1 Damage from a Trap"); // send alert to Logger

                    //debug
                    //System.out.println("Trap 1 damage to " + type);
                    currentRoom.removeTreasure(treasure);
                    if(damage >= hitPoints) {
                        support.firePropertyChange(this.toString()," has ","Died"); // send alert to Logger
                        die();
                    }
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
                // If it's a potion, increase hitPoints to 4
                if (Objects.equals(treasure.type, "Potion")) {
                    hitPoints++;
                }

                inventory.add(treasure);
                support.firePropertyChange(this.toString()," found a ",treasure.type); // send alert to Logger
                currentRoom.removeTreasure(treasure);
                treasuresFound++;

            }
        }

    }



    public void die() {
        alive = false;
    }

    public String displayInventory(){
        String str = "";
        for (Treasure item : inventory){
            
             str = str  +item.type+",";
        }

       

        return str;
    }

    public void addPCL(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePCL(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
        
    }


}
