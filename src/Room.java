import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Room {

    BoardRenderer renderer;
    Integer y;
    Integer x;
    Integer z;
    String id;
    ArrayList<Treasure> treasures = new ArrayList<>();
    ArrayList<Adventurer> adventurers = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();

    ArrayList<Room> adjacentRooms = new ArrayList<>();
    ArrayList<String> roomIDs = new ArrayList<>();

    public Room(int yPos, int xPos, int zPos, String roomID, BoardRenderer boardRenderer) {
        renderer = boardRenderer;
        y = yPos;
        x = xPos;
        z = zPos;
        id = roomID;
    }

    // ADD / REMOVE --- Treasures, Adventurers, and Creatures
    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }
    public void removeTreasure(Treasure treasure) {
        treasures.remove(treasure);
    }
    public void addAdventurer(Adventurer adventurer) {
        adventurers.add(adventurer);
    }
    public void removeAdventurer(Adventurer adventurer) {
        adventurers.remove(adventurer);
    }
    public void addCreature(Creature creature) {
        creatures.add(creature);
    }
    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    // FIND & STORE ADJACENT ROOMS
    public void findAdjacentRooms() {

        if (y == 0) {
            roomIDs.add("1-1-1");
        } else {
            // if it's a room in the 4 floors below ground:
            if (x == 0 && z == 0) { // top left
                roomIDs.add(y + "-0-1");
                roomIDs.add(y + "-1-0");
            }
            if (x == 0 && z == 1) { // left middle
                roomIDs.add(y + "-0-0");
                roomIDs.add(y + "-0-2");
                roomIDs.add(y + "-1-1");
            }
            if (x == 0 && z == 2) { // bottom left
                roomIDs.add(y + "-0-1");
                roomIDs.add(y + "-1-2");
            }
            if (x == 1 && z == 0) { // top middle
                roomIDs.add(y + "-0-0");
                roomIDs.add(y + "-1-1");
                roomIDs.add(y + "-2-0");
            }
            if (x == 1 && z == 1) { // center room
                if (y > 1) {
                    roomIDs.add(y-1 + "-1-1");
                }
                if(y < 4) {
                    roomIDs.add((y+1) + "-1-1");
                }
                roomIDs.add(y + "-0-1");
                roomIDs.add(y + "-1-0");
                roomIDs.add(y + "-1-2");
                roomIDs.add(y + "-2-1");
            }
            if (x == 1 && z == 2) { // bottom middle
                roomIDs.add(y + "-0-2");
                roomIDs.add(y + "-1-1");
                roomIDs.add(y + "-2-2");
            }
            if (x == 2 && z == 0) { // top right
                roomIDs.add(y + "-1-0");
                roomIDs.add(y + "-2-1");
            }
            if (x == 2 && z == 1) { // right middle
                roomIDs.add(y + "-2-0");
                roomIDs.add(y + "-1-1");
                roomIDs.add(y + "-2-2");
            }
            if (x == 2 && z == 2) { // bottom right
                roomIDs.add(y + "-2-1");
                roomIDs.add(y + "-1-2");
            }
        }

        for (String roomID: roomIDs) {
            for (Room room: renderer.rooms) {
                if (Objects.equals(room.id, roomID)) {
                    adjacentRooms.add(room);
                }
            }
        }

    }

}
