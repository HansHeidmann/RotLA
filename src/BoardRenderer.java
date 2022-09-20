import java.util.ArrayList;
import java.util.Objects;

public class BoardRenderer {

    Integer turnsTaken = 0;
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Adventurer> adventurers = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();

    public BoardRenderer() {
        createRooms();
        findAdjacentRooms();
        spawnAdventurers();
        spawnCreatures();
    }

    public void displayGameState() {
        // print turn #
        System.out.println("RotLA Turn " + turnsTaken + ":");
        // render board
        for(Room room: rooms) {
            System.out.println(room.id + ": " + "- : -");
        }
        // list adventurers stats
        for(Adventurer adventurer: adventurers) {
            System.out.println(adventurer.type + " - " + adventurer.treasuresFound + " Treasures(s) - " + adventurer.damage + " Damage");
        }
        // list creatures stats
        //countCreatures();
        for(Creature creature: creatures) {
            //System.out.println(creature.type + " - " + creature.treasuresFound + " Treasures(s) - " + creature.damage + " Damage");
        }

    }

    public Room getRoomByID(String ID) {
        Room foundRoom = rooms.get(0);
        for (Room room: rooms) {
            if(room.y == ID.charAt(0) && room.x == ID.charAt(2) && room.z == ID.charAt(4)) {
                foundRoom = room;
            }
        }
        return foundRoom;
    }
    private void createRooms() {
        // ground level entry room
        rooms.add(new Room(0, 1, 1, "0-1-1", this));
        // 9 rooms per floor
        for(int y = 1; y <= 4; y++) {
            for(int x = 0; x <= 2; x++) {
                for(int z = 0; z <= 2; z++) {
                    rooms.add(new Room(y, x, z, y + "-" + x + "-" + z, this));
                }
            }
        }
    }

    private void findAdjacentRooms() {
        for (Room room: rooms) {
            room.findAdjacentRooms();
        }
    }
    private void spawnAdventurers() {
        Room groundLevelRoom = getRoomByID("0-1-1");
        // brawler
        Adventurer brawler = new Brawler();
        brawler.currentRoom = getRoomByID("0-1-1");
        brawler.type = "Brawler";
        adventurers.add(brawler);
        // sneaker
        Adventurer sneaker = new Sneaker();
        sneaker.currentRoom = getRoomByID("0-1-1");
        sneaker.type = "Sneaker";
        adventurers.add(sneaker);
        // runner
        Adventurer runner = new Runner();
        runner.currentRoom = getRoomByID("0-1-1");
        runner.type = "Runner";
        adventurers.add(runner);
        // thief
        Adventurer thief = new Thief();
        thief.currentRoom = getRoomByID("0-1-1");
        thief.type = "Thief";
        adventurers.add(thief);
    }

    private void spawnCreatures() {
        // 4 of each type
        for (int i=0; i<4; i++) {
            creatures.add(new Orbiter());
            creatures.add(new Seeker());
            creatures.add(new Blinker());
        }
    }
}
