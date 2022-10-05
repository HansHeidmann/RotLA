///
/// Blinker -> SUBCLASS of Creature
///
public class Blinker extends Creature {
    public Blinker() {
        //
    }
    public void move() {
        gotoRoom(1 + (int) (Math.random() * 4), (int) (Math.random() * 3), (int) (Math.random() * 3));
        support.firePropertyChange(this.toString()," enters room ",currentRoom.id); // alert
    }

    // ENCAPSULATION - this private function is encapsulated here and can not be called somewhere else
    private void gotoRoom(int y, int x, int z) {
        Room targetRoom = currentRoom.renderer.getRoomByID(y + "-" + x + "-" + z);
        currentRoom.removeCreature(this);
        targetRoom.addCreature(this);
        currentRoom = targetRoom;
    }
    public String toString(){
        return "Blinker";
    }

}
