public class Seeker extends Creature {

    public void move() {
        if(currentRoom.adventurers.size() == 0) {
            for(Room room: currentRoom.adjacentRooms) {
                if(room.adventurers.size() > 0) {
                    currentRoom.removeCreature(this);
                    room.addCreature(this);
                    currentRoom = room;
                }
            }
        }
    }

}
