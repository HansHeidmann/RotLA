public class Orbiter extends Creature {

    Integer direction;  // 0 - clockwise, 1 - counter-clockwise

    public Orbiter() {
        direction = (int)(Math.random() * 2);
    }

    public void move() {

        if(currentRoom.adventurers.size() == 0) {

            int x = currentRoom.x;
            int z = currentRoom.z;
            int tx = 0;
            int tz = 0;

            if(x == 0 && z == 0) { if (direction == 0) { tx = 1; tz = 0; } else { tx = 0; tz = 1; } }
            if(x == 1 && z == 0) { if (direction == 0) { tx = 2; tz = 0; } else { tx = 0; tz = 0; } }
            if(x == 2 && z == 0) { if (direction == 0) { tx = 2; tz = 1; } else { tx = 1; tz = 0; } }

            if(x == 0 && z == 2) { if (direction == 0) { tx = 0; tz = 1; } else { tx = 1; tz = 2; } }
            if(x == 1 && z == 2) { if (direction == 0) { tx = 0; tz = 2; } else { tx = 2; tz = 2; } }
            if(x == 2 && z == 2) { if (direction == 0) { tx = 1; tz = 2; } else { tx = 2; tz = 1; } }

            if(x == 2 && z == 1) { if (direction == 0) { tx = 2; tz = 2; } else { tx = 2; tz = 0; } }
            if(x == 0 && z == 1) { if (direction == 0) { tx = 0; tz = 0; } else { tx = 0; tz = 2; } }

            gotoRoom(tx, tz);

        }

    }

    private void gotoRoom(int x, int z) {
        int y = currentRoom.y;
        Room targetRoom = currentRoom.renderer.getRoomByID(y + "-" + x + "-" + z);
        currentRoom.removeCreature(this);
        targetRoom.addCreature(this);
        currentRoom = targetRoom;
    }

}
