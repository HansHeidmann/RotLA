///
/// Seeker -> SUBCLASS of Creature
///
public class Seeker extends Creature {
    public Seeker() {
        //
    }
    public void move() {
        if(currentRoom.adventurers.size() == 0) {
            for(Room room: currentRoom.adjacentRooms) {
                if(room.adventurers.size() > 0) {
                    currentRoom.removeCreature(this);
                    room.addCreature(this);
                    currentRoom = room;
                    support.firePropertyChange(this.toString()," Enters room ",currentRoom.id); // alert
                    return;
                }
            }
        }
        support.firePropertyChange(this.toString()," Stays in room ",currentRoom.id); // alert
    }

    public String toString(){
        return "Seeker";
    }

}
