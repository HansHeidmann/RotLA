import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;

///
/// Creature << ABSTRACT >> --- compare with Adventurer class to see the code is very COHESIVE
///
public abstract class Creature {
    String type;
    Boolean alive;
    Integer damage;
    Room currentRoom;
    public PropertyChangeSupport support;

    public Creature() {
        damage = 0;
        alive = true;
        support = new PropertyChangeSupport(this);
    }

    public int rollDice(int treasureModifier) {
        return ((int)(Math.random() * 12) + 1 + treasureModifier); // Two 6-sided dice
    }

    public void takeTurn() {
        move();
        if (currentRoom.adventurers.size() > 0) { // if there is at least one adventurer
            for (Adventurer adventurer: currentRoom.adventurers) {
                if (!currentRoom.renderer.gameOver && adventurer.alive) {
                    fight(adventurer);
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

        support.firePropertyChange(this.toString()," enters room ",currentRoom.id); // send alert to Logger (observer pattern)
    }


    public void fight(Adventurer adventurer) {
        if (!currentRoom.renderer.gameOver && adventurer.alive) {
            // 50% chance Sneaker doesn't have to fight
            if (Objects.equals(adventurer.combatStrategy.type, "Stealth") && (int) (Math.random() * 2) == 0) {
                return;
            }
            // DEBUG
            /*
            System.out.println("***** BATTLE *****");
            System.out.println("Room ID: " + currentRoom.id);
            System.out.println("Room Creatures: " + currentRoom.creatures);
            System.out.println(type + " fighting " + adventurer);
            System.out.println(adventurer.type + " inventory: " + adventurer.inventory);
             */

            int adventurerRollModifier = 0;
            int creatureRollModifier = 0;
            for (Treasure treasure : adventurer.inventory) {
                if (treasure.getClass() == Sword.class) {
                    adventurerRollModifier++;
                }
                if (treasure.getClass() == Gem.class) {
                    creatureRollModifier++;
                }
                if (treasure.getClass() == Armor.class) {
                    creatureRollModifier--;
                }
            }
            /* debug
            System.out.println(adventurer.type + " roll mod: " + adventurerRollModifier);
            System.out.println(this.type + " roll mod: " + creatureRollModifier);
             */

            if (adventurer.combatStrategy.rollDice(adventurerRollModifier) >= this.rollDice(creatureRollModifier)) {
                //debug
                //System.out.println(adventurer.type + " did 1 damage to " + this);
                this.damage++; // creature dies (all types have 1 health)

                support.firePropertyChange(adventurer.toString()," defeats ",this.toString()); // send alert to Logger (observer pattern)

                // adventurer celebrate
                adventurer.combatStrategy.celebrate();
                if (!Objects.equals(adventurer.combatStrategy.decorator.getCelebrations(), null)) {
                    support.firePropertyChange(adventurer.toString(), " ", adventurer.combatStrategy.decorator.getCelebrations()); // send alert to Logger (observer pattern)
                }

                support.firePropertyChange(this.toString()," has  ","Died"); // send alert to Logger (observer pattern)
                this.die();

            } else {
                //debug
                //System.out.println(this + " did 1 damage to " + adventurer.type);
                adventurer.damage++; // adventurer takes 1 damage

                if (adventurer.damage >= adventurer.hitPoints) {

                    support.firePropertyChange(this.toString()," defeats ",adventurer.toString()); // send alert to Logger (observer pattern)
                    support.firePropertyChange(adventurer.toString()," has  ","Died"); // send alert to Logger (observer pattern)
                    adventurer.die();


                }
            }
        }
    }

    public void die() {

        alive = false;
        
    }

    public String toString(){
        return type;
    }

    // for Logger  (observer pattern)
    public void addPCL(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
    // for Logger  (observer pattern)
    public void removePCL(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
        
    }

}
