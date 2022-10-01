///
/// Orbiter -> SUBCLASS of Creature
///
public class Orbiter extends Creature {
    private final Integer direction;  // 0 - clockwise, 1 - counter-clockwise
    public Orbiter() {
        direction = (int)(Math.random() * 2);
    }

    public void move() {

        if(currentRoom.adventurers.size() == 0) { // if there isn't an Adventurer in the current room:

            int x = currentRoom.x; // current x
            int z = currentRoom.z; // current z
            int tx = 0; // target x
            int tz = 0; // target z

            // clockwise/counter-clockwise movement, depending on int direction
            if(x == 0 && z == 0) { if (direction == 0) { tx = 1; tz = 0; } else { tx = 0; tz = 1; } }
            if(x == 1 && z == 0) { if (direction == 0) { tx = 2; tz = 0; } else { tx = 0; tz = 0; } }
            if(x == 2 && z == 0) { if (direction == 0) { tx = 2; tz = 1; } else { tx = 1; tz = 0; } }

            if(x == 0 && z == 2) { if (direction == 0) { tx = 0; tz = 1; } else { tx = 1; tz = 2; } }
            if(x == 1 && z == 2) { if (direction == 0) { tx = 0; tz = 2; } else { tx = 2; tz = 2; } }
            if(x == 2 && z == 2) { if (direction == 0) { tx = 1; tz = 2; } else { tx = 2; tz = 1; } }

            if(x == 2 && z == 1) { if (direction == 0) { tx = 2; tz = 2; } else { tx = 2; tz = 0; } }
            if(x == 0 && z == 1) { if (direction == 0) { tx = 0; tz = 0; } else { tx = 0; tz = 2; } }

            // move to room with target x,z
            gotoRoom(tx, tz);

        }

    }
    // ENCAPSULATION - this private function is encapsulated here and can not be called somewhere else
    private void gotoRoom(int x, int z) {
        int y = currentRoom.y;
        Room targetRoom = currentRoom.renderer.getRoomByID(y + "-" + x + "-" + z);
        currentRoom.removeCreature(this);
        targetRoom.addCreature(this);
        currentRoom = targetRoom;
    }

}
