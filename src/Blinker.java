public class Blinker extends Creature {

    @Override
    public void move() {
        gotoRoom(1 + (int) (Math.random() * 4), (int) (Math.random() * 3), (int) (Math.random() * 3));
    }

    private void gotoRoom(int y, int x, int z) {
        Room targetRoom = currentRoom.renderer.getRoomByID(y + "-" + x + "-" + z);
        currentRoom.removeCreature(this);
        targetRoom.addCreature(this);
        currentRoom = targetRoom;
    }

}
